package mytimeorganizer.persistance.DAO.tasks;

import mytimeorganizer.models.Task;
import mytimeorganizer.persistance.DAO.DAOException;

import java.time.LocalDate;
import java.util.List;

public interface TaskDAO {

    Long addNewTask(Task task) throws IllegalArgumentException, DAOException;

    void makeTaskCompleted(Long id);

    void makeTaskUncompleted(Long id);

    List<Task> findByDateAndUserID(LocalDate date, Long userId);
}
