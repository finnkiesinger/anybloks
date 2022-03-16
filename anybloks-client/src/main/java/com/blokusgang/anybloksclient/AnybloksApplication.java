package com.blokusgang.anybloksclient;

import com.blokusgang.anybloksclient.service.UserService;
import com.blokusgang.anybloksclient.utils.TokenStorage;
import com.blokusgang.anybloksclient.model.User;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class AnybloksApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        String token = TokenStorage.readToken();
        if (token != null) {
            User user = RestClient.getUser(token);
            if (user != null) {
                UserService.getInstance().setUser(user);
                Navigator.toMain(stage);
                return;
            }
        }

        Navigator.toAuth(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}