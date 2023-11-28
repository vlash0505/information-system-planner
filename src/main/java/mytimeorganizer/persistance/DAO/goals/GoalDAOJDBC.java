package mytimeorganizer.persistance.DAO.goals;

import mytimeorganizer.models.Goal;
import mytimeorganizer.persistance.DAO.DAOException;
import mytimeorganizer.persistance.DAO.DAOUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GoalDAOJDBC implements GoalDAO {

    //constant queries

    private static final String SQL_INSERT_GOAL_QUERY =
            "INSERT INTO goals (type, description, is_completed, user_id) VALUES (?, ?, false, ?)";

    private static final String SQL_SELECT_UNCOMPLETED_BY_TYPE_AND_USER_ID_ORDER_BY_ID_QUERY =
            "SELECT * FROM goals WHERE user_id = ? AND type = ? AND is_completed = false ORDER BY id";

    private static final String SQL_SELECT_COMPLETED_BY_TYPE_AND_USER_ID_ORDER_BY_ID_QUERY =
            "SELECT * FROM goals WHERE user_id = ? AND type = ? AND is_completed = true ORDER BY id";

    private static final String SQL_MAKE_GOAL_COMPLETED_QUERY =
            "UPDATE goals SET is_completed = true WHERE id = ?";

    private static final String SQL_MAKE_GOAL_UNCOMPLETED_QUERY =
            "UPDATE goals SET is_completed = false WHERE id = ?";


    private final DriverGoalDAO driverGoalDAO;



    public GoalDAOJDBC(DriverGoalDAO driverGoalDAO) {
        this.driverGoalDAO = driverGoalDAO;
    }


    @Override
    public List<Goal> findUncompletedByTypeAndUserId(Long id, String type) throws DAOException {
        Object[] values = {
                id,
                type
        };

        return findByParams(SQL_SELECT_UNCOMPLETED_BY_TYPE_AND_USER_ID_ORDER_BY_ID_QUERY, values);
    }

    @Override
    public List<Goal> findCompletedByTypeAndUserId(Long id, String type) {
        Object[] values = {
                id,
                type
        };

        return findByParams(SQL_SELECT_COMPLETED_BY_TYPE_AND_USER_ID_ORDER_BY_ID_QUERY, values);
    }

    public List<Goal> findByParams(String query, Object... values) {
        List<Goal> goals = new ArrayList<>();
        try (
                var connection = driverGoalDAO.getConnection();
                PreparedStatement statement = DAOUtils.prepareStatement(connection, query, true, values);
                ResultSet resultSet = statement.executeQuery();
        ) {
            while (resultSet.next()) {
                goals.add(map(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return goals;
    }

    @Override
    public Long addGoal(Goal goal) throws IllegalArgumentException, DAOException {
        if (goal.getId() != null) {
            throw new IllegalArgumentException("[GoalDAOJDBC] Goal is already created, the goal ID is not null.");
        }
        Long id = null;

        Object[] values = {
                goal.getType(),
                goal.getDescription(),
                Goal.USER_ID
        };

        try (
                Connection connection = driverGoalDAO.getConnection();
                PreparedStatement statement = DAOUtils.prepareStatement(connection, SQL_INSERT_GOAL_QUERY, true, values)
        ) {
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new DAOException("[GoalDAOJDBC] Creating goal failed, no rows affected.");
            }
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    goal.setId(generatedKeys.getLong(1));
                    id = goal.getId();
                } else {
                    throw new DAOException("[GoalDAOJDBC] Creating goal failed, no generated key obtained.");
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return id;
    }

    @Override
    public void makeGoalCompleted(Long id) {
        alterGoal(id, SQL_MAKE_GOAL_COMPLETED_QUERY);
    }

    @Override
    public void makeGoalUncompleted(Long id) {
        alterGoal(id, SQL_MAKE_GOAL_UNCOMPLETED_QUERY);
    }

    public void alterGoal(Long id, String query) {
        try (
                Connection connection = driverGoalDAO.getConnection();
                PreparedStatement statement = DAOUtils.prepareStatement(connection, query, true, id);
        ) {
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new DAOException("[GoalDAOJDBC] Changing goal failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    private static Goal map(ResultSet resultSet) throws SQLException {
        Goal goal = new Goal();
        goal.setId(resultSet.getLong("id"));
        goal.setType(resultSet.getString("type"));
        goal.setDescription(resultSet.getString("description"));
        return goal;
    }
}
