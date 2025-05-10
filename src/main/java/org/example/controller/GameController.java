package org.example.controller;

import org.example.entitites.BotPlayer;
import org.example.entitites.HumanPlayer;
import org.example.entitites.IPlayer;
import org.example.model.facade.ToguzBoard;
import org.example.model.observer.IStateObserver;
import org.example.view.MainView;

public class GameController implements IStateObserver {
    private final ToguzBoard model;
    private MainView view;
    private final IPlayer[] players;

    public GameController(boolean twoPlayers) {
        this.model = new ToguzBoard();
        this.players = twoPlayers
                ? new IPlayer[]{new HumanPlayer(0), new HumanPlayer(1)}
                : new IPlayer[]{new HumanPlayer(0), new BotPlayer(1)};
        this.model.addObserver(this);
    }

    public void setView(MainView view) {
        this.view = view;
        onStateChanged(); // Initial update
    }

    public void onHoleClicked(int holeIndex, boolean playerSide) {
        if (model.isGameFinished()) {
            return;
        }
        int currentPlayer = model.getCurrentColor();
        boolean isPlayerSide = (currentPlayer == 0 && playerSide) || (currentPlayer == 1 && !playerSide);
        if (!isPlayerSide || players[currentPlayer] instanceof BotPlayer) {
            return;
        }
        model.makeMove(holeIndex, currentPlayer);
    }

    private void makeBotMoveIfNeeded() {
        if (model.isGameFinished()) {
            return;
        }
        int currentPlayer = model.getCurrentColor();
        if (players[currentPlayer] instanceof BotPlayer) {
            int move = players[currentPlayer].makeMove(model);
            if (move != -1) {
                model.makeMove(move, currentPlayer);
            }
        }
    }

    public void onNewGame() {
        model.reset();
    }

    public int getCurrentPlayer() {
        return model.getCurrentColor();
    }

    public int getGameResult() {
        return model.getGameResult();
    }

    public int getHoleCount(int holeIndex) {
        return model.getHoleCount(holeIndex);
    }

    public int getOpponentHoleCount(int holeIndex) {
        return model.getOpponentHoleCount(holeIndex);
    }

    public int getKazan(int playerColor) {
        return model.getKazan(playerColor);
    }

    public int getTuzdyk(int playerColor) {
        return model.getTuzdyk(playerColor);
    }

    public boolean isFinished() {
        return model.isGameFinished();
    }

    public IPlayer[] getPlayers() {
        return players;
    }

    @Override
    public void onStateChanged() {
        if (view != null) {
            view.update();
            makeBotMoveIfNeeded();
        }
    }
}