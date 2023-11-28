package mytimeorganizer.persistance.DAO;

import mytimeorganizer.persistance.DAO.goals.DriverGoalDAO;
import mytimeorganizer.persistance.DAO.notes.DriverNoteDAO;
import mytimeorganizer.persistance.DAO.tasks.DriverTaskDAO;
import mytimeorganizer.persistance.DAO.users.DriverUserDAO;

public class PropertiesLoader {
    private static final String PROPERTY_URL = "url";
    private static final String PROPERTY_DRIVER = "driver";
    private static final String PROPERTY_USERNAME = "username";
    private static final String PROPERTY_PASSWORD = "password";

    public static String getUrlProperty(DAOProperties properties) {
        return properties.getProperty(PROPERTY_URL, true);
    }

    public static String getPasswordProperty(DAOProperties properties) {
        return properties.getProperty(PROPERTY_PASSWORD, true);
    }

    public static String getUsernameProperty(DAOProperties properties) {
        return properties.getProperty(PROPERTY_USERNAME, true);
    }

    public static DriverUserDAO getDriverUserDAOInstance() {
        DAOProperties properties = new DAOProperties("javabase.jdbc");
        return new DriverUserDAO(
                getUrlProperty(properties),
                getUsernameProperty(properties),
                getPasswordProperty(properties)
        );
    }

    public static DriverGoalDAO getDriverGoalDAOInstance() {
        DAOProperties properties = new DAOProperties("javabase.jdbc");
        return new DriverGoalDAO(
                getUrlProperty(properties),
                getUsernameProperty(properties),
                getPasswordProperty(properties)
        );
    }

    public static DriverTaskDAO getDriverTaskDAOInstance() {
        DAOProperties properties = new DAOProperties("javabase.jdbc");
        return new DriverTaskDAO(
                getUrlProperty(properties),
                getUsernameProperty(properties),
                getPasswordProperty(properties)
        );
    }

    public static DriverNoteDAO getDriverNoteDAOInstance() {
        DAOProperties properties = new DAOProperties("javabase.jdbc");
        return new DriverNoteDAO(
                getUrlProperty(properties),
                getUsernameProperty(properties),
                getPasswordProperty(properties)
        );
    }
}
