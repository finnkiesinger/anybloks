package db;

import models.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserRepository {
    public static User createUser(int id, String username) {
        Connection conn = DatabaseConnection.getConnection();
        try {
            Statement statement = conn.createStatement();
            statement.executeUpdate(
                    "insert into user (id, username, games, wins) values(" + id + ", '" + username + "', 0, 0)"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new User(username);
    }

    public static User find(String username) {
        Connection conn = DatabaseConnection.getConnection();
        User user = null;
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("select * from user where username = '" + username + "'");

            while (rs.next()) {
                user = new User(username, rs.getInt("games"), rs.getInt("wins"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
}
