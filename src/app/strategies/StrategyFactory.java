package app.strategies;

import app.strategies.strategy.*;
import io.input.action.Request;

import java.util.Objects;

public class StrategyFactory {
    private enum StrategyType {
        CHANGEPAGE("change page"),
        BACK("back"),
        SUBSCRIBE("subscribe"),
        DATABASE("database"),
        ONPAGE("on page");

        private final String type;
        StrategyType(String s) {
            type = s;
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

    public static Strategy createStrategy(final Request request) {
        return switch (Objects.requireNonNull(StrategyType.fromString(request.getType()))) {
            case CHANGEPAGE -> new ChangePageStrategy(request);
            case BACK -> new BackStrategy(request);
            case SUBSCRIBE -> new SubscribeStrategy(request);
            case ONPAGE -> new OnPageStrategy(request);
            case DATABASE -> new DatabaseStrategy(request);
        };
    }
}
