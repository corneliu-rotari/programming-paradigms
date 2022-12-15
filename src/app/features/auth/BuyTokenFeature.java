package app.features.auth;

import app.App;
import io.input.action.Action;
import app.features.ActionTacker;
import components.user.User;

public final class BuyTokenFeature implements ActionTacker {
    /**
     * Converts user amount of money into token currency.
     * @param action - input for the feature
     */
    @Override
    public void takeAction(final Action action) {
        User activeUser = App.getInstance().getCurrentUser();
        int balance = Integer.parseInt(activeUser.getCredentials().getBalance());
        activeUser.setTokensCount(activeUser.getTokensCount() + action.getCount());
        activeUser.getCredentials().setBalance(Integer.toString(balance - action.getCount()));
    }
}
