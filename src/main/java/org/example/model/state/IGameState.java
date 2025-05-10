package org.example.model.state;

public interface IGameState {
    boolean isFinished();
    int getResult();
    void checkGameState();
    void reset();
}