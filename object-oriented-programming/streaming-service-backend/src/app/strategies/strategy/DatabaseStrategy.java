package app.strategies.strategy;

import app.App;
import app.database.Database;
import app.strategies.Strategy;
import components.movie.Movie;
import io.input.request.Request;
import io.output.Output;
import io.output.response.Response;

public final class DatabaseStrategy extends Strategy {

    private final String add = "add";
    private final String delete = "delete";

    public DatabaseStrategy(final Request request) {
        super(request);
    }

    /**
     * Checks the request of the type of the operations and calls the specified function.
     */
    @Override
    public void execute() {
        if (request == null) {
            return;
        }

        if (request.getFeature().equals(add)) {
            addMovie();
        } else if (request.getFeature().equals(delete)) {
            deleteMovie();
        }
    }

    private void addMovie() {
        Database database = App.getInstance().getDatabase();
        Movie movieToAdd = request.getAddedMovie();
        if (database.addMovie(movieToAdd)) {
            for (String genre : movieToAdd.getGenres()) {
                if (database.getSubscribedGenres().containsKey(genre)) {
                    database.getSubscribedGenres().get(genre)
                            .notifySubscribers(movieToAdd, add.toUpperCase());
                }
            }
        } else {
            Output.getInstance().addToTree(new Response.Builder().fail().build());
        }
    }

    private void deleteMovie() {
        Database database = App.getInstance().getDatabase();
        Movie foundMovie = database.deleteMovie(request.getDeletedMovie());
        if (foundMovie != null) {
            foundMovie.getGenres().forEach(s -> {
                if (database.getSubscribedGenres().containsKey(s)) {
                    database.getSubscribedGenres().get(s)
                            .notifySubscribers(foundMovie, delete.toUpperCase());
                }
            });
        } else {
            Output.getInstance().addToTree(new Response.Builder().fail().build());
        }
    }
}
