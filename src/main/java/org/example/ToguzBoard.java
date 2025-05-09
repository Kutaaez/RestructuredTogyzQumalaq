package org.example;

import java.util.ArrayList;
import java.util.List;

public class ToguzBoard {
    private final int[] toguzFields;
    private boolean finished = false;
    private int gameResult = -2;
    private final List<BoardObserver> observers = new ArrayList<>();
    private final MoveCommandExecutor moveExecutor;
    private final GameStateChecker stateChecker;

    public ToguzBoard(BoardFactory factory, MoveCommandExecutor moveExecutor, GameStateChecker stateChecker) {
        this.toguzFields = factory.createBoard();
        this.moveExecutor = moveExecutor;
        this.stateChecker = stateChecker;
    }

    public boolean makeMove(int num, int playerColor) {
        MoveCommand move = new MoveCommand(num, playerColor);
        boolean result = moveExecutor.executeMove(this, move);
        if (result) {
            stateChecker.checkState(this);
            notifyObservers(move);
        }
        return result;
    }

    public List<Integer> getAvailableMoves(int playerColor) {
        List<Integer> moves = new ArrayList<>();
        int start = playerColor == 0 ? 0 : 9;
        int end = playerColor == 0 ? 8 : 17;
        for (int i = start; i <= end; i++) {
            if (toguzFields[i] > 0 && toguzFields[i] < 255) {
                moves.add(i - start + 1);
            }
        }
        return moves;
    }

    public void reset() {
        for (int i = 0; i < toguzFields.length; i++) {
            toguzFields[i] = i < 18 ? 9 : 0;
        }
        finished = false;
        gameResult = -2;
        notifyObserversReset();
    }

    public void addObserver(BoardObserver observer) {
        observers.add(observer);
    }

    public int[] getFields() {
        return toguzFields;
    }

    public boolean isGameFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public int getGameResult() {
        return gameResult;
    }

    public void setGameResult(int gameResult) {
        this.gameResult = gameResult;
    }

    public int getTuzdyk(int playerColor) {
        if (playerColor < 0 || playerColor > 1)
            throw new IllegalArgumentException("Player color must be 0 or 1");
        return toguzFields[20 + playerColor];
    }

    public int getKazan(int playerColor) {
        if (playerColor < 0 || playerColor > 1)
            throw new IllegalArgumentException("Player color must be 0 or 1");
        return toguzFields[18 + playerColor];
    }

    public int getOpponentHoleCount(int hole) {
        if (hole < 1 || hole > 9)
            throw new IllegalArgumentException("Hole index must be 1..9");
        return toguzFields[9 + (hole - 1)];
    }

    public int getHoleCount(int hole) {
        if (hole < 1 || hole > 9)
            throw new IllegalArgumentException("Hole index must be 1..9");
        return toguzFields[hole - 1];
    }

    public int getCurrentColor() {
        return toguzFields[22];
    }

    public String getScore() {
        return toguzFields[18] + " - " + toguzFields[19];
    }

    private void notifyObservers(MoveCommand move) {
        for (BoardObserver observer : observers) {
            observer.onMoveMade(move);
        }
    }

    private void notifyObserversReset() {
        for (BoardObserver observer : observers) {
            observer.onReset();
        }
    }
}