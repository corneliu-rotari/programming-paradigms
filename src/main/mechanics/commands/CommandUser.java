package main.mechanics.commands;

import fileio.ActionsInput;

public interface CommandUser {
    /**
     *
     * @param actionsInput - command information
     */
    void determineCommand(final ActionsInput actionsInput);
}
