package components.user;

import components.movie.Movie;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Objects;

public class User {
    @Getter @Setter private Credentials credentials;
    @Getter @Setter private int tokensCount = 0;
    @Getter @Setter private int numFreePremiumMovies = 15;
    @Getter @Setter private ArrayList<Movie> watchedMovies = new ArrayList<>();;
    @Getter @Setter private ArrayList<Movie> purchasedMovies = new ArrayList<>();;
    @Getter @Setter private ArrayList<Movie> likedMovies = new ArrayList<>();;
    @Getter @Setter private ArrayList<Movie> ratedMovies = new ArrayList<>();;

    public User(Credentials credentials) {
        this.credentials = credentials;
    }

    public User() {
    }

    public void addPurchase(Movie newMovie) {
        this.purchasedMovies.add(newMovie);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return credentials.equals(user.credentials);
    }

    @Override
    public int hashCode() {
        return Objects.hash(credentials);
    }
}
