package com.blokusgang.anybloksclient.model;

public class Piece implements Comparable<Piece> {
    private final PieceColor color;
    private final PieceType type;
    private final int value;
    private int[][] positions;
    private int width;
    private int height;

    public Piece(PieceType type, PieceColor color) {
        this.type = type;
        this.color = color;
        if (type.name().contains("ONE")) {
            this.value = 1;
        } else if (type.name().contains("TWO")) {
            this.value = 2;
        } else if (type.name().contains("THREE")) {
            this.value = 3;
        } else if (type.name().contains("FOUR")) {
            this.value = 4;
        } else {
            this.value = 5;
        }
        switch (type) {
            case P -> {
                this.positions = new int[][]{{0, 0}, {1, 0}, {1, 0}, {1, 1}, {2, 1}};
                this.width = 2;
                this.height = 3;
            }
            case T -> {
                this.positions = new int[][]{{0, 0}, {1, 0}, {2, 0}, {1, 1}, {2, 1}};
                this.width = 3;
                this.height = 3;
            }
            case F -> {
                this.positions = new int[][]{{1, 0}, {0, 1}, {1, 1}, {2, 0}, {2, 1}};
                this.width = 3;
                this.height = 3;
            }
            case I -> {
                this.positions = new int[][]{{0, 0}, {0, 1}, {0, 2}, {0, 3}, {0, 4}};
                this.width = 1;
                this.height = 5;
            }
            case L -> {
                this.positions = new int[][]{{0, 0}, {0, 1}, {0, 2}, {0, 3}, {1, 3}};
                this.width = 2;
                this.height = 4;
            }
            case N -> {
                this.positions = new int[][]{{1, 0}, {0, 1}, {1, 1}, {0, 2}, {0, 3}};
                this.width = 2;
                this.height = 4;
            }
            case W -> {
                this.positions = new int[][]{{2, 0}, {2, 1}, {1, 1}, {1, 2}, {0, 2}};
                this.width = 3;
                this.height = 3;
            }
            case U -> {
                this.positions = new int[][]{{0, 0}, {0, 1}, {1, 1}, {2, 1}, {2, 0}};
                this.width = 3;
                this.height = 2;
            }
            case X -> {
                this.positions = new int[][]{{1, 0}, {1, 1}, {0, 1}, {2, 1}, {1, 2}};
                this.width = 3;
                this.height = 3;
            }
            case V -> {
                this.positions = new int[][]{{2, 0}, {2, 1}, {2, 2}, {1, 2}, {0, 2}};
                this.width = 3;
                this.height = 3;
            }
            case Z -> {
                this.positions = new int[][]{{0, 0}, {1, 0}, {1, 1}, {1, 2}, {2, 2}};
                this.width = 3;
                this.height = 3;
            }
            case Y -> {
                this.positions = new int[][]{{1, 0}, {1, 1}, {0, 1}, {1, 2}, {2, 3}};
                this.width = 2;
                this.height = 4;
            }
            case ONE -> {
                this.positions = new int[][]{{0, 0}};
                this.width = 1;
                this.height = 1;
            }
            case TWO -> {
                this.positions = new int[][]{{0, 0}, {1, 0}};
                this.width = 2;
                this.height = 1;
            }
            case THREE_I -> {
                this.positions = new int[][]{{0, 0}, {1, 0}, {2, 0}};
                this.width = 3;
                this.height = 1;
            }
            case THREE_L -> {
                this.positions = new int[][]{{0, 1}, {1, 1}, {0, 1}};
                this.width = 3;
                this.height = 1;
            }
            case FOUR_I -> {
                this.positions = new int[][]{{0, 0}, {1, 0}, {2, 0}, {3, 0}};
                this.width = 4;
                this.height = 1;
            }
            case FOUR_L -> {
                this.positions = new int[][]{{0, 1}, {1, 1}, {2, 1}, {2, 0}};
                this.width = 3;
                this.height = 2;
            }
            case FOUR_T -> {
                this.positions = new int[][]{{0, 0}, {1, 0}, {2, 0}, {1, 1}};
                this.width = 3;
                this.height = 2;
            }
            case FOUR_O -> {
                this.positions = new int[][]{{0, 0}, {1, 0}, {0, 1}, {1, 1}};
                this.width = 2;
                this.height = 2;
            }
            case FOUR_Z -> {
                this.positions = new int[][]{{0, 0}, {1, 0}, {1, 1}, {2, 1}};
                this.width = 3;
                this.height = 2;
            }
        }
    }

    public PieceColor getColor() {
        return color;
    }

    public PieceType getType() {
        return type;
    }

    public int getValue() {
        return value;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int[][] getPositions() {
        return positions;
    }

    @Override
    public String toString() {
        return "Piece{" +
                "color=" + color +
                ", type=" + type +
                '}';
    }

    @Override
    public int compareTo(Piece o) {
        return o.value - this.value;
    }
}
