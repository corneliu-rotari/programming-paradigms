package main.mechanics.commands;

import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.ActionsInput;
import main.mechanics.table.GameTable;


public final class CommandController implements CommandUser {
    private DebugAndStatsCommands debugAndStatsCommands;
    private GameControlCommands gameControlCommands;


    public CommandController(ArrayNode output, GameTable gameTable) {
        this.debugAndStatsCommands = new DebugAndStatsCommands(output, gameTable);
        this.gameControlCommands = new GameControlCommands(gameTable);
    }

    public void determineCommand(final ActionsInput actionsInput) {
        if (actionsInput.getCommand().startsWith("get")) {
            debugAndStatsCommands.determineCommand(actionsInput);
        } else {
            gameControlCommands.determineCommand(actionsInput);
        }
    }
}
