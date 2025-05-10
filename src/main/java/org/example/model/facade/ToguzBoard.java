package org.example.model.facade;

import org.example.model.core.BoardState;
import org.example.model.factory.MoveStrategyFactory;
import org.example.model.observer.IStateObserver;
import org.example.model.state.IGameState;
import org.example.model.state.GameStateImpl;
import org.example.model.strategy.IMoveStrategy;

import java.util.ArrayList;
import java.util.List;

public class ToguzBoard {
    private final BoardState boardState;
    private final IGameState gameState;
    private final IMoveStrategy moveStrategy;
    private final List<IStateObserver> observers;

    public ToguzBoard() {
        this.boardState = new BoardState();
        this.gameState = new GameStateImpl(boardState);
        this.moveStrategy = MoveStrategyFactory.createStandardMoveStrategy();
        this.observers = new ArrayList<>();
    }

    public boolean makeMove(int hole, int playerColor) {
        if (gameState.isFinished() || !isValidMove(hole, playerColor)) {
            return false;
        }
        boolean moveSuccessful = moveStrategy.executeMove(boardState, hole, playerColor);
        if (moveSuccessful) {
            gameState.checkGameState();
            notifyObservers();
        }
        return moveSuccessful;
    }

    public List<Integer> getAvailableMoves(int playerColor) {
        return boardState.getAvailableMoves(playerColor);
    }

    public int getGameResult() {
        return gameState.getResult();
    }

    public String getScore() {
        return boardState.getKazan(0) + " - " + boardState.getKazan(1);
    }

    public int getCurrentColor() {
        return boardState.getCurrentPlayer();
    }

    public boolean isGameFinished() {
        return gameState.isFinished();
    }

    public int getTuzdyk(int playerColor) {
        if (playerColor < 0 || playerColor > 1) {
            throw new IllegalArgumentException("Player color must be 0 or 1");
        }
        return boardState.getTuzdyk(playerColor);
    }

    public int getKazan(int playerColor) {
        if (playerColor < 0 || playerColor > 1) {
            throw new IllegalArgumentException("Player color must be 0 or 1");
        }
        return boardState.getKazan(playerColor);
    }

    public int getOpponentHoleCount(int hole) {
        if (hole < 1 || hole > 9) {
            throw new IllegalArgumentException("Hole index must be 1..9");
        }
        return boardState.getHoleCount(9 + (hole - 1));
    }

    public int getHoleCount(int hole) {
        if (hole < 1 || hole > 9) {
            throw new IllegalArgumentException("Hole index must be 1..9");
        }
        return boardState.getHoleCount(hole - 1);
    }

    public void reset() {
        boardState.reset();
        gameState.reset();
        notifyObservers();
    }

    public void addObserver(IStateObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(IStateObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers() {
        for (IStateObserver observer : observers) {
            observer.onStateChanged();
        }
    }

    private boolean isValidMove(int hole, int playerColor) {
        if (playerColor != boardState.getCurrentPlayer()) {
            return false;
        }
        int idx = hole + 9 * playerColor - 1;
        return hole >= 1 && hole <= 9 && boardState.getHoleCount(idx) > 0 && boardState.getHoleCount(idx) != 255;
    }
}