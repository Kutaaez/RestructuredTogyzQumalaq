package org.example.entitites;

import org.example.model.facade.ToguzBoard;

public interface IPlayer {
    int makeMove(ToguzBoard board); // Returns the move index (1–9)
    int getColor(); // Returns the player's color (0 or 1)
}