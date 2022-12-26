package app.features.auth;

import app.App;
import app.features.FeatureFactory;
import io.input.action.Request;
import app.features.FeatureCommand;
import components.movie.Movie;
import components.user.User;
import io.output.Output;
import io.output.response.Response;

public final class RateFeature implements FeatureCommand {
    private static final int MAX_RATING = 5;
    /**
     * If the movie was watched it can be rated.
     * The value is stored in an HasMap with the user info
     * @param request - input for the feature
     */
    @Override
    public void doCommand(final Request request) {
        User user = App.getInstance().getCurrentUser();
        Movie ratedMovie = App.getInstance().getChosenMovie();

        /* Checks if the user has watched the film and the rating is correct */
        if (user.getWatchedMovies().contains(ratedMovie)
                && request.getRate() <= MAX_RATING && request.getRate() >= 0) {
            user.addMovieByFeature(ratedMovie, FeatureFactory.FeatureType.RATE);
            ratedMovie.addRating(request.getRate());
            Output.getInstance().addToTree(new Response.Builder().user().movies().build());
        } else {
            Output.getInstance().addToTree(new Response.Builder().fail().build());
        }
    }
}
