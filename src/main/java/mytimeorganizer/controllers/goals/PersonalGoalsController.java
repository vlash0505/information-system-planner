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

public class PersonalGoalsController extends GoalsController implements Initializable {

    @FXML
    private VBox personalGoalsVBox;

    //Initializable override.

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        List<Goal> goals = goalDAO.findUncompletedByTypeAndUserId(Goal.USER_ID, "personal");
        goals.forEach(e -> super.addCheckboxWithDescription(e, personalGoalsVBox));
    }

    /**
     * Adds pane with input when the "Add" button is pressed.
     *
     */
    public void onAddPersonalGoalButton() {
        super.onAddGoalButton(personalGoalsVBox, "personal");
    }

    /**
     * Switches to the work goals view.
     *
     */
    public void onSwitchToWorkGoalsImage() {
        PaneViewSwitcher.switchTo(View.WORK_GOALS);
    }

    /**
     * Switches to the study goals view.
     *
     */
    public void onSwitchToStudyGoalsImage() {
        PaneViewSwitcher.switchTo(View.STUDY_GOALS);
    }
}
