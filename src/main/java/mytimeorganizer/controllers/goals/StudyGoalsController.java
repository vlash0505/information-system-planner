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

public class StudyGoalsController extends GoalsController implements Initializable {

    @FXML
    private VBox studyGoalsVBox;

    //Initializable Override

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        List<Goal> goals = goalDAO.findUncompletedByTypeAndUserId(Goal.USER_ID, "study");
        goals.forEach(e -> super.addCheckboxWithDescription(e, studyGoalsVBox));
    }

    /**
     * Adds pane with input when the "Add" button is pressed.
     */
    public void onAddStudyGoalButton() {
        super.onAddGoalButton(studyGoalsVBox, "study");
    }

    /**
     * Switches to the personal goals view.
     */
    public void onSwitchToPersonalGoalsImage() {
        PaneViewSwitcher.switchTo(View.PERSONAL_GOALS);
    }

    /**
     * Switches to the work goals view.
     */
    public void onSwitchToWorkGoalsImage() {
        PaneViewSwitcher.switchTo(View.WORK_GOALS);
    }
}
