package app.action.auth;

import app.App;
import app.action.Action;
import app.action.ActionTacker;
import app.database.Database;
import components.movie.Movie;
import io.output.Output;
import io.output.response.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SearchAction implements ActionTacker {
    @Override
    public void takeAction(Action action) {
        List<Movie> movieList = App.getInstance().getCurrentMovieList().stream().filter(movie -> movie.getName()
                .startsWith(action.getStartsWith())).toList();
        Output.getInstance().addToTree(new Response.Builder().user().movies(movieList).build());
    }
}
