package org.example;

public class StandardBoardFactory implements BoardFactory {
    @Override
    public int[] createBoard() {
        int[] fields = new int[23];
        for (int i = 0; i < 18; i++) {
            fields[i] = 9;
        }
        return fields;
    }
}

//(Factory Method)