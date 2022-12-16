package app.features.auth;

import app.App;
import components.user.account.Standard;
import io.input.action.Action;
import app.features.ActionTacker;
import components.user.User;
import components.user.account.Premium;

public final class BuyPremiumFeature implements ActionTacker {
    /**
     * Changes the users type from standard to premium
     * @param action - input for the feature
     */
    @Override
    public void takeAction(final Action action) {
        User activeUser = App.getInstance().getCurrentUser();
        if (activeUser.getCredentials().getAccountType().equals(Standard.TYPE)) {
            activeUser.subtractTokens(Premium.PRICE);
            activeUser.getCredentials().setAccountType(Premium.TYPE);
            activeUser.setNumFreePremiumMovies(Premium.NR_FREE_MOVIES);
        }
    }
}
