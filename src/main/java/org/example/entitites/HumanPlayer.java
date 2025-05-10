package org.example.entitites;

import org.example.model.facade.ToguzBoard;

public class HumanPlayer implements IPlayer {
    private final int color;

    public HumanPlayer(int color) {
        this.color = color;
    }

    @Override
    public int makeMove(ToguzBoard board) {
        // Move is determined via UI (GameController), so return -1
        // Actual move is passed through GameController
        return -1;
    }

    @Override
    public int getColor() {
        return color;
    }
}