package app.strategies;

import io.input.request.Request;

/**
 * Predefined strategy parent class.
 */
public abstract class Strategy {
    protected Request request;

    public Strategy(final Request request) {
        this.request = request;
    }

    /**
     * Executes a predefined strategy.
     */
    public abstract void execute();
}
