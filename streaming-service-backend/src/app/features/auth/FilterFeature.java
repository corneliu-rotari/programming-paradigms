package app.features.auth;

import app.App;
import app.features.FeatureCommand;
import components.filter.Filter;
import components.movie.Movie;
import io.input.request.Request;
import io.output.Output;
import io.output.response.Response;

import java.util.Comparator;
import java.util.List;

/**
 * Filter are used to select specific movies based on a criteria.
 */
public final class FilterFeature implements FeatureCommand, Comparator<Movie> {
    private Filter.Sort sortCriteria = null;
    /**
     * Filters the movie list:
     * Sorts by duration and rating.
     * Contains a certain actor or a genre or both.
     * @param request input for the feature
     */
    @Override
    public void doCommand(final Request request) {
        App.getInstance().setCurrentMovieList();
        List<Movie> sortedMovies = App.getInstance().getCurrentMovieList();
        sortCriteria = request.getFilters().getSort();

        if (sortCriteria != null) {
            sortedMovies.sort(this);
        }

        Filter.OptionalFilters optionalFilters = request.getFilters().getContains();
        if (optionalFilters != null) {
            if (optionalFilters.getActors() != null) {
                optionalFilters.getActors().forEach(actor ->
                        sortedMovies.removeIf(movie -> !movie.getActors().contains(actor)));
            }

            if (optionalFilters.getGenre() != null) {
                optionalFilters.getGenre().forEach(genre ->
                        sortedMovies.removeIf(movie -> !movie.getGenres().contains(genre)));
            }
        }
        App.getInstance().setCurrentMovieList(sortedMovies);
        Output.getInstance().addToTree(new Response.Builder().user().movies().build());
    }

    @Override
    public int compare(final Movie o1, final Movie o2) {
        if (sortCriteria.getDuration() == null || o1.getDuration() == o2.getDuration()) {
            if (sortCriteria.getRating().equals("decreasing")) {
                return Double.compare(o2.getRating(), o1.getRating());
            } else {
                return Double.compare(o1.getRating(), o2.getRating());
            }
        } else {
            if (sortCriteria.getDuration().equals("decreasing")) {
                return Integer.compare(o2.getDuration(), o1.getDuration());
            } else {
                return Integer.compare(o1.getDuration(), o2.getDuration());
            }
        }
    }
}
