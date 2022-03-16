package com.blokusgang.anybloksclient;

import com.blokusgang.anybloksclient.model.Game;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Navigator {
    public static void toAuth(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(Navigator.class.getResource("auth-screen.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("AnyBloks - Login");
        stage.setResizable(false);
        stage.show();
    }
    public static void toMain(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(Navigator.class.getResource("menu-screen.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("AnyBloks by Blokus Gang");
        stage.setResizable(false);
        stage.show();
    }

    public static void toGame(Stage stage, Game game) throws IOException {
        FXMLLoader loader = new FXMLLoader(Navigator.class.getResource("game-screen.fxml"));
        Parent root = loader.load();

        GameController controller = loader.getController();
        controller.setGame(game);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("AnyBloks by Blokus Gang");
        stage.setResizable(true);
        stage.setMinWidth(1000);
        stage.setMinHeight(700);
        stage.show();
    }
}
