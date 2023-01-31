package app.strategies.strategy;

import app.App;
import app.strategies.Strategy;
import io.input.request.Request;

public final class BackStrategy extends Strategy {
    public BackStrategy(final Request request) {
        super(request);
    }

    /**
     * Goes back to the last visted page
     * {@link App#undoPageChange()}
     */
    @Override
    public void execute() {
        if (request == null) {
            return;
        }
        App.getInstance().undoPageChange();
    }
}
