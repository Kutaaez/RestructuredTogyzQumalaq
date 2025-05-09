package org.example.controller;

public interface MoveHandler {
    void handleHumanMove(GameController controller, int holeIndex, boolean playerSide);
    void handleBotMove(GameController controller);
}