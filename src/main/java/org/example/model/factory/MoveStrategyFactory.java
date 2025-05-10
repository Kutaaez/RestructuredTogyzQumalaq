package org.example.model.factory;

import org.example.model.strategy.IMoveStrategy;
import org.example.model.strategy.StandardMoveStrategy;

public class MoveStrategyFactory {
    public static IMoveStrategy createStandardMoveStrategy() {
        return new StandardMoveStrategy();
    }
}