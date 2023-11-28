package mytimeorganizer.controllers.navigation;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import mytimeorganizer.view_logic.BaseSwitcher;
import mytimeorganizer.view_logic.PaneViewSwitcher;
import mytimeorganizer.view_logic.View;
import mytimeorganizer.view_logic.SceneViewSwitcher;

public class HomeController {

    @FXML
    AnchorPane contentArea;

    @FXML
    public void initialize() {
        PaneViewSwitcher.setPane(contentArea);
        PaneViewSwitcher.switchTo(View.START);
    }

    public void onHomeButton() {
        PaneViewSwitcher.switchTo(View.START);
    }

    public void onPlanButton() {
        PaneViewSwitcher.switchTo(View.PLAN);
    }

    public void onGoalsButton() {
        PaneViewSwitcher.switchTo(View.PERSONAL_GOALS);
    }

    public void onJournalingButton() {
        PaneViewSwitcher.switchTo(View.JOURNAL);
    }

    public void onAchievementsButton() {
        PaneViewSwitcher.switchTo(View.PERSONAL_ACHIEVEMENTS);
    }

    public void onTimerButton() {
        PaneViewSwitcher.switchTo(View.TIMER);
    }

    public void onLogOutHyperlink() {
        SceneViewSwitcher.switchTo(View.LOGIN);
        BaseSwitcher.clearCache();
    }
}
