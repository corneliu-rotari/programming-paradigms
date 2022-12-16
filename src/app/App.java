package app;

import app.database.Database;
import app.pages.Page;
import app.pages.PageFactory;
import components.movie.Movie;
import components.user.User;
import io.input.Input;
import io.output.Output;
import io.output.response.Response;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Application Singleton
 */
public final class App {
    private static App instance = null;
    @Getter private Database database;
    @Getter @Setter private User currentUser;
    @Getter @Setter private Page currentPage;
    @Getter private List<Movie> currentMovieList;
    @Getter private Movie chosenMovie;


    private App(final Input input) {
        this.database = new Database(input.getUsers(), input.getMovies());
        this.currentPage = PageFactory.createPage();
        this.currentUser = null;
        this.currentMovieList = null;
    }

    /**
     * Gets a new instance of the application
     * @param input - Input information for the database
     * @return - the app instance
     */
    public static synchronized App getInstance(final Input input) {
        if (instance == null) {
            instance = new App(input);
        }
        return instance;
    }

    /**
     * Get the app instance
     * @return App object
     */
    public static synchronized App getInstance() {
        return instance;
    }


    /**
     * Takes from the database a list of movies the user is allowed to see
     */
    public void setCurrentMovieList() {
        this.currentMovieList = this.database.getMovies(this.currentUser.
                                getCredentials().getCountry());
    }

    public void setCurrentMovieList(final List<Movie> list) {
        this.currentMovieList = list;
    }

    /**
     * Sets a single movie to the user's currentMovieList
     * @param movieName - full name of the movie
     * @return - if it was added or not
     */
    public boolean setChosenMovie(final String movieName) {
        boolean added = false;
        for (Movie movie: this.currentMovieList) {
            if (movie.getName().equals(movieName)) {
                this.chosenMovie = movie;
                added = true;
                this.currentMovieList = new ArrayList<>();
                this.currentMovieList.add(movie);
            }
        }
        if (!added) {
            Output.getInstance().addToTree(new Response.Builder().fail().build());
        } else {
            Output.getInstance().addToTree(new Response.Builder().user().movies().build());
        }
        return added;
    }

    /**
     * Closes the application
     * @throws IOException if the output is null
     */
    public void end() throws IOException {
        Output.getInstance().writeToFile();
        instance = null;
        this.database = null;
        this.currentUser = null;
        Output.destroy();
    }

}
