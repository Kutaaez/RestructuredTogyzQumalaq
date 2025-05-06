package org.example.entitites;

import org.example.ToguzBoard;

public class HumanPlayer implements Player {
    private final int color;

    public HumanPlayer(int color) {
        this.color = color;
    }

    @Override
    public int makeMove(ToguzBoard board) {
        // Ход определяется через UI (GameController), поэтому возвращаем -1
        // Реальный ход будет передан через GameController
        return -1;
    }

    @Override
    public int getColor() {
        return color;
    }
}