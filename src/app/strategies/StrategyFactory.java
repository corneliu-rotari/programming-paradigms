package app.strategies;

import app.strategies.strategy.BackStrategy;
import app.strategies.strategy.ChangePageStrategy;
import app.strategies.strategy.DatabaseStrategy;
import app.strategies.strategy.OnPageStrategy;
import io.input.request.Request;

import java.util.Objects;

public final class StrategyFactory {
    private enum StrategyType {
        CHANGEPAGE("change page"),
        BACK("back"),
        DATABASE("database"),
        ONPAGE("on page");

        private final String type;
        StrategyType(final String type) {
            this.type = type;
        }

        public static StrategyType fromString(final String text) {
            for (StrategyType strategy : StrategyType.values()) {
                if (strategy.type.equalsIgnoreCase(text)) {
                    return strategy;
                }
            }
            return null;
        }
    }

    /**
     * Creates a specific strategy based on the name.
     * {@link Strategy}
     * @param request String the name of the strategy.
     * @return the created strategy
     */
    public static Strategy createStrategy(final Request request) {
        return switch (Objects.requireNonNull(StrategyType.fromString(request.getType()))) {
            case CHANGEPAGE -> new ChangePageStrategy(request);
            case BACK -> new BackStrategy(request);
            case ONPAGE -> new OnPageStrategy(request);
            case DATABASE -> new DatabaseStrategy(request);
        };
    }

    private StrategyFactory() {
    }
}
