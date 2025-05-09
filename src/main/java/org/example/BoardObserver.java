package org.example;

public interface BoardObserver {
    void onMoveMade(MoveCommand move);
    void onReset();
}

//(Observer)