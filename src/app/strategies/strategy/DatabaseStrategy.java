package app.strategies.strategy;

import app.App;
import app.database.Database;
import app.strategies.Strategy;
import components.movie.Movie;
import io.input.action.Request;
import io.output.Output;
import io.output.response.Response;

public class DatabaseStrategy extends Strategy {

    private final String ADD = "add";
    private final String DELETE = "delete";

    public DatabaseStrategy(Request request) {
        super(request);
    }

    @Override
    public void execute() {
        if (request == null) {
            return;
        }

        if (request.getFeature().equals(ADD)) {
            addMovie();
        } else if (request.getFeature().equals(DELETE)) {
            deleteMovie();
        }
    }

    private void addMovie() {
        Database database = App.getInstance().getDatabase();
        Movie movieToAdd = request.getAddedMovie();
        if (database.addMovie(movieToAdd)) {
            for (String genre : movieToAdd.getGenres()) {
                if (database.getSubscribedGenres().containsKey(genre)){
                    database.getSubscribedGenres().get(genre).notifySubscribers(movieToAdd, ADD.toUpperCase());
                }
            }
        } else {
            Output.getInstance().addToTree(new Response.Builder().fail().build());
        }
    }

    private void deleteMovie() {
        Database database = App.getInstance().getDatabase();
        Movie foundMovie = database.getMovie(request.getDeletedMovie());
        if (foundMovie != null) {
            foundMovie.getGenres().forEach(s -> {
                if (database.getSubscribedGenres().containsKey(s)) {
                    database.getSubscribedGenres().get(s).notifySubscribers(foundMovie, DELETE.toUpperCase());
                }
            });
        } else {
            Output.getInstance().addToTree(new Response.Builder().fail().build());
        }
    }
}
