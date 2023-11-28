package mytimeorganizer.controllers.achievements;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import mytimeorganizer.models.Goal;
import mytimeorganizer.view_logic.PaneViewSwitcher;
import mytimeorganizer.view_logic.View;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class WorkAchievementsController extends AchievementsController implements Initializable {

    @FXML
    public VBox workAchievementsVBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        List<Goal> goals = goalDAO.findCompletedByTypeAndUserId(Goal.USER_ID, "work");
        goals.forEach(e -> super.addAchievementAsText(e.getDescription(), workAchievementsVBox));
    }

    public void onSwitchToStudyAchievementsImage() {
        PaneViewSwitcher.switchTo(View.STUDY_ACHIEVEMENTS);
    }

    public void onSwitchToPersonalAchievementsImage() {
        PaneViewSwitcher.switchTo(View.PERSONAL_ACHIEVEMENTS);
    }
}
