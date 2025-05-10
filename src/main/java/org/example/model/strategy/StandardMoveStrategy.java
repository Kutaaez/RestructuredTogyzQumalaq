package org.example.model.strategy;

import org.example.model.core.BoardState;

public class StandardMoveStrategy implements IMoveStrategy {
    @Override
    public boolean executeMove(BoardState boardState, int hole, int playerColor) {
        int idx = hole + 9 * playerColor - 1;
        int seeds = boardState.getHoleCount(idx);

        // Sowing seeds
        int sow;
        if (seeds == 1) {
            boardState.setHoleCount(idx, 0);
            sow = 1;
        } else {
            boardState.setHoleCount(idx, 1);
            sow = seeds - 1;
        }

        // Distribute seeds
        for (int i = 0; i < sow; i++) {
            idx = (idx + 1) % 18; // Only real holes 0–17
            if (boardState.getHoleCount(idx) == 255) {
                // Tuzdyk: Go to opponent's kazan
                int kazanIndex = (idx > 8 ? 0 : 1);
                boardState.addToKazan(kazanIndex, 1);
            } else {
                boardState.incrementHoleCount(idx);
            }
        }

        // Check for tuzdyk (3 seeds in opponent's hole)
        if (boardState.getHoleCount(idx) == 3) {
            // White player (0): Holes 9–16
            if (playerColor == 0 && idx > 8 && idx < 17 && boardState.getTuzdyk(0) == 0 && boardState.getTuzdyk(1) != idx - 8) {
                boardState.addToKazan(0, 3);
                boardState.setHoleCount(idx, 255);
                boardState.setTuzdyk(0, idx - 8);
            }
            // Black player (1): Holes 0–7
            else if (playerColor == 1 && idx < 8 && boardState.getTuzdyk(1) == 0 && boardState.getTuzdyk(0) != idx + 1) {
                boardState.addToKazan(1, 3);
                boardState.setHoleCount(idx, 255);
                boardState.setTuzdyk(1, idx + 1);
            }
        }

        // Capture even number of seeds
        if (boardState.getHoleCount(idx) % 2 == 0 && boardState.getHoleCount(idx) != 255) {
            if (playerColor == 0 && idx > 8) {
                boardState.addToKazan(0, boardState.getHoleCount(idx));
                boardState.setHoleCount(idx, 0);
            } else if (playerColor == 1 && idx < 9) {
                boardState.addToKazan(1, boardState.getHoleCount(idx));
                boardState.setHoleCount(idx, 0);
            }
        }

        // Switch player
        boardState.setCurrentPlayer(1 - boardState.getCurrentPlayer());
        return true;
    }
}