package mytimeorganizer.persistance.DAO.users;

import mytimeorganizer.models.User;
import mytimeorganizer.persistance.DAO.DAOException;

public interface UserDAO {

    void addNewUser(User user) throws IllegalArgumentException, DAOException;

    boolean registrationValidation(String email, String username) throws DAOException;

    Long existsUser(String username, String password) throws DAOException;
}
