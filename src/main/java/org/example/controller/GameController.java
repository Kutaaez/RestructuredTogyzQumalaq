package org.example.controller;

import org.example.entitites.BotPlayer;
import org.example.ToguzBoard;
import org.example.view.MainView;
import org.example.entitites.Player;
import org.example.entitites.HumanPlayer;

public class GameController {

    private final ToguzBoard model;
    private MainView view;
    private final Player[] players;

    public GameController(boolean twoPlayers) {
        this.model = new ToguzBoard();
        if (twoPlayers) {
            this.players = new Player[]{new HumanPlayer(0), new HumanPlayer(1)};
        } else {
            this.players = new Player[]{new HumanPlayer(0), new BotPlayer(1)};
        }
    }

    public void setView(MainView view) {
        this.view = view;
        view.update();
        // Если текущий игрок — бот, делаем его ход
        makeBotMoveIfNeeded();
    }

    public void onHoleClicked(int holeIndex, boolean playerSide) {
        if (model.isGameFinished()) {
            return;
        }
        int currentPlayer = model.getCurrentColor();
        // Проверяем, что клик по правильной стороне
        boolean isPlayerSide = (currentPlayer == 0 && playerSide) || (currentPlayer == 1 && !playerSide);
        if (!isPlayerSide || players[currentPlayer] instanceof BotPlayer) {
            return;
        }
        // Проверяем, что ход валиден
        if (model.makeMove(holeIndex, currentPlayer)) {
            view.update();
            makeBotMoveIfNeeded();
        }
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
                view.update();
            }
        }
    }

    public void onNewGame() {
        model.reset();
        view.update();
        makeBotMoveIfNeeded();
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