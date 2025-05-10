package org.example.model.state;

public interface IGameState {
    boolean isFinished(); // Returns true if the game is finished
    int getResult(); // Returns game result: 1 (white wins), -1 (black wins), 0 (draw), -2 (ongoing)
    void checkGameState(); // Updates game state based on board conditions
    void reset(); // Resets game state to initial conditions
}