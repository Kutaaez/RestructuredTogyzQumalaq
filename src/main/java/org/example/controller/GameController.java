package org.example.controller;

import org.example.ToguzBoard;
import org.example.view.MainView;
import org.example.entitites.Player;

public class GameController {
    private final ToguzBoard model;
    private MainView view;
    private final Player[] players;
    private final MoveHandler moveHandler;

    public GameController(PlayerFactory playerFactory, MoveHandler moveHandler, ToguzBoard model) {
        this.model = model;
        this.players = playerFactory.createPlayers();
        this.moveHandler = moveHandler;
    }

    public void setView(MainView view) {
        this.view = view;
        view.update();
        moveHandler.handleBotMove(this);
    }

    public void onHoleClicked(int holeIndex, boolean playerSide) {
        moveHandler.handleHumanMove(this, holeIndex, playerSide);
        view.update();
    }

    public void onNewGame() {
        model.reset();
        view.update();
        moveHandler.handleBotMove(this);
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

    public Player[] getPlayers() {
        return players;
    }

    public ToguzBoard getModel() {
        return model;
    }
}