package models;

public class Game {
    public String code;

    public Game() {
        this.code = "";
        for (int i = 0; i < 6; i++) {
            code += new java.util.Random().nextInt(10);
        }
    }
}
