package org.example.entitites;


import org.example.ToguzBoard;

public interface Player {
    int makeMove(ToguzBoard board); // Возвращает индекс хода (1–9)
    int getColor(); // Возвращает цвет игрока (0 или 1)
}
