package components.user;

import app.features.FeatureFactory;
import components.movie.Movie;
import components.genre.GenreSubscriber;
import components.movie.MovieRecommendation;
import components.notification.Notification;
import components.user.account.Premium;
import io.output.Output;
import io.output.response.Response;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Objects;

@Getter @Setter
public final class User implements GenreSubscriber {
    private Credentials credentials;
    private int tokensCount = 0;
    private int numFreePremiumMovies = Premium.NR_FREE_MOVIES;
    private LinkedHashSet<Movie> watchedMovies = new LinkedHashSet<>();
    private LinkedHashSet<Movie> purchasedMovies = new LinkedHashSet<>();
    private LinkedHashSet<Movie> likedMovies = new LinkedHashSet<>();
    private LinkedHashSet<Movie> ratedMovies = new LinkedHashSet<>();
    private LinkedList<Notification> notifications = new LinkedList<>();

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

    /**
     * Adds the notification to the array.
     * {@link GenreSubscriber#update(Notification)}
     * @param notification the Notification
     */
    @Override
    public void update(final Notification notification) {
        this.notifications.add(notification);
    }

    /**
     * If the logged-in user is premium, at the last output it prints a new movie recommendation.
     * {@link MovieRecommendation}
     */
    public void getRecommendation() {
        if (credentials.getAccountType().equals(Premium.TYPE)) {
            MovieRecommendation recommendation = new MovieRecommendation(this.likedMovies);
            update(new Notification("Recommendation", recommendation.getMovieName()));
            Output.getInstance().addToTree(new Response.Builder().user().noMovieList().build());
        }
    }
}
