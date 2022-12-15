package app.features.auth;

import app.App;
import app.features.FeatureType;
import io.input.action.Action;
import app.features.ActionTacker;
import components.movie.Movie;
import components.user.User;
import io.output.Output;
import io.output.response.Response;

public final class LikeFeature implements ActionTacker {

    /**
     * Likes a watched movie
     * @param action - input for the feature
     */
    @Override
    public void takeAction(final Action action) {
        User user = App.getInstance().getCurrentUser();
        Movie movie = App.getInstance().getChosenMovie();
        if (user.getWatchedMovies().contains(movie)) {
            movie.setNumLikes(movie.getNumLikes() + 1);
            user.addMovieByFeature(movie, FeatureType.LIKE);
            Output.getInstance().addToTree(new Response.Builder().user().movies().build());
        } else {
            Output.getInstance().addToTree(new Response.Builder().fail().build());
        }
    }
}
