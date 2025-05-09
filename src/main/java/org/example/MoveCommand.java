package org.example;

public class MoveCommand {
    private final int num;
    private final int playerColor;

    public MoveCommand(int num, int playerColor) {
        this.num = num;
        this.playerColor = playerColor;
    }

    public int getNum() {
        return num;
    }

    public int getPlayerColor() {
        return playerColor;
    }
}

//(Command)