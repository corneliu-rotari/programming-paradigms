package io.database;

import components.movie.Movie;
import components.user.User;

import java.util.ArrayList;

public class Database {
    private ArrayList <User> users;
    private ArrayList <Movie> movies;

    public Database(ArrayList<User> users, ArrayList<Movie> movies) {
        this.users = users;
        this.movies = movies;
    }
}
