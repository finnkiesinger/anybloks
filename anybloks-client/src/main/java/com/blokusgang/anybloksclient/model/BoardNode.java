package com.blokusgang.anybloksclient.model;

public class BoardNode {
    public int x;
    public int y;
    public int visualX;
    public int visualY;

    public BoardNode(int x, int y) {
        this.x = x;
        this.y = y;
        this.visualX = x;
        this.visualY = y;
    }

    public BoardNode(int x, int y, int visualX, int visualY) {
        this.x = x;
        this.y = y;
        this.visualX = visualX;
        this.visualY = visualY;
    }

}
