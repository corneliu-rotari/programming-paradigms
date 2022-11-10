package main.mechanics.commands;

import fileio.ActionsInput;
import main.mechanics.table.GameTable;

public final class GameControlCommands implements CommandUser {
    private GameTable gameTable;
    public GameControlCommands(GameTable gameTable) {
        this.gameTable = gameTable;
    }

    /**
     * C
     * @param actionsInput - command information
     */
    public void determineCommand(final ActionsInput actionsInput) {
    }
}
