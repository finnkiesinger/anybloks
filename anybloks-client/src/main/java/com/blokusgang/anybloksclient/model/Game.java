package com.blokusgang.anybloksclient.model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Game {
    private GameMode gameMode;
    private PieceColor[][] board;
    private List<Piece>[] pieces;
    private int activePlayer;
    private int playerCount;
    private ArrayList<Piece>[] placedPieces;

    public Game(GameMode gameMode) {
        this.gameMode = gameMode;
        this.board = new PieceColor[20][20];
        initPieces();
        this.activePlayer = 0;
        this.playerCount = 4;
    }

    public PieceColor[][] getBoard() {
        return board;
    }

    private void initPieces() {
        if (this.gameMode == GameMode.CLASSIC) {
            this.pieces = new ArrayList[]{
                    new ArrayList<>(),
                    new ArrayList<>(),
                    new ArrayList<>(),
                    new ArrayList<>(),
            };
            this.placedPieces = new ArrayList[]{
                    new ArrayList<>(),
                    new ArrayList<>(),
                    new ArrayList<>(),
                    new ArrayList<>(),
            };
            for (PieceColor color: PieceColor.values()) {
                for (PieceType type: PieceType.values()) {
                    pieces[color.ordinal()].add(new Piece(type, color));
                }
            }
        }
    }

    public List<Piece>[] getPieces() {
        return this.pieces;
    }

    public List<BoardNode> getPossibleNodes(Piece piece) {
        List<BoardNode> possibleNodes = new ArrayList<>();

        if (placedPieces[getActivePlayer()].size() == 0) {
            if (board[0][0] == null) {
                possibleNodes.add(new BoardNode(0, 0));
            }
            if (board[board.length-1][board.length-1] == null) {
                possibleNodes.add(new BoardNode(board.length-1, board.length-1, board.length, board.length));
            }
            if (board[0][board.length-1] == null) {
                possibleNodes.add(new BoardNode(0, board.length-1, 0, board.length));
            }
            if (board[board.length-1][0] == null) {
                possibleNodes.add(new BoardNode(board.length-1, 0, board.length, 0));
            }
        }

        for (int i = 0; i < getBoardSize().x; i++) {
            for (int j = 0; j < getBoardSize().y; j++) {
                if (board[i][j] != null) {
                    if (board[i][j].ordinal() == getActivePlayer()) {
                        // Check Edges
                        if (i < getBoardSize().x-1) {
                            // Check Column to the right
                            if (j > 0) {
                                // Check cell top-right
                                if (isPossible(i+1, j-1)) {
                                    possibleNodes.add(new BoardNode(i+1, j-1, i+1, j));
                                }
                            }
                            if (j < getBoardSize().y-1) {
                                // Check cell bottom-right
                                if(isPossible(i+1, j+1)) {
                                    possibleNodes.add(new BoardNode(i+1, j+1));
                                }
                            }
                        }
                        if (i > 0) {
                            // Check Column to the left
                            if (j > 0) {
                                // Check cell top-left
                                if (isPossible(i-1, j-1)) {
                                    possibleNodes.add(new BoardNode(i-1, j-1, i, j));
                                }
                            }
                            if (j < getBoardSize().y-1) {
                                // Check cell bottom-left
                                if(isPossible(i-1, j+1)) {
                                    possibleNodes.add(new BoardNode(i-1, j+1, i, j+1));
                                }
                            }
                        }
                    }
                }
            }
        }

        return possibleNodes;
    }

    private boolean isPossible(int x, int y) {
        return x < 0 && y < 0;
    }

    public void next() {
        this.activePlayer++;
        if (activePlayer == playerCount) {
            this.activePlayer = 0;
        }
    }

    public int getActivePlayer() {
        return this.activePlayer;
    }

    public void placePiece(Piece piece, int rotation, BoardNode position, boolean mirrored) {
        int[][] positions = piece.getPositions();
        if (mirrored) {
            for (int[] p: positions) {
                p[0] = piece.getWidth() - p[0];
            }
        }
        while (rotation > 0) {
            rotateBy90Degrees(piece, positions, rotation % 2 == 0);
            rotation--;
        }

        for (int[] p: positions) {
            board[position.x + p[0]][position.y + p[1]] = PieceColor.values()[getActivePlayer()];
        }
        placedPieces[activePlayer].add(piece);
        pieces[activePlayer].remove(piece);
    }

    public void rotateBy90Degrees(Piece piece, int[][] positions, boolean switched) {
        int width = switched ? piece.getHeight() : piece.getWidth();
        for (int[] p: positions) {
            int temp = p[0];
            p[0] = p[1];
            p[1] = -temp + (width-1);
        }
    }

    public Point getBoardSize() {
        return new Point(20, 20);
    }

    public void increasePlayerCount() {
        playerCount++;
    }

    public void decreasePlayerCount() {
        playerCount--;
    }
}
