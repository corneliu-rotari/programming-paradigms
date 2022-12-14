package app.action.auth;

import app.App;
import app.action.Action;
import app.action.ActionTacker;
import components.user.User;

public class BuyTokenAction implements ActionTacker {
    @Override
    public void takeAction(Action action) {
        User activeUser = App.getInstance().getCurrentUser();
        int balance = Integer.parseInt(activeUser.getCredentials().getBalance());
        activeUser.setTokensCount(activeUser.getTokensCount() + action.getCount());
        activeUser.getCredentials().setBalance(Integer.toString(balance - action.getCount()));
    }
}
