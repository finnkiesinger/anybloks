package models;

import java.util.Objects;

public class AuthUser {
    private String username;
    private String password;

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthUser authUser = (AuthUser) o;
        return username.equals(authUser.username) && password.equals(authUser.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }

    public AuthUser(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
