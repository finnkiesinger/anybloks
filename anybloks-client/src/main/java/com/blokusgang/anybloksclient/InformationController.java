package com.blokusgang.anybloksclient;

import com.blokusgang.anybloksclient.Navigator;
import com.blokusgang.anybloksclient.service.UserService;
import com.blokusgang.anybloksclient.utils.TokenStorage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.IOException;

public class InformationController {
   @FXML
   private Label usernameLabel;

   @FXML
   public void logout(ActionEvent event) throws IOException {
      TokenStorage.clearToken();
      Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
      Navigator.toAuth(stage);
   }

   @FXML
    public void initialize() {
       usernameLabel.setText(UserService.getInstance().getUser().getUsername());
   }
}
