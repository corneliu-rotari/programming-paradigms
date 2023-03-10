package app.features.auth;

import app.App;
import app.features.FeatureCommand;
import components.user.User;
import components.user.account.Premium;
import io.input.request.Request;

public final class BuyPremiumFeature implements FeatureCommand {
    /**
     * Changes the users type from standard to premium
     * @param request input for the feature
     */
    @Override
    public void doCommand(final Request request) {
        User activeUser = App.getInstance().getCurrentUser();
        if (activeUser.getCredentials().getAccountType().equals("standard")) {
            activeUser.subtractTokens(Premium.PRICE);
            activeUser.getCredentials().setAccountType(Premium.TYPE);
            activeUser.setNumFreePremiumMovies(Premium.NR_FREE_MOVIES);
        }
    }
}
