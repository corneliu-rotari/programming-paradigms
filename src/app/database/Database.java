package app.database;

import components.genre.GenreManager;
import components.movie.Movie;
import components.user.Credentials;
import components.user.User;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Stores the information of the application
 */
public final class Database {
    private ArrayList<User> users;
    private ArrayList<Movie> movies;
    @Getter private HashMap<String, GenreManager> subscribedGenres;

    public Database(final ArrayList<User> users, final ArrayList<Movie> movies) {
        this.users = users;
        this.movies = movies;
        this.subscribedGenres = new HashMap<>();
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

    public boolean addMovie(final Movie movie) {
        for (Movie movieInDb : movies) {
            if (movieInDb.getName().equals(movie.getName())) {
                return false;
            }
        }
        movies.add(movie);
        return true;
    }

    public Movie getMovie(final String nameMovie) {
        for (Movie movie : movies) {
            if (movie.getName().equals(nameMovie)) {
                return movie;
            }
        }
        return null;
    }

    public boolean deleteMovie(final String nameMovie) {
        return movies.removeIf(movie -> movie.getName().equals(nameMovie));
    }
}
