package app.strategies.strategy;

import app.strategies.Strategy;
import io.input.action.Request;

public class BackStrategy extends Strategy {
    public BackStrategy(Request request) {
        super(request);
    }

    @Override
    public void execute() {
    }
}
