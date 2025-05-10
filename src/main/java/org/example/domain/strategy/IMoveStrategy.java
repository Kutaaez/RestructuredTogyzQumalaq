package org.example.domain.strategy;

import org.example.domain.board.BoardState;

public interface IMoveStrategy {
    boolean executeMove(BoardState boardState, int hole, int playerColor); // Executes a move and returns true if successful
}