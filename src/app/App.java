package app;

import io.database.Database;
import io.input.Input;

public class App {
    private static App instance= null;
    private Database database;
    private App(Input input) {
        this.database = new Database(input.getUsers(), input.getMovies());
    }

    public synchronized static App getInstance(Input input) {
        if (instance == null) {
            instance = new App(input);
        }
        return instance;
    }
}
