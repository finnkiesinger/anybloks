package com.blokusgang.anybloksclient;

import com.blokusgang.anybloksclient.model.Piece;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PieceListCell {
    private Piece piece;

    @FXML
    private ImageView pieceView;

    public void setPiece(Piece piece) {
        this.piece = piece;
        String path = "pieces/" + piece.getColor() + "_" + piece.getType() + ".png";
        Image image = new Image(String.valueOf(getClass().getResource(path)));
        pieceView.setFitHeight(image.getHeight() * 128 / 640);
        pieceView.setFitWidth(image.getWidth() * 128 / 512);
        pieceView.setImage(image);
    }
}
