package org.example.controller;

import org.example.entitites.HumanPlayer;
import org.example.entitites.Player;

public class TwoHumanPlayerFactory implements PlayerFactory {
    @Override
    public Player[] createPlayers() {
        return new Player[]{new HumanPlayer(0), new HumanPlayer(1)};
    }
}