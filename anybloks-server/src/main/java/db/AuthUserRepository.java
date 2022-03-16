package db;

import models.AuthUser;

import java.sql.*;

public class AuthUserRepository {
    public static AuthUser find(String username) {
        Connection conn = DatabaseConnection.getConnection();
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("select * from auth_user where username = '" + username + "'");
            AuthUser user = null;
            while (rs.next()) {
                user = new AuthUser(rs.getString("username"), rs.getString("password"));
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static int create(AuthUser user) {
        Connection conn = DatabaseConnection.getConnection();
        try {
            PreparedStatement statement = conn.prepareStatement("insert into auth_user (username, password) " +
                    "values('" + user.getUsername() + "', '"+ user.getPassword() + "')", Statement.RETURN_GENERATED_KEYS);
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            int key = 0;
            while (rs.next()) {
                key = rs.getInt(1);
            }
            return key;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
