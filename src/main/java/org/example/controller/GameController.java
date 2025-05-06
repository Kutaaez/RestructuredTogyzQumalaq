package org.example.controller;

import org.example.entitites.Player;
import org.example.ToguzBoard;
import org.example.view.MainView;
import org.example.entitites.HumanPlayer;

public class GameController {

    private final ToguzBoard model;
    private MainView view;
    private Player[] players;

    public GameController() {
        this.model = new ToguzBoard();
        this.players = new Player[]{new HumanPlayer(0), new HumanPlayer(1)};
    }

    public void setView(MainView view) {
        this.view = view;
        view.update();
    }

    public void onHoleClicked(int holeIndex, boolean playerSide) {
        if (model.isGameFinished()) {
            return;
        }
        int currentPlayer = model.getCurrentColor();
        // Проверяем, что клик по правильной стороне
        boolean isPlayerSide = (currentPlayer == 0 && playerSide) || (currentPlayer == 1 && !playerSide);
        if (!isPlayerSide) {
            return;
        }
        // Проверяем, что ход валиден
        if (model.makeMove(holeIndex, currentPlayer)) {
            view.update();
        }
    }

    public void onNewGame() {
        model.reset();
        view.update();
    }

    public int getCurrentPlayer() {
        return model.getCurrentColor();
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
}