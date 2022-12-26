package components.user;

import app.features.FeatureFactory;
import components.movie.Movie;
import components.user.account.Premium;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.LinkedList;
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
    private LinkedList<String> notifications = new LinkedList<>();

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
    public void addMovieByFeature(final Movie newMovie,
                                  final FeatureFactory.FeatureType featureType) {
        if (featureType.equals(FeatureFactory.FeatureType.PURCHASE)) {
            this.purchasedMovies.add(newMovie);
        } else if (featureType.equals(FeatureFactory.FeatureType.RATE)) {
            this.ratedMovies.add(newMovie);
        } else if (featureType.equals(FeatureFactory.FeatureType.WATCH)) {
            this.watchedMovies.add(newMovie);
        } else if (featureType.equals(FeatureFactory.FeatureType.LIKE)) {
            this.likedMovies.add(newMovie);
        }

    }

    /**
     * Subtract an amount form the users token balance
     * @param amount how much the user is charged
     */
    public void subtractTokens(final int amount) {
        this.tokensCount -= amount;
    }

    /**
     * Adds to user's account token count
     * @param amount - the amount to add
     */
    public void addTokens(final int amount) {
        this.tokensCount += amount;
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
