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
     * @return - an Feature that implements ActionTacker
     */
    public static ActionTacker createAction(final String type) {
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
            default -> null;
        };
    }
    private FeatureFactory() {
    }

}
