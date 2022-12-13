package io.output.error;

import components.movie.Movie;
import components.user.User;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

public class Error {
    @Setter @Getter private String error;
    @Setter @Getter private ArrayList<Movie> currentMovieList = new ArrayList<>();
    @Setter @Getter private User currentUser;
}
