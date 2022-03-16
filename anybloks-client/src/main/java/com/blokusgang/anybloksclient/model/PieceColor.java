package com.blokusgang.anybloksclient.model;

import javafx.scene.paint.Color;

public enum PieceColor {
    RED,
    GREEN,
    YELLOW,
    BLUE;

    @Override
    public String toString() {
        return this.name();
    }

    public Color getColor() {
        switch (this) {
            case RED -> {
                return Color.RED;
            }
            case BLUE -> {
                return Color.BLUE;
            }
            case GREEN -> {
                return Color.GREEN;
            }
            case YELLOW -> {
                return Color.YELLOW;
            }
        }

        return Color.WHITE;
    }
}
