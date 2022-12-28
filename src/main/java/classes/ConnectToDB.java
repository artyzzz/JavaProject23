package classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectToDB {
    static final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/players";
    static final String USER = "postgres";
    static final String PASS = "123456";

    static Connection connect = null;

    public ConnectToDB() {
    }

    public static Connection getConnect() {
        if (connect != null) {
            return connect;
        }

        try {
            connect = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return connect;
    }
}
