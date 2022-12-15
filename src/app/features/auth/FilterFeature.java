package app.features.auth;

import app.App;
import io.input.action.Action;
import app.features.ActionTacker;
import components.filter.Filter;
import components.movie.Movie;
import components.movie.MovieSorter;
import io.output.Output;
import io.output.response.Response;

import java.util.List;

/**
 * Filter are used to select specific movies based on a criteria.
 */
public class FilterFeature implements ActionTacker {
    /**
     * Filters the movie list:
     * Sorts - by duration and rating.
     * Contains - a certain actor or a genre or both.
     * @param action - input for the feature
     */
    @Override
    public void takeAction(final Action action) {
        App.getInstance().setCurrentMovieList();
        List<Movie> sortedMovies = App.getInstance().getCurrentMovieList();
        if (action.getFilters().getSort() != null) {
            sortedMovies.sort(new MovieSorter(action.getFilters().getSort()));
        }
        Filter.OptionalFilters optionalFilters = action.getFilters().getContains();
        if (optionalFilters != null) {
            if (optionalFilters.getActors() != null) {
                for (String actor: optionalFilters.getActors()) {
                    sortedMovies.removeIf(movie -> !movie.getActors().contains(actor));
                }
            }
            if (optionalFilters.getGenre() != null) {
                for (String genre: optionalFilters.getGenre()) {
                    sortedMovies.removeIf(movie -> !movie.getGenres().contains(genre));
                }
            }
        }
        App.getInstance().setCurrentMovieList(sortedMovies);
        Output.getInstance().addToTree(new Response.Builder().user().movies().build());
    }

}
