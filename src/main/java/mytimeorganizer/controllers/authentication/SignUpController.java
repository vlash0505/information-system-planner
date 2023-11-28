package mytimeorganizer.controllers.authentication;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.text.Text;
import mytimeorganizer.models.User;
import mytimeorganizer.persistance.DAO.users.DriverUserDAO;
import mytimeorganizer.persistance.DAO.PropertiesLoader;
import mytimeorganizer.persistance.DAO.users.UserDAO;
import mytimeorganizer.view_logic.View;
import mytimeorganizer.view_logic.SceneViewSwitcher;

import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpController implements Initializable {

    @FXML
    private Text signUpStatusBar;

    @FXML
    private TextField signUpEmailTextField;

    @FXML
    private TextField signUpUsernameTextField;

    @FXML
    private PasswordField signUpPasswordField;

    private UserDAO userDAO;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DriverUserDAO driverUserDAO = PropertiesLoader.getDriverUserDAOInstance();
        userDAO = driverUserDAO.getUserDAO();
    }

    public boolean validateInputFields() {
        return (regExValidation(signUpEmailTextField.getText(),    RegularExpression.EMAIL) &&
                regExValidation(signUpUsernameTextField.getText(), RegularExpression.USERNAME) &&
                regExValidation(signUpPasswordField.getText(),     RegularExpression.PASSWORD));
    }

    public void onLogInHyperlink() {
        SceneViewSwitcher.switchTo(View.LOGIN);
    }

    public boolean regExValidation(String toInspect, RegularExpression regularExpression) {
        Pattern pattern = Pattern.compile(regularExpression.getRegularExpression());
        Matcher match = pattern.matcher(toInspect);
        return match.matches();
    }

    public void onSignUpButton() {
        if(validateInputFields() && !userDAO.registrationValidation(signUpEmailTextField.getText(), signUpUsernameTextField.getText())) {
            User user = new User();
            user.setEmail(signUpEmailTextField.getText());
            user.setUsername(signUpUsernameTextField.getText());
            user.setPassword(signUpPasswordField.getText());
            userDAO.addNewUser(user);

            SceneViewSwitcher.switchTo(View.LOGIN);
        } else {
            signUpStatusBar.setText("User already exists or incorrect input");
        }
    }
}
