package mytimeorganizer.persistance.DAO.users;

import mytimeorganizer.models.User;
import mytimeorganizer.persistance.DAO.DAOException;
import mytimeorganizer.persistance.DAO.DAOUtils;

import java.sql.*;

public class UserDAOJDBC implements UserDAO {

    //constant queries

    private static final String SQL_INSERT_USER_QUERY =
            "INSERT INTO users (email, username, password) VALUES (?, ?, ?)";

    private static final String SQL_USER_EXISTS_QUERY =
            "SELECT id FROM users WHERE username = ? AND password = ?";

    private static final String SQL_VALIDATE_REGISTRATION_QUERY =
            "SELECT * FROM users WHERE email = ? OR username = ?";


    private final DriverUserDAO driverUserDAO;

    public UserDAOJDBC(DriverUserDAO driverUserDAO) {
        this.driverUserDAO = driverUserDAO;
    }

    private User findByProperty(Object... values) throws DAOException {
        User user = null;

        try (
                Connection connection = driverUserDAO.getConnection();
                PreparedStatement statement = DAOUtils.prepareStatement(connection, UserDAOJDBC.SQL_VALIDATE_REGISTRATION_QUERY, false, values);
                ResultSet resultSet = statement.executeQuery()
        ) {
            if (resultSet.next()) {
                user = map(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }

        return user;
    }

    @Override
    public void addNewUser(User user) throws IllegalArgumentException, DAOException {
        if (user.getId() != null) {
            throw new IllegalArgumentException("[UserDAOJDBC] User is already created, the user ID is not null.");
        }

        Object[] values = {
                user.getEmail(),
                user.getUsername(),
                user.getPassword()
        };

        try (
                Connection connection = driverUserDAO.getConnection();
                PreparedStatement statement = DAOUtils.prepareStatement(connection, SQL_INSERT_USER_QUERY, true, values)
        ) {
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new DAOException("[UserDAOJDBC] Creating user failed, no rows affected.");
            }
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setId(generatedKeys.getLong(1));
                } else {
                    throw new DAOException("[UserDAOJDBC] Creating user failed, no generated key obtained.");
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public boolean registrationValidation(String email, String username) throws DAOException {
        return findByProperty(email, username) != null;
    }

    @Override
    public Long existsUser(String username, String password) throws DAOException {
        Object[] values = {
                username,
                password
        };
        Long id = null;
        try (
                Connection connection = driverUserDAO.getConnection();
                PreparedStatement statement = DAOUtils.prepareStatement(connection, SQL_USER_EXISTS_QUERY, false, values);
                ResultSet resultSet = statement.executeQuery()
        ) {
            if (resultSet.next()) {
                id = resultSet.getLong("id");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return id;
    }

    private static User map(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setEmail(resultSet.getString("email"));
        user.setUsername(resultSet.getString("username"));
        return user;
    }
}
