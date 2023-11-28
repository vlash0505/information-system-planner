package mytimeorganizer.visual_components;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import mytimeorganizer.controllers.custom_elements.PaneWithInputController;

import java.io.IOException;

public class PaneWithInput extends AnchorPane {

    PaneWithInputController controller;

    public PaneWithInput() {
        super();
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(PaneWithInput.class.getResource("/components/TempInputPaneView.fxml"));
            controller = new PaneWithInputController();
            fxmlLoader.setController(controller);
            Node view = fxmlLoader.load();
            this.getChildren().add(view);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public PaneWithInputController getController() {
        return controller;
    }
}
