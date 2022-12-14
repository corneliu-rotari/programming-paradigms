package app.action.auth;

import app.App;
import app.action.Action;
import app.action.ActionTacker;
import components.user.User;
import components.user.account.Premium;

public class BuyPremiumAction implements ActionTacker {
    @Override
    public void takeAction(Action action) {
        User activeUser = App.getInstance().getCurrentUser();
        activeUser.setTokensCount(activeUser.getTokensCount() - Premium.price);
        activeUser.getCredentials().setAccountType(Premium.type);
        activeUser.setNumFreePremiumMovies(Premium.nrFreeMovies);
    }
}
