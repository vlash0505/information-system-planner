package mytimeorganizer.view_logic;

import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * Class that implements scene-switching
 * in order for user to switch between
 * different views.
 */

public class SceneViewSwitcher {
    private static Scene scene;

    /**
     * Setter for scene.
     *
     * @param scene scene to be set as one to
     *              which user will be moved.
     */

    public static void setScene(Scene scene) {
        SceneViewSwitcher.scene = scene;
    }

    /**
     * Method that switches scene to one given as a parameter
     * so that user now has requested view.
     *
     * @param view - scene to which user wants to switch.
     */

    public static void switchTo(View view) {
        Parent root= BaseSwitcher.getRoot(view);
        scene.setRoot(root);
    }
}
