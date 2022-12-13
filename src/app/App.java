package app;

import components.movie.Movie;
import app.pages.Page;
import app.pages.PageFactory;
import components.user.User;
import app.database.Database;
import io.input.Input;
import io.output.Output;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.ArrayList;

public class App {
    private static App instance= null;
    @Getter private Database database;
    @Getter @Setter private User currentUser = null;
    @Getter @Setter private Page currentPage;
    @Getter private ArrayList<Movie> currentMovieList;


    private App(Input input) {
        this.database = new Database(input.getUsers(), input.getMovies());
        this.currentPage = PageFactory.createPage();
        this.currentUser = null;
        this.currentMovieList = null;
    }

    public synchronized static App getInstance(Input input) {
        if (instance == null) {
            instance = new App(input);
        }
        return instance;
    }

    public synchronized static App getInstance() {
        return instance;
    }

    public void end() throws IOException {
        Output.getInstance().writeToFile();
        instance = null;
        this.database = null;
        this.currentUser = null;
        Output.destory();
    }

    public void setCurrentMovieList() {
        this.currentMovieList = this.database.getMovies(this.currentUser.getCredentials().getCountry());
    }
}
