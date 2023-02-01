package app.features.auth;

import app.App;
import app.features.FeatureCommand;
import app.features.FeatureFactory;
import components.movie.Movie;
import components.user.User;
import io.input.request.Request;
import io.output.Output;
import io.output.response.Response;

public final class WatchFeature implements FeatureCommand {
    /**
     * Adds the movie to a user's watched list
     * @param request input for the feature
     */
    @Override
    public void doCommand(final Request request) {
        User user = App.getInstance().getCurrentUser();
        Movie movie = App.getInstance().getChosenMovie();

        /* If the movie is already purchased it can be watched */
        if (user.getPurchasedMovies().contains(movie)) {
            if (!user.getWatchedMovies().contains(movie)) {
                user.addMovieByFeature(movie, FeatureFactory.FeatureType.WATCH);
            }
            Output.getInstance().addToTree(new Response.Builder().user().movies().build());
        } else {
            Output.getInstance().addToTree(new Response.Builder().fail().build());
        }
    }
}
