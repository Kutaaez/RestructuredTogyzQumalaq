package org.example.controller;

import org.example.ToguzBoard;
import org.example.entitites.BotPlayer;
import org.example.entitites.Player;

public class StandardMoveHandler implements MoveHandler {
    @Override
    public void handleHumanMove(GameController controller, int holeIndex, boolean playerSide) {
        ToguzBoard model = controller.getModel();
        if (model.isGameFinished()) {
            return;
        }
        int currentPlayer = model.getCurrentColor();
        boolean isPlayerSide = (currentPlayer == 0 && playerSide) || (currentPlayer == 1 && !playerSide);
        Player[] players = controller.getPlayers();
        if (!isPlayerSide || players[currentPlayer] instanceof BotPlayer) {
            return;
        }
        model.makeMove(holeIndex, currentPlayer);
    }

    @Override
    public void handleBotMove(GameController controller) {
        ToguzBoard model = controller.getModel();
        if (model.isGameFinished()) {
            return;
        }
        int currentPlayer = model.getCurrentColor();
        Player[] players = controller.getPlayers();
        if (players[currentPlayer] instanceof BotPlayer) {
            int move = players[currentPlayer].makeMove(model);
            if (move != -1) {
                model.makeMove(move, currentPlayer);
            }
        }
    }
}