package app.features.auth;

import app.App;
import app.features.FeatureCommand;
import components.user.account.Standard;
import io.input.action.Request;
import components.user.User;
import components.user.account.Premium;

public final class BuyPremiumFeature implements FeatureCommand {
    /**
     * Changes the users type from standard to premium
     * @param request - input for the feature
     */
    @Override
    public void takeAction(final Request request) {
        User activeUser = App.getInstance().getCurrentUser();
        if (activeUser.getCredentials().getAccountType().equals(Standard.TYPE)) {
            activeUser.subtractTokens(Premium.PRICE);
            activeUser.getCredentials().setAccountType(Premium.TYPE);
            activeUser.setNumFreePremiumMovies(Premium.NR_FREE_MOVIES);
        }
    }
}
