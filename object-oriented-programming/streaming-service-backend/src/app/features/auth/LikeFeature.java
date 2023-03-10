package app.features.auth;

import app.App;
import app.features.FeatureFactory;
import io.input.request.Request;
import app.features.FeatureCommand;
import components.movie.Movie;
import components.user.User;
import io.output.Output;
import io.output.response.Response;

public final class LikeFeature implements FeatureCommand {

    /**
     * Likes a watched movie
     * @param request input for the feature
     */
    @Override
    public void doCommand(final Request request) {
        User user = App.getInstance().getCurrentUser();
        Movie movie = App.getInstance().getChosenMovie();

        /* Check if the user has watched the movie */
        if (user.getWatchedMovies().contains(movie)) {
            movie.setNumLikes(movie.getNumLikes() + 1);
            user.addMovieByFeature(movie, FeatureFactory.FeatureType.LIKE);
            Output.getInstance().addToTree(new Response.Builder().user().movies().build());
        } else {
            Output.getInstance().addToTree(new Response.Builder().fail().build());
        }
    }
}
