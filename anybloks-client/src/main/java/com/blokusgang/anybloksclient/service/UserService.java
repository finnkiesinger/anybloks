package com.blokusgang.anybloksclient.service;

import com.blokusgang.anybloksclient.model.User;

public class UserService {
    private static UserService INSTANCE;

    private User user;

    private UserService() {}

    public void setUser(User user) {
        this.user = user;
    }
    public User getUser() {
        return user;
    }

    public static UserService getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new UserService();
        }

        return INSTANCE;
    }
}
