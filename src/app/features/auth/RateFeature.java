package app.features.auth;

import app.App;
import app.features.FeatureFactory;
import io.input.action.Action;
import app.features.ActionTacker;
import components.movie.Movie;
import components.user.User;
import io.output.Output;
import io.output.response.Response;

public final class RateFeature implements ActionTacker {
    private static final int MAX_RATING = 5;
    /**
     * If the movie was watched it can be rated.
     * The value is stored in an HasMap with the user info
     * @param action - input for the feature
     */
    @Override
    public void takeAction(final Action action) {
        User user = App.getInstance().getCurrentUser();
        Movie ratedMovie = App.getInstance().getChosenMovie();

        /* Checks if the user has watched the film and the rating is correct */
        if (user.getWatchedMovies().contains(ratedMovie)
                && action.getRate() <= MAX_RATING && action.getRate() >= 0) {
            user.addMovieByFeature(ratedMovie, FeatureFactory.FeatureType.RATE);
            ratedMovie.addRating(action.getRate());
            Output.getInstance().addToTree(new Response.Builder().user().movies().build());
        } else {
            Output.getInstance().addToTree(new Response.Builder().fail().build());
        }
    }
}
