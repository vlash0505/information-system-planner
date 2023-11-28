package mytimeorganizer.persistance.DAO.notes;

import mytimeorganizer.models.Note;

import java.time.LocalDate;

public interface NoteDAO {

    Note findByDateAndUserID(Long userId, LocalDate date);

    void addNote(Note note);

    void updateNoteDescription(String description, Long userId, LocalDate date);

    boolean hasNoteAtDate(Long userId, LocalDate localDate);
}
