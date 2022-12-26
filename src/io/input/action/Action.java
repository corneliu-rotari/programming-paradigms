package io.input.action;

import components.filter.Filter;
import components.movie.Movie;
import components.user.Credentials;
import lombok.Getter;
import lombok.Setter;

/**
 * Request format
 */
@Getter @Setter
public class Action {
    private String type;
    private String page;
    private String movie;
    private String feature;
    private Credentials credentials;
    private String startsWith;
    private Filter filters;
    private int count;
    private int rate;
    private String subscribedGenre;
    private Movie addedMovie;
    private String deletedMovie;
}
