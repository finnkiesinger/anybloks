package com.blokusgang.anybloksclient;

import com.blokusgang.anybloksclient.Navigator;
import com.blokusgang.anybloksclient.model.Game;
import com.blokusgang.anybloksclient.model.GameMode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.io.IOException;

public class LocalMenuController {
    @FXML
    public void onPlayClassic(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Game game = new Game(GameMode.CLASSIC);
        Navigator.toGame(stage, game);
    }
}
