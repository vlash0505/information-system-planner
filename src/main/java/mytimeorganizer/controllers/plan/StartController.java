package mytimeorganizer.controllers.plan;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import mytimeorganizer.models.Task;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class StartController extends BasePlanController implements Initializable {

    @FXML
    private VBox todayTasksVBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        List<Task> tasks = taskDAO.findByDateAndUserID(LocalDate.now(), Task.USER_ID);
        tasks.forEach(e -> addCheckboxWithDescription(e, todayTasksVBox));
    }
}
