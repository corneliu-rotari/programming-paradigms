package io.input;

import components.movie.Movie;
import components.user.User;
import io.input.request.Request;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

/**
 * Input format
 */
@Getter @Setter
public final class Input {
    private ArrayList<User> users;
    private ArrayList<Movie> movies;
    private ArrayList<Request> actions;
    public Input() {
    }
}
