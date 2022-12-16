package app.database;

import components.movie.Movie;
import components.user.Credentials;
import components.user.User;

import java.util.ArrayList;

/**
 * Stores the information of the application
 */
public final class Database {
    private ArrayList<User> users;
    private ArrayList<Movie> movies;

    public Database(final ArrayList<User> users, final ArrayList<Movie> movies) {
        this.users = users;
        this.movies = movies;
    }

    /**
     * Registers a new user to the Database
     * @param credentials - user information
     * @return if the addition was successful
     */
    public boolean addUser(final Credentials credentials) {
        if (authUser(credentials) != null) {
            return false;
        }
        User user = new User(credentials);
        users.add(user);
        return true;
    }

    /**
     * Creates a movie list of what the user is able to see based on users country
     * @param userCountry - user's country
     * @return available movies
     */
    public ArrayList<Movie> getMovies(final String userCountry) {
        ArrayList<Movie> ableToSee = new ArrayList<>();
        for (Movie movie : movies) {
            if (!movie.getCountriesBanned().contains(userCountry)) {
                ableToSee.add(movie);
            }
        }
        return ableToSee;
    }

    /**
     * Checks if the user can be logged in the system
     * @param credentials - name and password
     * @return the user found / null if not found.
     */
    public User authUser(final Credentials credentials) {
        for (User user : users) {
            if (credentials.equals(user.getCredentials())) {
                return user;
            }
        }
        return null;
    }
}
