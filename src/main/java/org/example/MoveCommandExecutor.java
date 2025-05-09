package org.example;

public abstract class MoveCommandExecutor {
    public boolean executeMove(ToguzBoard board, MoveCommand move) {
        int[] fields = board.getFields();
        int num = move.getNum();
        int playerColor = move.getPlayerColor();

        if (!validateMove(fields, num, playerColor)) {
            return false;
        }

        int numotau = num + (9 * playerColor) - 1;
        int numkum = fields[numotau];
        boolean capturedTuzdyk = false;
        int sow;

        if (numkum == 1) {
            fields[numotau] = 0;
            sow = 1;
        } else {
            fields[numotau] = 1;
            sow = numkum - 1;
        }

        numotau = sowKumalak(fields, numotau, sow);
        capturedTuzdyk = checkTuzdyk(fields, numotau, playerColor, capturedTuzdyk);
        captureEvenKumalak(fields, numotau, playerColor);

        int finished_otau = numotau > 8 ? numotau - 9 + 1 : numotau + 1;
        String moveStr = Integer.toString(num) + Integer.toString(finished_otau);
        if (capturedTuzdyk) {
            moveStr += "x";
        }

        fields[22] = (fields[22] == 0 ? 1 : 0);
        return true;
    }

    protected boolean validateMove(int[] fields, int num, int playerColor) {
        if (playerColor != fields[22]) {
            return false;
        }
        int numotau = num + (9 * playerColor) - 1;
        return num >= 1 && num <= 9 && fields[numotau] != 0 && fields[numotau] != 255;
    }

    protected int sowKumalak(int[] fields, int numotau, int sow) {
        for (int i = 0; i < sow; i++) {
            numotau++;
            if (numotau > 17) numotau = 0;
            if (fields[numotau] == 255) {
                if (numotau > 8) fields[18]++;
                else fields[19]++;
            } else {
                fields[numotau]++;
            }
        }
        return numotau;
    }

    protected boolean checkTuzdyk(int[] fields, int numotau, int playerColor, boolean capturedTuzdyk) {
        if (fields[numotau] == 3) {
            if (playerColor == 0 && fields[20] == 0 && numotau > 8 && numotau < 17 && fields[21] != numotau - 8) {
                fields[18] += 3;
                fields[numotau] = 255;
                fields[20] = numotau - 8;
                return true;
            } else if (playerColor == 1 && fields[21] == 0 && numotau < 8 && fields[20] != numotau + 1) {
                fields[19] += 3;
                fields[numotau] = 255;
                fields[21] = numotau + 1;
                return true;
            }
        }
        return capturedTuzdyk;
    }

    protected void captureEvenKumalak(int[] fields, int numotau, int playerColor) {
        if (fields[numotau] % 2 == 0) {
            if (playerColor == 0 && numotau > 8) {
                fields[18] += fields[numotau];
                fields[numotau] = 0;
            } else if (playerColor == 1 && numotau < 9) {
                fields[19] += fields[numotau];
                fields[numotau] = 0;
            }
        }
    }
}

//(Command)