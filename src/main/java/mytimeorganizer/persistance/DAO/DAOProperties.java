package mytimeorganizer.persistance.DAO;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DAOProperties {

    //Constants---------------------------------------------------------------------------------------------------------
    private static final String PROPERTIES_FILE = "dao.properties";
    private static final Properties PROPERTIES = new Properties();

    static {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream propertiesFile = classLoader.getResourceAsStream(PROPERTIES_FILE);

        if (propertiesFile == null) {
            throw new DAOConfigurationException(
                    "[DAOProperties] Properties file '" + PROPERTIES_FILE + "' is missing in classpath."
            );
        }

        try {
            PROPERTIES.load(propertiesFile);
        } catch (IOException e) {
            throw new DAOConfigurationException(
                    "[DAOProperties] Cannot load properties file '" + PROPERTIES_FILE + "'.", e
            );
        }
    }

    private final String specificKey;

    // Constructors -------------------------------------------------------------------------------

    public DAOProperties(String specificKey) throws DAOConfigurationException {
        this.specificKey = specificKey;
    }

    // Actions ------------------------------------------------------------------------------------

    public String getProperty(String key, boolean mandatory) throws DAOConfigurationException {
        String fullKey = specificKey + "." + key;
        String property = PROPERTIES.getProperty(fullKey);

        if (property == null || property.trim().length() == 0) {
            if (mandatory) {
                throw new DAOConfigurationException(
                        "[DAOProperties] Required property '" + fullKey + "'"
                        + " is missing in properties file '" + PROPERTIES_FILE + "'."
                );
            } else {
                property = null;
            }
        }
        return property;
    }
}
