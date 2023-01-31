package app.database;

import components.genre.GenreManager;
import components.movie.Movie;
import components.user.Credentials;
import components.user.User;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Stores the information of users, movies and subscribers.
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
     * Returns if the addition of the user is successful or not.
     * Checks if the user already exists in the database, by calling
     * {@link #authUser(Credentials)}
     * @param credentials user information
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
     * Returns a list of movies that are filtered based on the country in which they are banned.
     * If the movie contains the users country in the banned list the movie is not added.
     * @param userCountry user's country
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
     * Returns the user object that has the exact same credentials.
     * If the such user does not exist the null object is returned.
     * @param credentials name and password
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

    /**
     * Returns if the addition of successful or not.
     * Checks if a movie already exists with the same name.
     * @param movie the movie to be added
     * @return boolean if the addition of successful
     */
    public boolean addMovie(final Movie movie) {
        for (Movie movieInDb : movies) {
            if (movieInDb.getName().equals(movie.getName())) {
                return false;
            }
        }
        movies.add(movie);
        return true;
    }

    /**
     * Finds a specific movie in the database based on the name.
     * @param nameMovie String name of the movie.
     * @return Movie found/ null if not found
     */
    public Movie getMovie(final String nameMovie) {
        for (Movie movie : movies) {
            if (movie.getName().equals(nameMovie)) {
                return movie;
            }
        }
        return null;
    }

    /**
     * Deletes a specified movie from the database and returns the deleted movie.
     * @param nameMovie the name of the movie.
     * @return the Movie that was deleted.
     */
    public Movie deleteMovie(final String nameMovie) {
        Movie foundMovie = getMovie(nameMovie);
        movies.removeIf(movie -> movie.getName().equals(nameMovie));
        return foundMovie;
    }
}
