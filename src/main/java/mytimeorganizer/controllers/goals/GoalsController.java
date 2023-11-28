package mytimeorganizer.controllers.goals;

import mytimeorganizer.models.Goal;
import mytimeorganizer.persistance.DAO.*;
import mytimeorganizer.persistance.DAO.goals.DriverGoalDAO;
import mytimeorganizer.persistance.DAO.goals.GoalDAO;
import mytimeorganizer.visual_components.PaneWithInput;

import com.jfoenix.controls.JFXCheckBox;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class GoalsController implements Initializable {

    protected GoalDAO goalDAO;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DriverGoalDAO driverGoalDAO = PropertiesLoader.getDriverGoalDAOInstance();
        goalDAO = driverGoalDAO.getGoalDAO();
    }

    public void onAddGoalButton(VBox vBox, String goalType) {
        if(vBox.getChildren().stream().anyMatch(e -> e instanceof PaneWithInput)) {
            return;
        }

        PaneWithInput paneWithInput = new PaneWithInput();
        vBox.getChildren().add(paneWithInput);

        paneWithInput.getController().getCheckmarkView().setOnMouseClicked(e -> {
            String description = paneWithInput.getController().getTextField().getText();
            if(description.length() < 45 && description.length() > 0) {
                Goal goal = new Goal();
                goal.setType(goalType);
                goal.setDescription(description);

                vBox.getChildren().remove(paneWithInput);
                goal.setId(addToDatabase(description, goalType));
                addCheckboxWithDescription(goal, vBox);
            }
        });
    }

    public void addCheckboxWithDescription(Goal goal, VBox vbox) {
        String description = goal.getDescription();
        JFXCheckBox box = new JFXCheckBox(description);
        box.setMinHeight(30.0);

        box.setOnMouseClicked(e -> {
            vbox.getChildren().remove(box);
            if(box.isSelected()) {
                vbox.getChildren().add(box);
                goalDAO.makeGoalCompleted(goal.getId());
            } else {
                vbox.getChildren().add(0, box);
                goalDAO.makeGoalUncompleted(goal.getId());
            }
        });
        vbox.getChildren().add(box);
    }

    public Long addToDatabase(String description, String goalType) {
        Goal goal = new Goal();
        goal.setDescription(description);
        goal.setType(goalType);

        return goalDAO.addGoal(goal);
    }
}
