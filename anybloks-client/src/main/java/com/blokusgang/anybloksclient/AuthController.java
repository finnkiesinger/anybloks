package com.blokusgang.anybloksclient;

import com.blokusgang.anybloksclient.Navigator;
import com.blokusgang.anybloksclient.RestClient;
import com.blokusgang.anybloksclient.model.User;
import com.blokusgang.anybloksclient.service.UserService;
import com.blokusgang.anybloksclient.utils.Validator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;

public class AuthController {

    @FXML
    private Label infoLabel;

    @FXML
    private TextField usernameTextfield;

    @FXML
    private PasswordField passwordTextfield;

    @FXML
    private Button loginButton;

    private void createError(String error) {
        infoLabel.getStyleClass().remove("status");
        infoLabel.getStyleClass().add("error");
        infoLabel.setText(error);
    }

    private void createStatus(String status) {
        infoLabel.getStyleClass().remove("error");
        infoLabel.getStyleClass().add("status");
        infoLabel.setText(status);
    }

    private void resolveError() {
        infoLabel.getStyleClass().remove("status");
        infoLabel.getStyleClass().add("error");
        infoLabel.setText("");
    }

    public void onInputChanged() {
        loginButton.setDisable(usernameTextfield.getText().isEmpty() || passwordTextfield.getText().isEmpty());
    }

    @FXML
    public void onLoginClick(ActionEvent event) throws IOException {
        resolveError();
        String username = usernameTextfield.getText();
        String password = passwordTextfield.getText();
        if (!Validator.validPassword(password) || !(username.length() > 6)) {
            createError("Not a valid username or password!");
        } else {
            createStatus("Logging you in...");
            // Password gets hashed on client side and on server side
            // Password can't be leaked when sent to server
            String hashedPassword = DigestUtils.sha256Hex(password);
            User user = RestClient.login(username, hashedPassword);
            if (user == null) {
                createError("No user found with these credentials");
                return;
            }
            // Navigate to new menu
            UserService.getInstance().setUser(user);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Navigator.toMain(stage);
        }
    }
}
