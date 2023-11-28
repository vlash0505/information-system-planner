package mytimeorganizer.controllers.plan;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import mytimeorganizer.models.Task;
import mytimeorganizer.visual_components.PaneWithInput;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class PlanController extends BasePlanController implements Initializable {

    @FXML
    private Text errorMessageText;

    @FXML
    private DatePicker datePicker;

    @FXML
    private VBox tasksVBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        datePicker.valueProperty().addListener(
                (o, oldDate, date) -> {
                    tasksVBox.getChildren().clear();
                    List<Task> tasks = taskDAO.findByDateAndUserID(date, Task.USER_ID);
                    tasks.forEach(e -> addCheckboxWithDescription(e, tasksVBox));
                }
        );
        datePicker.setValue(LocalDate.now());
    }

    public void onAddTaskButton() {
        if(tasksVBox.getChildren().stream().anyMatch(e -> e instanceof PaneWithInput)) {
            return;
        }
        PaneWithInput paneWithInput = new PaneWithInput();
        tasksVBox.getChildren().add(paneWithInput);

        paneWithInput.getController().getCheckmarkView().setOnMouseClicked(e -> {
            String description = paneWithInput.getController().getTextField().getText();
            if(description.length() > 0 && description.length() < 45) {
                errorMessageText.setText("");

                Task task = new Task();
                task.setDate(datePicker.getValue());
                task.setDescription(description);

                tasksVBox.getChildren().remove(paneWithInput);
                task.setId(taskDAO.addNewTask(task));
                super.addCheckboxWithDescription(task, tasksVBox);
            } else {
                errorMessageText.setText("Wrong input data");
            }
        });
    }
}
