package components.user;

import components.movie.Movie;

import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;

public class User {
    @Getter @Setter private Credentials credentials;
    @Getter @Setter private int tokenCount;
    @Getter @Setter private ArrayList<Movie> numFreePremiumMovies;
    @Getter @Setter private ArrayList<Movie> watchedMovies;
    @Getter @Setter private ArrayList<Movie> likedMovies;
    @Getter @Setter private ArrayList<Movie> ratedMovies;
}
