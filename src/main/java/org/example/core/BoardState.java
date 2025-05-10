package org.example.model.core;

import java.util.ArrayList;
import java.util.List;

public class BoardState {
    private final int[] fields; // 0–17: holes, 18: white kazan, 19: black kazan, 20: white tuzdyk, 21: black tuzdyk, 22: current player

    public BoardState() {
        fields = new int[23];
        reset();
    }

    public void reset() {
        for (int i = 0; i < fields.length; i++) {
            fields[i] = (i < 18 ? 9 : 0);
        }
        fields[22] = 0; // White starts
    }

    public int getHoleCount(int index) {
        if (index < 0 || index >= 18) {
            throw new IllegalArgumentException("Hole index must be 0..17");
        }
        return fields[index];
    }

    public void setHoleCount(int index, int value) {
        if (index < 0 || index >= 18) {
            throw new IllegalArgumentException("Hole index must be 0..17");
        }
        fields[index] = value;
    }

    public void incrementHoleCount(int index) {
        if (index < 0 || index >= 18) {
            throw new IllegalArgumentException("Hole index must be 0..17");
        }
        if (fields[index] != 255) {
            fields[index]++;
        }
    }

    public int getKazan(int playerColor) {
        if (playerColor < 0 || playerColor > 1) {
            throw new IllegalArgumentException("Player color must be 0 or 1");
        }
        return fields[18 + playerColor];
    }

    public void addToKazan(int playerColor, int amount) {
        if (playerColor < 0 || playerColor > 1) {
            throw new IllegalArgumentException("Player color must be 0 or 1");
        }
        fields[18 + playerColor] += amount;
    }

    public int getTuzdyk(int playerColor) {
        if (playerColor < 0 || playerColor > 1) {
            throw new IllegalArgumentException("Player color must be 0 or 1");
        }
        return fields[20 + playerColor];
    }

    public void setTuzdyk(int playerColor, int hole) {
        if (playerColor < 0 || playerColor > 1) {
            throw new IllegalArgumentException("Player color must be 0 or 1");
        }
        fields[20 + playerColor] = hole;
    }

    public int getCurrentPlayer() {
        return fields[22];
    }

    public void setCurrentPlayer(int playerColor) {
        if (playerColor < 0 || playerColor > 1) {
            throw new IllegalArgumentException("Player color must be 0 or 1");
        }
        fields[22] = playerColor;
    }

    public List<Integer> getAvailableMoves(int playerColor) {
        List<Integer> moves = new ArrayList<>();
        int start = (playerColor == 0 ? 0 : 9);
        int end = (playerColor == 0 ? 8 : 17);
        for (int i = start; i <= end; i++) {
            if (fields[i] > 0 && fields[i] < 255) {
                moves.add(i - start + 1);
            }
        }
        return moves;
    }
}