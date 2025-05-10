package org.example.model.state;

import org.example.model.core.BoardState;

public class GameStateImpl implements IGameState {
    private final BoardState boardState;
    private boolean finished;
    private int gameResult;

    public GameStateImpl(BoardState boardState) {
        this.boardState = boardState;
        this.finished = false;
        this.gameResult = -2; // Ongoing
    }

    @Override
    public boolean isFinished() {
        return finished;
    }

    @Override
    public int getResult() {
        return gameResult;
    }

    @Override
    public void checkGameState() {
        int whiteSum = 0;
        for (int i = 0; i < 9; i++) {
            int count = boardState.getHoleCount(i);
            if (count < 255) {
                whiteSum += count;
            }
        }
        int blackSum = 162 - whiteSum - boardState.getKazan(0) - boardState.getKazan(1);

        // If the current player has no moves, give remaining seeds to the opponent
        if (boardState.getCurrentPlayer() == 0 && whiteSum == 0) {
            boardState.addToKazan(1, blackSum);
        } else if (boardState.getCurrentPlayer() == 1 && blackSum == 0) {
            boardState.addToKazan(0, whiteSum);
        }

        // Check end-of-game conditions
        if (boardState.getKazan(0) >= 82) {
            finished = true;
            gameResult = 1; // White wins
        } else if (boardState.getKazan(1) >= 82) {
            finished = true;
            gameResult = -1; // Black wins
        } else if (boardState.getKazan(0) == 81 && boardState.getKazan(1) == 81) {
            finished = true;
            gameResult = 0; // Draw
        }
    }

    @Override
    public void reset() {
        finished = false;
        gameResult = -2;
    }
}