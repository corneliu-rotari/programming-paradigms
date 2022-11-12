package main.mechanics.commands;

import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.ActionsInput;
import main.mechanics.table.GameTable;


public final class CommandController implements CommandControlHelper {
    private DebugAndStatsCommands debugAndStatsCommands;
    private GameControlCommands gameControlCommands;


    public CommandController(final ArrayNode output) {
        this.debugAndStatsCommands = new DebugAndStatsCommands(output);
        this.gameControlCommands = new GameControlCommands(output);
    }

    /**
     * Chooses which helper to send it to
     * @param actionsInput - command information
     */
    public void determineCommand(final ActionsInput actionsInput) {
        if (!actionsInput.getCommand().startsWith("get")
                && !GameTable.getGameTable().isGameFinished()) {
            gameControlCommands.determineCommand(actionsInput);
        } else {
            debugAndStatsCommands.determineCommand(actionsInput);
        }
    }

}
