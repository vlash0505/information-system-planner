package mytimeorganizer.controllers.goals;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import mytimeorganizer.models.Goal;
import mytimeorganizer.view_logic.PaneViewSwitcher;
import mytimeorganizer.view_logic.View;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class WorkGoalsController extends GoalsController implements Initializable {

    @FXML
    private VBox workGoalsVBox;

    //Initializable override

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        List<Goal> goals = goalDAO.findUncompletedByTypeAndUserId(Goal.USER_ID, "work");
        goals.forEach(e -> super.addCheckboxWithDescription(e, workGoalsVBox));
    }

    /**
     * Adds pane with input when the "Add" button is pressed.
     */
    public void onAddWorkGoalButton() {
        super.onAddGoalButton(workGoalsVBox, "work");
    }

    /**
     * Switches to the study goals view.
     */
    public void onSwitchToStudyGoalsImage() {
        PaneViewSwitcher.switchTo(View.STUDY_GOALS);
    }

    /**
     * Switches to the personal goals view.
     */
    public void onSwitchToPersonalGoalsImage() {
        PaneViewSwitcher.switchTo(View.PERSONAL_GOALS);
    }

}
