package org.example.model.command;

import org.example.model.facade.ToguzBoard;

public class ResetCommand implements ICommand {
    private final ToguzBoard board;

    public ResetCommand(ToguzBoard board) {
        this.board = board;
    }

    @Override
    public boolean execute() {
        board.reset();
        return true;
    }
}