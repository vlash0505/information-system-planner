package mytimeorganizer.view_logic;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginViewInit extends Application {

    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(new Pane());
        SceneViewSwitcher.setScene(scene);
        SceneViewSwitcher.switchTo(View.LOGIN);

        stage.setTitle("Organizer");
        stage.getIcons().add(new Image("/assets/signup/logo.png"));
        stage.setMinWidth(650.0);
        stage.setMinHeight(550.0);
        stage.setScene(scene);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
