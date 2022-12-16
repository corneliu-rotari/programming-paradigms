package app.features.auth;

import app.App;
import io.input.action.Action;
import app.features.ActionTacker;
import components.movie.Movie;
import io.output.Output;
import io.output.response.Response;

import java.util.List;

/**
 * Search for movie name
 */
public final class SearchFeature implements ActionTacker {
    /**
     * Search from the movie list based on beginning of the movie name
     * @param action - input for the feature
     */
    @Override
    public void takeAction(final Action action) {
        List<Movie> movieList = App.getInstance().getCurrentMovieList().stream().filter(movie ->
                                    movie.getName().startsWith(action.getStartsWith())).toList();

        App.getInstance().setCurrentMovieList(movieList);
        Output.getInstance().addToTree(new Response.Builder().user().movies().build());
    }
}
