package implementation.mechanics.commands;

import fileio.ActionsInput;

public interface CommandControlHelper {
    /**
     * Methode that parses and sends the command
     * @param actionsInput - command information
     */
    void determineCommand(ActionsInput actionsInput);
}
