package org.example.controller;

import org.example.entitites.IPlayer;
import org.example.model.command.ICommand;
import org.example.model.command.MoveCommand;
import org.example.model.facade.ToguzBoard;

public class BotMoveScheduler {
    public void scheduleBotMove(GameController controller, ToguzBoard model, IPlayer[] players) {
        if (model.isGameFinished()) {
            return;
        }
        int currentPlayer = model.getCurrentColor();
        if (players[currentPlayer] instanceof org.example.entitites.BotPlayer) {
            int move = players[currentPlayer].makeMove(model);
            if (move != -1) {
                ICommand command = new MoveCommand(model, move, currentPlayer);
                command.execute();
            }
        }
    }
}