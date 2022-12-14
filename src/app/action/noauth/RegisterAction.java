package app.action.noauth;

import app.App;
import app.action.Action;
import app.action.ActionFactory;
import app.action.ActionTacker;

public class RegisterAction implements ActionTacker {
    @Override
    public void takeAction(Action action) {
        App app = App.getInstance();
        if (app.getDatabase().addUser(action.getCredentials())) {
            ActionFactory.createAction("login").takeAction(action);
        }
    }
}
