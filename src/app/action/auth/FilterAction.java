package app.action.auth;

import app.App;
import app.action.Action;
import app.action.ActionTacker;
import components.filter.Filter;
import components.movie.Movie;
import components.movie.MovieSorter;
import io.output.Output;
import io.output.response.Response;

import java.util.List;

public class FilterAction implements ActionTacker {
    @Override
    public void takeAction(Action action) {
        List<Movie> sortedMovies = App.getInstance().getCurrentMovieList();
        sortedMovies.sort(new MovieSorter(action.getFilters()));
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
        Output.getInstance().addToTree(new Response.Builder().user().movies(sortedMovies).build());
    }

}
