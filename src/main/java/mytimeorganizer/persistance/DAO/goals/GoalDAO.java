package mytimeorganizer.persistance.DAO.goals;

import mytimeorganizer.models.Goal;
import mytimeorganizer.persistance.DAO.DAOException;

import java.util.List;

public interface GoalDAO {

    List<Goal> findUncompletedByTypeAndUserId(Long id, String type) throws DAOException;
    List<Goal> findCompletedByTypeAndUserId(Long id, String type) throws DAOException;

    void makeGoalUncompleted(Long id) throws DAOException;
    void makeGoalCompleted(Long Id) throws DAOException;

    Long addGoal(Goal goal) throws IllegalArgumentException, DAOException;
}
