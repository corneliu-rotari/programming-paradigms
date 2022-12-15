package app.features.auth;

import app.App;
import app.features.ActionTacker;
import app.features.FeatureFactory;
import components.movie.Movie;
import components.user.User;
import io.input.action.Action;
import io.output.Output;
import io.output.response.Response;

public final class WatchFeature implements ActionTacker {
    /**
     * If the movie is already purchased it can be watched
     * @param action - input for the feature
     */
    @Override
    public void takeAction(final Action action) {
        User user = App.getInstance().getCurrentUser();
        Movie movie = App.getInstance().getChosenMovie();
        if (user.getPurchasedMovies().contains(movie)) {
            user.addMovieByFeature(movie, FeatureFactory.FeatureType.WATCH);
            Output.getInstance().addToTree(new Response.Builder().user().movies().build());
        } else {
            Output.getInstance().addToTree(new Response.Builder().fail().build());
        }
    }
}
