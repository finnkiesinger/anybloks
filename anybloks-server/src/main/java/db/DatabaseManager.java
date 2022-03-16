package db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {
    public static void reset() {
        Connection conn = DatabaseConnection.getConnection();
        try {
            Statement statement = conn.createStatement();
            statement.executeUpdate("drop table if exists user");
            statement.executeUpdate("drop table if exists auth_user");
            statement.executeUpdate("create table auth_user (" +
                    "id integer primary key autoincrement," +
                    "username text," +
                    "password text" +
                    ")");
            statement.executeUpdate("create table user (" +
                    "id integer primary key," +
                    "username text," +
                    "games integer," +
                    "wins integer" +
                    ")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        reset();
    }
}
