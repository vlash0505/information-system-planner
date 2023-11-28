package mytimeorganizer.persistance.DAO.goals;

import mytimeorganizer.persistance.DAO.DriverDAO;

public class DriverGoalDAO extends DriverDAO {

    public DriverGoalDAO(String url, String username, String password) {
        super(url, username, password);
    }

    public GoalDAO getGoalDAO() {
        return new GoalDAOJDBC(this);
    }
}
