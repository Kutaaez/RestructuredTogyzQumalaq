package org.example;

public class StandardGameStateChecker implements GameStateChecker {
    @Override
    public void checkState(ToguzBoard board) {
        int[] fields = board.getFields();
        int whiteKum = 0;
        for (int i = 0; i < 9; i++) {
            if (fields[i] < 255) {
                whiteKum += fields[i];
            }
        }
        int blackKum = 162 - whiteKum - fields[18] - fields[19];

        if (fields[22] == 0 && whiteKum == 0) {
            fields[19] += blackKum;
        } else if (fields[22] == 1 && blackKum == 0) {
            fields[18] += whiteKum;
        }

        if (fields[18] >= 82) {
            board.setFinished(true);
            board.setGameResult(1);
        } else if (fields[19] >= 82) {
            board.setFinished(true);
            board.setGameResult(-1);
        } else if (fields[18] == 81 && fields[19] == 81) {
            board.setFinished(true);
            board.setGameResult(0);
        }
    }
}
//(Strategy)