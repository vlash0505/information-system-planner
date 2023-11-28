package mytimeorganizer.controllers.custom_elements;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class PaneWithInputController implements Initializable {

    @FXML
    public TextField textField;

    @FXML
    public ImageView checkmarkView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public TextField getTextField() {
        return textField;
    }

    public ImageView getCheckmarkView() {
        return checkmarkView;
    }
}
