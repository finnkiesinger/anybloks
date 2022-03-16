package com.blokusgang.anybloksclient;

import com.blokusgang.anybloksclient.model.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class GameController {
    private static final PieceColor[] colors = PieceColor.values();
    @FXML
    private ListView<Piece> piecesListView;

    @FXML
    private Label playerLabel;

    @FXML
    private GridPane board;

    @FXML
    private StackPane layer;

    private Game game;

    private Piece selectedPiece;

    private int currentRotation;
    private boolean mirrored;

    @FXML
    public void initialize() {
        piecesListView
                .getSelectionModel()
                .selectedItemProperty()
                .addListener(
                        (observableValue, oldPiece, newPiece) -> selectedPiece = newPiece
                );
    }

    public void setGame(Game game) {
        this.game = game;
        this.playerLabel.setText(colors[game.getActivePlayer()].name());
        List<Piece> pieces = game.getPieces()[game.getActivePlayer()];
        Collections.sort(pieces);
        this.piecesListView.getItems().addAll(pieces);
        currentRotation = 0;
        mirrored = false;
        showPossibleNodes();
        selectedPiece = pieces.get(0);

        piecesListView.setCellFactory(pieceListView -> new ListCell<>(){
            @Override
            protected void updateItem(Piece piece, boolean empty) {
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else if (piece != null) {
                    setText(null);
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("piece.fxml"));
                    Parent root = null;
                    try {
                        root = loader.load();
                        root.setOnMouseClicked(mouseEvent -> piecesListView.getSelectionModel().select(piece));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    PieceListCell cell = loader.getController();
                    cell.setPiece(piece);
                    setGraphic(root);
                } else {
                    setGraphic(null);
                    setText(null);
                }
            }
        });
        updateBoard();
        piecesListView.getSelectionModel().select(0);
    }

    public void updateBoard() {
        for (int i = 0; i < game.getBoardSize().x; i++) {
            for (int j = 0; j < game.getBoardSize().y; j++) {
                HBox box = new HBox();
                box.setPrefSize(31, 31);
                box.setMinSize(31, 31);
                box.setMaxSize(31, 31);
                if (game.getBoard()[i][j] == null) {
                    box.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
                } else {
                    PieceColor color = game.getBoard()[i][j];
                    box.setBackground(new Background(new BackgroundFill(color.getColor(), null, null)));
                }
                box.setBorder(
                        new Border(
                                new BorderStroke(
                                        Color.LIGHTGRAY,
                                        BorderStrokeStyle.SOLID,
                                        null,
                                        new BorderWidths(1.0)
                                )
                        )
                );
                board.add(box, i, j);
            }
        }
    }

    public void place(BoardNode node) {
        System.out.println("Placed " + selectedPiece);
        game.placePiece(selectedPiece, 0, node, false);
        updateBoard();
        game.next();
        this.playerLabel.setText(colors[game.getActivePlayer()].name());
        this.piecesListView.getItems().removeIf(p -> true);
        this.piecesListView.getItems().addAll(game.getPieces()[game.getActivePlayer()]);
    }

    public void showPossibleNodes() {
        for(BoardNode node: game.getPossibleNodes(selectedPiece)) {
            HBox box = new HBox();
            double nodeSize = 15.0;
            box.setPrefSize(nodeSize, nodeSize);
            box.setMaxSize(nodeSize, nodeSize);
            box.setStyle("-fx-background-color: #2cb22c; -fx-background-radius: " + nodeSize/2);
            box.setTranslateX(-nodeSize/2 + 1 + node.visualX * 31);
            box.setTranslateY(-nodeSize/2 + 1 + node.visualY * 31);
            box.setOnMouseClicked(mouseEvent -> {
                place(node);
            });
            layer.getChildren().add(box);
        }
    }
}
