package mytimeorganizer.controllers.timer;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import javax.swing.*;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

public class TimerController implements Initializable {

    @FXML
    private Text textArea;

    private Timer timer;
    private DecimalFormat decimalFormat;

    private int minutes;
    private int seconds;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initCountdownTimer();
        decimalFormat = new DecimalFormat("00");
        minutes = 1;
        seconds = 0;
        Font font = new Font("Arial", 45);
        textArea.setFont(font);
        textArea.setOpacity(0.85);
        setTimeAsText();
    }

    public void setTimeAsText() {
        textArea.setText(
                decimalFormat.format(minutes) + ":" + decimalFormat.format(seconds)
        );
    }

    public void initCountdownTimer() {
        timer = new Timer(1000, e -> {
            setTimeAsText();
            if(seconds == -1) {
                seconds = 59;
                minutes--;
                setTimeAsText();
            }
            seconds--;
            if(minutes <= 0 && seconds <= 0) {
                setTimeAsText();
                showNotification("Timer Ended", "Your countdown timer has finished.");
                timer.stop();
            }
        });
    }

    public void onStartTimerButton() {
        timer.start();
    }

    public void onStopTimerButton() {
        timer.stop();
    }

    public void setMinutes(int selectedMinutes) {
        minutes = selectedMinutes;
        seconds = 0;
        setTimeAsText();
    }

    public void onResetTimerButton() {
        timer.stop();
        minutes = 1;
        seconds = 0;
        setTimeAsText();
    }

    private void showNotification(String title, String message) {
        Platform.runLater(() -> {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        });
    }


    @FXML
    public void setTimerAtOneMinute() {
        setMinutes(1);
    }

    @FXML
    public void setTimerAtFiveMinute() {
        setMinutes(5);
    }

    @FXML
    public void setTimerAtTenMinutes() {
        setMinutes(10);
    }

    @FXML
    public void setTimerAtTwentyFiveMinutes() {
        setMinutes(25);
    }
}
