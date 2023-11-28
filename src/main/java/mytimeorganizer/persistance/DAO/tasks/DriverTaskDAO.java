package mytimeorganizer.persistance.DAO.tasks;

import mytimeorganizer.persistance.DAO.DriverDAO;

public class DriverTaskDAO extends DriverDAO {

    public DriverTaskDAO(String url, String username, String password) {
        super(url, username, password);
    }

    public TaskDAO getTaskDAO() {
        return new TaskDAOJDBC(this);
    }
}
