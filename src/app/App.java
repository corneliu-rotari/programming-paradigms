package app;

import app.database.Database;
import app.pages.Page;
import app.pages.PageFactory;
import app.strategies.Strategy;
import app.strategies.StrategyFactory;
import app.strategies.strategy.ChangePageStrategy;
import components.movie.Movie;
import components.user.User;
import io.input.Input;
import io.input.action.Request;
import io.output.Output;
import io.output.response.Response;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Application Singleton
 */
public final class App {
    private static App instance = null;
    @Getter private Database database;
    @Setter private Strategy strategy;
    @Getter @Setter private User currentUser;
    @Getter private Page currentPage;
    @Getter private List<Movie> currentMovieList;
    @Getter private Movie chosenMovie;
    @Getter @Setter private LinkedList<Request> history;


    private App(final Input input) {
        this.database = new Database(input.getUsers(), input.getMovies());
        this.history = new LinkedList<>();
        this.currentPage = PageFactory.createPage();
        this.currentUser = null;
        this.currentMovieList = null;
        this.strategy = null;
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
        this.currentMovieList = getAllowedMovies();
    }

    public List<Movie> getAllowedMovies() {
        return this.database.getMovies(this.currentUser.getCredentials().getCountry());
    }

    public void setCurrentMovieList(final List<Movie> list) {
        this.currentMovieList = list;
    }

    /**
     * Sets a chosen movie from the list of the movies that the user is allowed to see.
     * Operations that use it are : Purchase, Watch, Like, Rate.
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

    public void setCurrentPage(Page currentPage) {
        this.currentPage = currentPage;
    }

    public void undoPageChange() {
        if (this.history.size() != 0) {
            this.history.pop();
            this.strategy = new ChangePageStrategy(this.history.peek());
            applyStrategy();
        } else {
            Output.getInstance().addToTree(new Response.Builder().fail().build());
        }
    }

    public void applyStrategy() {
        this.strategy.execute();
    }

    /**
     * Receives a requests and creates a strategy based on the request;
     * @param request -
     */
    public void receiveRequest(final Request request) {
        this.strategy = StrategyFactory.createStrategy(request);
    }

    public void addToHistory(Request request) {
        this.history.push(request);
    }

    public void showHistory() {
        this.history.forEach(request -> System.out.print(request.getPage() + " "));
        System.out.println();
    }

    /**
     * Closes the application
     * @throws IOException if the output is null
     */
    public void end() throws IOException {
        if (this.currentUser != null) {
            this.currentUser.getRecommendation();
        }
        instance = null;
        this.database = null;
        this.currentUser = null;
        Output.getInstance().writeToFile();
    }


}
