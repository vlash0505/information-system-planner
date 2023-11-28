package mytimeorganizer.view_logic;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class BaseSwitcher {
    //cache mechanism
    private static final Map<View, Parent> cache = new HashMap<>();

    public static Parent getRoot(View view) {
        Parent root = new Pane();
        try {
            //if we already loaded the fxml file before,
            //pick it from cache.
            if (cache.containsKey(view)) {
                root = cache.get(view);
            } else {
                root = FXMLLoader.load(
                        Objects.requireNonNull(SceneViewSwitcher.class.getResource(view.getFilename()))
                );
                //we want to keep up-to-date plan view with home view
                if(!view.getFilename().equals("/views/plan/planView.fxml") && !view.getFilename().equals("/views/startView.fxml")) {
                    cache.put(view, root);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return root;
    }

    public static void clearCache() {
        cache.clear();
    }
}
