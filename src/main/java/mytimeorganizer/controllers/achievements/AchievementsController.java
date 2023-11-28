package mytimeorganizer.controllers.achievements;

import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import mytimeorganizer.persistance.DAO.PropertiesLoader;
import mytimeorganizer.persistance.DAO.goals.DriverGoalDAO;
import mytimeorganizer.persistance.DAO.goals.GoalDAO;

import java.net.URL;
import java.util.ResourceBundle;

public class AchievementsController implements Initializable {

    protected GoalDAO goalDAO;

    Font font = new Font("Arial", 13);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DriverGoalDAO driverGoalDAO = PropertiesLoader.getDriverGoalDAOInstance();
        goalDAO = driverGoalDAO.getGoalDAO();
    }

    public void addAchievementAsText(String description, VBox vBox) {
        Text text = new Text();
        text.setFont(font);
        text.setText(description);
        text.setOpacity(0.85);
        vBox.getChildren().add(text);
    }

}
