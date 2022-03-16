package com.blokusgang.anybloksclient;

import com.blokusgang.anybloksclient.Navigator;
import com.blokusgang.anybloksclient.utils.TokenStorage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {

    @FXML
    private Button onlineButton;

    @FXML
    private AnchorPane container;

    @FXML
    private Button localButton;

    @FXML
    private Button settingsButton;

    @FXML
    public void initialize() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("online-screen.fxml"));
        container.getChildren().add(root);
    }

    @FXML
    public void onMenuClick(ActionEvent event) {
        String id = ((Node)event.getSource()).getId();
        switch (id) {
            case "online" -> switchToOnline();
            case "local" -> switchToLocal();
            case "settings" -> switchToSettings();
        }
    }

    private void switchToOnline() {
        onlineButton.getStyleClass().add("active");
        localButton.getStyleClass().remove("active");
        settingsButton.getStyleClass().remove("active");
        container.getChildren().removeIf(e -> true);
        try {
            Parent root = FXMLLoader.load(getClass().getResource("online-screen.fxml"));
            container.getChildren().add(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void switchToLocal() {
        onlineButton.getStyleClass().remove("active");
        localButton.getStyleClass().add("active");
        settingsButton.getStyleClass().remove("active");
        container.getChildren().removeIf(e -> true);
        try {
            Parent root = FXMLLoader.load(getClass().getResource("local-screen.fxml"));
            container.getChildren().add(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void switchToSettings() {
        onlineButton.getStyleClass().remove("active");
        localButton.getStyleClass().remove("active");
        settingsButton.getStyleClass().add("active");
        container.getChildren().removeIf(e -> true);
        try {
            Parent root = FXMLLoader.load(getClass().getResource("settings-screen.fxml"));
            container.getChildren().add(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void logout(ActionEvent event) throws IOException {
        TokenStorage.clearToken();
        // Navigate to new menu
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Navigator.toAuth(stage);
    }
}
