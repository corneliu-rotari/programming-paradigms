package app.features;

import io.input.action.Action;

/**
 * Interface for an object that can change the state of the Application
 */
public interface ActionTacker {
    /**
     * Changes the state of the Application
     * @param action - input for the feature
     */
    void takeAction(Action action);
}
