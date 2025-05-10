package org.example;

import java.util.ArrayList;
import java.util.List;

public class ToguzBoard {
    private final int[] toguzFields = new int[23];
    private boolean finished = false;
    private int gameResult = -2;

    public ToguzBoard() {
        // инициализируем первые 18 лунок по 9 шариков, остальные поля — нулями
        for (int i = 0; i < toguzFields.length; i++) {
            toguzFields[i] = (i < 18 ? 9 : 0);
        }
    }

    public boolean makeMove(int hole, int playerColor) {
        // ход не по очереди
        if (playerColor != toguzFields[22]) {
            return false;
        }
        int idx = hole + 9 * playerColor - 1;
        // неверная лунка
        if (hole < 1 || hole > 9 || toguzFields[idx] == 0 || toguzFields[idx] == 255) {
            return false;
        }

        int seeds = toguzFields[idx];
        int sow;
        if (seeds == 1) {
            toguzFields[idx] = 0;
            sow = 1;
        } else {
            toguzFields[idx] = 1;
            sow = seeds - 1;
        }

        // раздача шариков по кругу
        for (int i = 0; i < sow; i++) {
            idx = (idx + 1) % 18;  // только реальные лунки 0–17
            if (toguzFields[idx] == 255) {
                // попали в туздык — сразу в казан соперника
                int kazanIndex = (idx > 8 ? 18 : 19);
                toguzFields[kazanIndex]++;
            } else {
                toguzFields[idx]++;
            }
        }

        // проверка на «туздык»
        if (toguzFields[idx] == 3) {
            // для белого игрока (0) — лунки 9–16
            if (playerColor == 0
                    && toguzFields[20] == 0
                    && idx > 8
                    && idx < 17
                    && toguzFields[21] != idx - 8) {
                toguzFields[18] += 3;
                toguzFields[idx] = 255;
                toguzFields[20] = idx - 8;
            }
            // для чёрного игрока (1) — лунки 0–7
            else if (playerColor == 1
                    && toguzFields[21] == 0
                    && idx < 8
                    && toguzFields[20] != idx + 1) {
                toguzFields[19] += 3;
                toguzFields[idx] = 255;
                toguzFields[21] = idx + 1;
            }
        }

        // захват чётного количества шариков
        if (toguzFields[idx] % 2 == 0) {
            if (playerColor == 0 && idx > 8) {
                toguzFields[18] += toguzFields[idx];
                toguzFields[idx] = 0;
            } else if (playerColor == 1 && idx < 9) {
                toguzFields[19] += toguzFields[idx];
                toguzFields[idx] = 0;
            }
        }

        // смена хода
        toguzFields[22] = 1 - toguzFields[22];
        checkPos();
        return true;
    }

    public List<Integer> getAvailableMoves(int playerColor) {
        List<Integer> moves = new ArrayList<>();
        int start = (playerColor == 0 ? 0 : 9);
        int end   = (playerColor == 0 ? 8 : 17);
        for (int i = start; i <= end; i++) {
            if (toguzFields[i] > 0 && toguzFields[i] < 255) {
                moves.add(i - start + 1);
            }
        }
        return moves;
    }

    private void checkPos() {
        int whiteSum = 0;
        for (int i = 0; i < 9; i++) {
            if (toguzFields[i] < 255) {
                whiteSum += toguzFields[i];
            }
        }
        int blackSum = 162 - whiteSum - toguzFields[18] - toguzFields[19];

        // если у текущего игрока нет ходов — отдать все оставшиеся шарики
        if (toguzFields[22] == 0 && whiteSum == 0) {
            toguzFields[19] += blackSum;
        } else if (toguzFields[22] == 1 && blackSum == 0) {
            toguzFields[18] += whiteSum;
        }

        // проверка конца игры
        if (toguzFields[18] >= 82) {
            finished   = true;
            gameResult = 1;
        } else if (toguzFields[19] >= 82) {
            finished   = true;
            gameResult = -1;
        } else if (toguzFields[18] == 81 && toguzFields[19] == 81) {
            finished   = true;
            gameResult = 0;
        }
    }

    public int getGameResult() {
        return gameResult;
    }

    public String getScore() {
        return toguzFields[18] + " - " + toguzFields[19];
    }

    public int getCurrentColor() {
        return toguzFields[22];
    }

    public boolean isGameFinished() {
        return finished;
    }

    public int getTuzdyk(int playerColor) {
        if (playerColor < 0 || playerColor > 1) {
            throw new IllegalArgumentException("Player color must be 0 or 1");
        }
        return toguzFields[20 + playerColor];
    }

    public int getKazan(int playerColor) {
        if (playerColor < 0 || playerColor > 1) {
            throw new IllegalArgumentException("Player color must be 0 or 1");
        }
        return toguzFields[18 + playerColor];
    }

    public int getOpponentHoleCount(int hole) {
        if (hole < 1 || hole > 9) {
            throw new IllegalArgumentException("Hole index must be 1..9");
        }
        return toguzFields[9 + (hole - 1)];
    }

    public int getHoleCount(int hole) {
        if (hole < 1 || hole > 9) {
            throw new IllegalArgumentException("Hole index must be 1..9");
        }
        return toguzFields[hole - 1];
    }

    public void reset() {
        for (int i = 0; i < toguzFields.length; i++) {
            toguzFields[i] = (i < 18 ? 9 : 0);
        }
        finished   = false;
        gameResult = -2;
        toguzFields[22] = 0;
    }
}
