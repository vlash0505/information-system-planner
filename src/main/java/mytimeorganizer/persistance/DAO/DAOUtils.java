package mytimeorganizer.persistance.DAO;

import java.sql.*;
import java.time.LocalDate;

public final class DAOUtils {

    public static void setValues(PreparedStatement statement, Object... values) throws SQLException {
        for (int i = 0; i < values.length; i++) {
            statement.setObject(i + 1, values[i]);
        }
    }

    public static PreparedStatement prepareStatement
            (Connection connection, String sql, boolean returnGeneratedKeys, Object... values)
            throws SQLException {
        PreparedStatement statement = connection.prepareStatement(sql,
                returnGeneratedKeys ? Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS);
        setValues(statement, values);
        return statement;
    }

    public static Date toSqlDate(LocalDate date) {
        return Date.valueOf(date);
    }
}
