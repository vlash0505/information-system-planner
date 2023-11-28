package mytimeorganizer.persistance.DAO.notes;

import mytimeorganizer.models.Goal;
import mytimeorganizer.models.Note;
import mytimeorganizer.persistance.DAO.DAOException;
import mytimeorganizer.persistance.DAO.DAOUtils;

import java.sql.*;
import java.time.LocalDate;

public class NoteDAOJDBC implements NoteDAO {

    //constant queries

    private static final String SQL_INSERT_NOTE_QUERY =
            "INSERT INTO notes (date, description, user_id) VALUES (?, ?, ?)";

    private static final String SQL_SELECT_NOTES_BY_USER_ID_AND_DATE_QUERY =
            "SELECT * FROM notes WHERE user_id = ? AND date = ?";

    private static final String SQL_UPDATE_NOTE_DESCRIPTION_BY_USER_ID_AND_DATE_QUERY =
            "UPDATE notes SET description = ? WHERE user_id = ? AND date = ?";



    private final DriverNoteDAO driverNoteDAO;

    public NoteDAOJDBC(DriverNoteDAO driverNoteDAO) {
        this.driverNoteDAO = driverNoteDAO;
    }

    @Override
    public Note findByDateAndUserID(Long userId, LocalDate date) {
        Note note = new Note();
        try (
                var connection = driverNoteDAO.getConnection();
                PreparedStatement statement = DAOUtils.prepareStatement(connection, SQL_SELECT_NOTES_BY_USER_ID_AND_DATE_QUERY, true, userId, DAOUtils.toSqlDate(date));
                ResultSet resultSet = statement.executeQuery()
        ) {
            if(resultSet.next()) {
                note = map(resultSet);
            } else {
                note.setDescription("");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return note;
    }

    @Override
    public void addNote(Note note) {
        if (note.getId() != null) {
            throw new IllegalArgumentException("[NoteDAOJDBC] Task is already created, the task ID is not null.");
        }

        Object[] values = {
                note.getDate(),
                note.getDescription(),
                Goal.USER_ID
        };

        try (
                Connection connection = driverNoteDAO.getConnection();
                PreparedStatement statement = DAOUtils.prepareStatement(connection, SQL_INSERT_NOTE_QUERY, true, values)
        ) {
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new DAOException("[NoteDAOJDBC] Creating note failed, no rows affected.");
            }
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    note.setId(generatedKeys.getLong(1));
                } else {
                    throw new DAOException("[NoteDAOJDBC] Creating note failed, no generated key obtained.");
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void updateNoteDescription(String description, Long userId, LocalDate date) {
        try (
                Connection connection = driverNoteDAO.getConnection();
                PreparedStatement statement = DAOUtils.prepareStatement(connection, SQL_UPDATE_NOTE_DESCRIPTION_BY_USER_ID_AND_DATE_QUERY, true, description, userId, date)
        ) {
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new DAOException("[NoteDAOJDBC] Changing note failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public boolean hasNoteAtDate(Long userId, LocalDate localDate) {
        boolean noteExists = false;
        try (
                Connection connection = driverNoteDAO.getConnection();
                PreparedStatement statement = DAOUtils.prepareStatement(connection,SQL_SELECT_NOTES_BY_USER_ID_AND_DATE_QUERY, false, userId, localDate);
                ResultSet resultSet = statement.executeQuery()
        ) {
            if(resultSet.next()) {
                noteExists = true;
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return noteExists;
    }

    private static Note map(ResultSet resultSet) throws SQLException {
        Note note = new Note();
        note.setId(resultSet.getLong("id"));
        note.setDate(Date.valueOf(String.valueOf(resultSet.getDate("date"))).toLocalDate());
        note.setDescription(resultSet.getString("description"));
        return note;
    }
}
