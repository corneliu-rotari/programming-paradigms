package app.action.auth;

import app.App;
import app.action.Action;
import app.action.ActionTacker;
import components.user.User;

public class WatchAction implements ActionTacker {
    @Override
    public void takeAction(Action action) {
        User user = App.getInstance().getCurrentUser();

    }
}
