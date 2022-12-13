package io.input;

import app.action.Action;
import components.movie.Movie;
import components.user.User;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

public final class Input {
    @Getter @Setter private ArrayList<User> users;
    @Getter @Setter private ArrayList<Movie> movies;
    @Getter @Setter private ArrayList<Action> actions;
    public Input() {
    }
}
