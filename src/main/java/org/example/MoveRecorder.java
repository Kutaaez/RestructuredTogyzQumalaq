package org.example;

import java.util.ArrayList;
import java.util.List;

public class MoveRecorder implements BoardObserver {
    private final List<String> gameMoves = new ArrayList<>();

    @Override
    public void onMoveMade(MoveCommand move) {
        String moveStr = Integer.toString(move.getNum()) + "x"; // Упрощённый формат, можно расширить
        gameMoves.add(moveStr);
    }

    @Override
    public void onReset() {
        gameMoves.clear();
    }

    public List<String> getGameMoves() {
        return new ArrayList<>(gameMoves);
    }
}

//(Observer)