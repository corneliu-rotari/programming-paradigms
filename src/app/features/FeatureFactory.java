package app.features;

import app.features.auth.BuyPremiumFeature;
import app.features.auth.BuyTokenFeature;
import app.features.auth.FilterFeature;
import app.features.auth.LikeFeature;
import app.features.auth.PurchaseFeature;
import app.features.auth.RateFeature;
import app.features.auth.SearchFeature;
import app.features.auth.WatchFeature;
import app.features.noauth.LoginFeature;
import app.features.noauth.RegisterFeature;

import java.util.Objects;

/**
 * Factory that creates an Feature from a string
 */
public final class FeatureFactory {
    /**
     * Creates a feature from a String
     * @param type - String name of the feature
     * @return - an Feature that implements FeatureCommand
     */
    public static FeatureCommand createFeature(final String type) {
        return switch (Objects.requireNonNull(FeatureType.fromString(type))) {
            case LOGIN -> new LoginFeature();
            case REGISTER -> new RegisterFeature();
            case SEARCH -> new SearchFeature();
            case FILTER -> new FilterFeature();
            case PURCHASE -> new PurchaseFeature();
            case WATCH -> new WatchFeature();
            case LIKE -> new LikeFeature();
            case RATE -> new RateFeature();
            case BUYPREAMIUM -> new BuyPremiumFeature();
            case BUYTOKEN -> new BuyTokenFeature();
        };
    }
    private FeatureFactory() {
    }

    /**
     * Type of Feature that can happen in the application
     */
    public enum FeatureType {
        LOGIN("login"),
        REGISTER("register"),
        SEARCH("search"),
        FILTER("filter"),
        PURCHASE("purchase"),
        WATCH("watch"),
        LIKE("like"),
        RATE("rate"),
        BUYPREAMIUM("buy premium account"),
        BUYTOKEN("buy tokens");


        private final String type;

        FeatureType(final String type) {
            this.type = type;
        }

        /**
         * Converts a string to a Feature type
         * @param text - string to convert
         * @return Type of Feature / null
         */
        public static FeatureType fromString(final String text) {
            for (FeatureType actionType : FeatureType.values()) {
                if (actionType.type.equalsIgnoreCase(text)) {
                    return actionType;
                }
            }
            return null;
        }

    }
}
