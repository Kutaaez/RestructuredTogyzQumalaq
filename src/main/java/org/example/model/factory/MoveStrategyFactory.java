package org.example.model.factory;

import org.example.model.rules.IRules;
import org.example.model.rules.StandardRules;
import org.example.model.strategy.IMoveStrategy;
import org.example.model.strategy.StandardMoveStrategy;

public class MoveStrategyFactory {
    public static IMoveStrategy createStandardMoveStrategy() {
        IRules rules = new StandardRules();
        return new StandardMoveStrategy(rules);
    }
}