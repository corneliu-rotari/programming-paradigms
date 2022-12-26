package app.features.auth;

import app.App;
import app.features.FeatureCommand;
import io.input.action.Request;
import components.movie.Movie;
import io.output.Output;
import io.output.response.Response;

import java.util.List;

/**
 * Search for movie name
 */
public final class SearchFeature implements FeatureCommand {
    /**
     * Search from the movie list based on beginning of the movie name
     * @param request - input for the feature
     */
    @Override
    public void takeAction(final Request request) {
        List<Movie> movieList = App.getInstance().getCurrentMovieList().stream().filter(movie ->
                                    movie.getName().startsWith(request.getStartsWith())).toList();

        App.getInstance().setCurrentMovieList(movieList);
        Output.getInstance().addToTree(new Response.Builder().user().movies().build());
    }
}
