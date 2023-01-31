package app.features.noauth;

import app.App;
import app.features.FeatureCommand;
import io.input.request.Request;
import app.features.FeatureFactory;

/**
 * Register the user in the application.
 */
public final class RegisterFeature implements FeatureCommand {
    /**
     * Check if the user already exists in the database.
     * Calls the login feature if the user can be registered.
     * {@link LoginFeature#doCommand(Request)}
     * {@link app.database.Database#addUser(components.user.Credentials)}
     * @param request - a request object received from the input.
     */
    @Override
    public void doCommand(final Request request) {
        App app = App.getInstance();
        if (app.getDatabase().addUser(request.getCredentials())) {
            FeatureFactory.createFeature("login").doCommand(request);
        }
    }
}
