package mytimeorganizer.controllers.journaling;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;

import mytimeorganizer.models.Note;
import mytimeorganizer.persistance.DAO.PropertiesLoader;
import mytimeorganizer.persistance.DAO.notes.DriverNoteDAO;
import mytimeorganizer.persistance.DAO.notes.NoteDAO;

import java.time.LocalDate;

public class JournalingController {

    @FXML
    private DatePicker datePicker;

    @FXML
    private TextArea textArea;

    private NoteDAO noteDAO;

    @FXML
    public void initialize() {
        DriverNoteDAO driverNoteDAO = PropertiesLoader.getDriverNoteDAOInstance();
        noteDAO = driverNoteDAO.getNoteDAO();

        datePicker.valueProperty().addListener(
                (o, oldDate, date) -> textArea.setText(
                        noteDAO.findByDateAndUserID(Note.USER_ID, date).getDescription()
                )
        );

        datePicker.setValue(LocalDate.now());
    }

    public void onUpdateNotesButton() {
        Note note = new Note();
        note.setDate(
                datePicker.getValue()
        );
        note.setDescription(textArea.getText());
        if (noteDAO.hasNoteAtDate(Note.USER_ID, datePicker.getValue())) {
            if (note.getDescription().length() > 0 && note.getDescription().length() < 3000) {
                noteDAO.updateNoteDescription(note.getDescription(), Note.USER_ID, datePicker.getValue());
            }
        } else {
            noteDAO.addNote(note);
        }
    }
}
