package app.features;

import io.input.action.Request;

/**
 * Interface for Command Design Pattern
 */
public interface FeatureCommand {
    /**
     * Changes the state of the Application
     * @param request - input for the feature
     */
    void takeAction(Request request);
}
