package app.features;

import io.input.action.Action;

/**
 * Interface for Command Design Pattern
 */
public interface ActionTacker {
    /**
     * Changes the state of the Application
     * @param action - input for the feature
     */
    void takeAction(Action action);
}
