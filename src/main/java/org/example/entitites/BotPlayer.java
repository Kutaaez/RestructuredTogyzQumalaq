package org.example.entitites;

import org.example.ToguzBoard;

import java.util.ArrayList;

public class BotPlayer implements Player {
    private final int color;

    public BotPlayer(int color) {
        this.color = color;
    }

    @Override
    public int makeMove(ToguzBoard board) {
        ArrayList<Integer> moves = board.getAvailableMoves(color);
        if (moves.isEmpty()) {
            return -1;
        }
        return moves.get((int) (Math.random() * moves.size()));
    }

    @Override
    public int getColor() {
        return color;
    }
}