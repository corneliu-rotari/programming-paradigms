package app.action.auth;

import app.App;
import app.action.Action;
import app.action.ActionTacker;
import components.filter.Filter;
import components.movie.Movie;
import io.output.Output;
import io.output.response.Response;

import java.util.List;

public class FilterAction implements ActionTacker {
    @Override
    public void takeAction(Action action) {
        Filter.Sort sort = action.getFilters().getSort();
        List<Movie> sortedMovies = App.getInstance().getCurrentMovieList();

//        if

        Output.getInstance().addToTree(new Response.Builder().user().movies(sortedMovies).build());
    }
}
