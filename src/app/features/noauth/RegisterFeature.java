package app.features.noauth;

import app.App;
import io.input.action.Action;
import app.features.FeatureFactory;
import app.features.ActionTacker;

public final class RegisterFeature implements ActionTacker {
    /**
     * Check if the user already exists in the database
     * Calls the LoginFeature if successful
     * @param action - input for the feature
     */
    @Override
    public void takeAction(final Action action) {
        App app = App.getInstance();
        if (app.getDatabase().addUser(action.getCredentials())) {
            FeatureFactory.createAction("login").takeAction(action);
        }
    }
}
