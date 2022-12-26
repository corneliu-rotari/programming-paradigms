package app.strategies.strategy;

import app.App;
import app.strategies.Strategy;
import io.input.action.Request;

public class BackStrategy extends Strategy {
    public BackStrategy(final Request request) {
        super(request);
    }

    @Override
    public void execute() {
        if (request == null) {
            return;
        }
        App.getInstance().undoPageChange();
    }
}
