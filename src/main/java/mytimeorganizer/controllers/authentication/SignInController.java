package mytimeorganizer.controllers.authentication;

import javafx.fxml.FXML;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import mytimeorganizer.models.Goal;
import mytimeorganizer.models.Note;
import mytimeorganizer.models.Task;
import mytimeorganizer.persistance.DAO.users.DriverUserDAO;
import mytimeorganizer.persistance.DAO.PropertiesLoader;
import mytimeorganizer.persistance.DAO.users.UserDAO;
import mytimeorganizer.view_logic.View;
import mytimeorganizer.view_logic.SceneViewSwitcher;

public class SignInController implements Initializable {

    @FXML
    private TextField signInUsernameField;

    @FXML
    private PasswordField signInPasswordField;

    @FXML
    public Text signInErrorMessageText;

    private UserDAO userDAO;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DriverUserDAO driverUserDAO = PropertiesLoader.getDriverUserDAOInstance();
        userDAO = driverUserDAO.getUserDAO();
    }

    public void onSignIn() {
        if (userDAO.existsUser(signInUsernameField.getText(), signInPasswordField.getText()) != null) {

            Long userId = userDAO.existsUser(signInUsernameField.getText(), signInPasswordField.getText());
            Goal.USER_ID = userId;
            Task.USER_ID = userId;
            Note.USER_ID = userId;

            signInPasswordField.setText("");
            signInErrorMessageText.setText("");

            SceneViewSwitcher.switchTo(View.HOME);
        } else {
            signInErrorMessageText.setText("Wrong username or password.");
            signInErrorMessageText.setTextAlignment(TextAlignment.CENTER);
        }
    }

    public void onSignUpHere() {
        SceneViewSwitcher.switchTo(View.SIGNUP);
    }
}
