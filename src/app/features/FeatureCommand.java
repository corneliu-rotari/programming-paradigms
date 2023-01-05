package app.features;

import io.input.request.Request;

/**
 * Interface for Command Design Pattern
 */
public interface FeatureCommand {
    /**
     * Changes the state of the Application
     * @param request input for the feature
     */
    void doCommand(Request request);
}
