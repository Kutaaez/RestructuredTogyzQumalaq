package org.example.model.state;

import org.example.model.core.BoardState;

public class GameStateImpl implements IGameState {
    private boolean finished;
    private int gameResult;
    private final GameResultChecker resultChecker;
    private final BoardState boardState;

    public GameStateImpl(BoardState boardState) {
        this.boardState = boardState;
        this.resultChecker = new GameResultChecker();
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
        resultChecker.checkGameState(boardState, this);
    }

    @Override
    public void reset() {
        finished = false;
        gameResult = -2;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public void setGameResult(int gameResult) {
        this.gameResult = gameResult;
    }
}