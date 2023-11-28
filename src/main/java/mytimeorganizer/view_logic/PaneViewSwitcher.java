package mytimeorganizer.view_logic;

import javafx.scene.Parent;
import javafx.scene.layout.Pane;

public class PaneViewSwitcher {
    private static Pane pane;

    /**
     * Setter for pane.
     *
     * @param node scene to be set as one to
     *              which user will be moved.
     */

    public static void setPane(Pane node) {
        PaneViewSwitcher.pane = node;
    }

    /**
     * Method that switches scene to one given as a parameter
     * so that user now has requested view.
     *
     * @param view - scene to which user wants to switch.
     */

    public static void switchTo(View view) {
        Parent root = BaseSwitcher.getRoot(view);
        if(pane.getChildren().contains(root)) return;
        pane.getChildren().clear();
        pane.getChildren().addAll(root);
    }
}
