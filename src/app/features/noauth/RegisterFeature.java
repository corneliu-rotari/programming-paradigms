package app.features.noauth;

import app.App;
import io.input.action.Request;
import app.features.FeatureFactory;
import app.features.ActionTacker;

public final class RegisterFeature implements ActionTacker {
    /**
     * Check if the user already exists in the database
     * Calls the LoginFeature if successful
     * @param request - input for the feature
     */
    @Override
    public void takeAction(final Request request) {
        App app = App.getInstance();
        if (app.getDatabase().addUser(request.getCredentials())) {
            FeatureFactory.createAction("login").takeAction(request);
        }
    }
}
