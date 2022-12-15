package io.input;

import io.input.action.Action;
import components.movie.Movie;
import components.user.User;
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
    private ArrayList<Action> actions;
    public Input() {
    }
}
