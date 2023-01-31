package app.features.auth;

import app.App;
import app.features.FeatureCommand;
import components.genre.GenreManager;
import io.input.request.Request;
import io.output.Output;
import io.output.response.Response;

import java.util.HashMap;

/**
 * Subscribes the user to a specific genre,
 * so if the new movies are added or old movies are deleted, the user is notified.
 */
public final class SubscribeFeature implements FeatureCommand {

    /**
     * Subscribes the user to a specific genre.
     * Checks if the request is null and if the user is already subscribed to this genre.
     * It creates a new GenreManager if the genre was never subscribed to by any other user.
     * Outputs if the addition failed.
     * <p>
     * {@link Output}
     * <p>
     * {@link GenreManager}
     * @param request input for the feature
     */
    @Override
    public void doCommand(final Request request) {
        if (request == null) {
            return;
        }

        App app = App.getInstance();

        HashMap<String, GenreManager> database = app.getDatabase().getSubscribedGenres();
        GenreManager genreManager;

        if (database.containsKey(request.getSubscribedGenre())) {
            genreManager = database.get(request.getSubscribedGenre());
            if (!genreManager.contains(app.getCurrentUser())) {
                genreManager.subscribe(app.getCurrentUser());
            } else {
                Output.getInstance().addToTree(new Response.Builder().fail().build());
            }
        } else {
            genreManager = new GenreManager();
            genreManager.subscribe(app.getCurrentUser());
            database.put(request.getSubscribedGenre(), genreManager);
        }
    }
}
