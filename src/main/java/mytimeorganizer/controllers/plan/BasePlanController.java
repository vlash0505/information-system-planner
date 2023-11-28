package mytimeorganizer.controllers.plan;

import com.jfoenix.controls.JFXCheckBox;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import mytimeorganizer.models.Task;
import mytimeorganizer.persistance.DAO.PropertiesLoader;
import mytimeorganizer.persistance.DAO.tasks.DriverTaskDAO;
import mytimeorganizer.persistance.DAO.tasks.TaskDAO;

import java.net.URL;
import java.util.ResourceBundle;

public class BasePlanController implements Initializable {

    protected TaskDAO taskDAO;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DriverTaskDAO driverTaskDAO = PropertiesLoader.getDriverTaskDAOInstance();
        taskDAO = driverTaskDAO.getTaskDAO();
    }

    public void addCheckboxWithDescription(Task task, VBox vBox) {
        JFXCheckBox checkBox = new JFXCheckBox(task.getDescription());
        checkBox.setMinHeight(30.0);
        checkBox.setOnMouseClicked(e -> {
            if(checkBox.isSelected()) {
                taskDAO.makeTaskCompleted(task.getId());
            } else {
                taskDAO.makeTaskUncompleted(task.getId());
            }
        });
        vBox.getChildren().add(checkBox);
    }
}
