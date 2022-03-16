package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection connection;

    public static Connection getConnection() {
        return connection;
    }

    static {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:anybloks.db");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
