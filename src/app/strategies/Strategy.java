package app.strategies;

import io.input.action.Request;

public abstract class Strategy {
    protected Request request;

    public Strategy(Request request) {
        this.request = request;
    }

    public abstract void execute();
}
