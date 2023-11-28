package mytimeorganizer.view_logic;

/**
 * Enumerator for views that are used in the app.
 */

public enum View {
    //scene views
    LOGIN("/views/authentication/signInView.fxml"),
    SIGNUP("/views/authentication/signUpView.fxml"),
    HOME("/views/base/homeView.fxml"),

    //pane views
    START("/views/startView.fxml"),
    JOURNAL("/views/journaling/journalingView.fxml"),
    PLAN("/views/plan/planView.fxml"),
    TIMER("/views/timer/timerView.fxml"),

    //goals switch views
    PERSONAL_GOALS("/views/goals/personalGoalsView.fxml"),
    WORK_GOALS("/views/goals/workGoalsView.fxml"),
    STUDY_GOALS("/views/goals/studyGoalsView.fxml"),

    //achievements switch views
    PERSONAL_ACHIEVEMENTS("/views/achievements/personalAchievementsView.fxml"),
    WORK_ACHIEVEMENTS("/views/achievements/workAchievementsView.fxml"),
    STUDY_ACHIEVEMENTS("/views/achievements/studyAchievementsView.fxml");

    private final String filename;

    /**
     * Sets the view mode.
     *
     * @param filename file of a scene
     */

    View(String filename) {
        this.filename = filename;
    }

    /**
     * Getter for the filename.
     *
     * @return filename as String.
     */

    public String getFilename() {
        return filename;
    }
}
