package main.mechanics.commands;

import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.ActionsInput;
import main.mechanics.table.GameTable;


public final class CommandController implements CommandUser {
    private DebugAndStatsCommands debugAndStatsCommands;
    private GameControlCommands gameControlCommands;


    public CommandController(final ArrayNode output) {
        this.debugAndStatsCommands = new DebugAndStatsCommands(output);
        this.gameControlCommands = new GameControlCommands(output);
    }

    public void determineCommand(final ActionsInput actionsInput) {
        if (!actionsInput.getCommand().startsWith("get")) {
            gameControlCommands.determineCommand(actionsInput);
        } else {
            debugAndStatsCommands.determineCommand(actionsInput);
        }
    }

}
