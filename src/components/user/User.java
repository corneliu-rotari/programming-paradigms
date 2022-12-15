package components.user;

import app.features.FeatureType;
import components.movie.Movie;
import components.user.account.Premium;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Objects;

@Getter @Setter
public final class User {
    private Credentials credentials;
    private int tokensCount = 0;
    private int numFreePremiumMovies = Premium.NR_FREE_MOVIES;
    private LinkedHashSet<Movie> watchedMovies = new LinkedHashSet<>();
    private LinkedHashSet<Movie> purchasedMovies = new LinkedHashSet<>();
    private LinkedHashSet<Movie> likedMovies = new LinkedHashSet<>();
    private LinkedHashSet<Movie> ratedMovies = new LinkedHashSet<>();

    public User(final Credentials credentials) {
        this.credentials = credentials;
    }

    public User() {
    }

    /**
     * Adds a movie to specific Set of movies based on the feature type
     * @param newMovie - movie to be added
     * @param featureType - Feature type form which it was called
     */
    public void addMovieByFeature(final Movie newMovie, final FeatureType featureType) {
        if (featureType.equals(FeatureType.PURCHASE)) {
            this.purchasedMovies.add(newMovie);
        } else if (featureType.equals(FeatureType.RATE)) {
            this.ratedMovies.add(newMovie);
        } else if (featureType.equals(FeatureType.WATCH)) {
            this.watchedMovies.add(newMovie);
        } else if (featureType.equals(FeatureType.LIKE)) {
            this.likedMovies.add(newMovie);
        }

    }


    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return credentials.equals(user.credentials);
    }

    @Override
    public int hashCode() {
        return Objects.hash(credentials);
    }
}
