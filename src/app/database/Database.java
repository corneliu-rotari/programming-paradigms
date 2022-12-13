package app.database;

import components.movie.Movie;
import components.user.Credentials;
import components.user.User;

import java.util.ArrayList;

public class Database {
    private ArrayList <User> users;
    private ArrayList <Movie> movies;

    public Database(ArrayList<User> users, ArrayList<Movie> movies) {
        this.users = users;
        this.movies = movies;
    }

    public boolean addUser(Credentials credentials) {
        if (authUser(credentials) != null) {
            return false;
        }
        User user = new User(credentials);
        users.add(user);
        return true;
    }

    public void printUsers() {
        for (User user :
                users) {
            System.out.println(user.getCredentials().getName());
        }
    }

    public ArrayList<Movie> getMovies(String userCountry) {
        ArrayList<Movie> ableToSee = new ArrayList<>();
        for (Movie movie : movies) {
            if (!movie.getCountriesBanned().contains(userCountry)) {
                ableToSee.add(movie);
            }
        }
        return ableToSee;
    }

    public User authUser(Credentials credentials) {
        for (User user : users) {
            if (credentials.equals(user.getCredentials())) {
                return user;
            }
        }
        return null;
    }

    public Movie findMovie(String startsWith) {
        return null;
    }
}
