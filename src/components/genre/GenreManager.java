package components.genre;

import components.movie.Movie;
import components.notification.Notification;
import components.user.User;

import java.util.HashSet;

/**
 * The Subscribed object by the User.
 * Extends HashSet of Genre Subscribers
 * {@link GenreSubscriber}
 */
public final class GenreManager extends HashSet<GenreSubscriber> {

    public GenreManager() {
        super();
    }

    /**
     * Adds a new subscriber to the Hash Set.
     * @param subscriber the new subscriber.
     * @return if the action is successful or not.
     */
    public boolean subscribe(final GenreSubscriber subscriber) {
        return this.add(subscriber);
    }

    /**
     * Notify every subscriber of the changes that happened to the movie database
     * {@link app.strategies.strategy.DatabaseStrategy}
     * @param movie the movie that the changes happened to.
     * @param message the type of changes that happened.
     */
    public void notifySubscribers(final Movie movie, final String message) {
        for (GenreSubscriber subscriber : this) {
            User user = (User) subscriber;
            Notification notification = new Notification(message, movie.getName());
            if (user.getNotifications().size() != 0
                    && user.getNotifications().getLast().equals(notification)) {
                return;
            }
            if (!movie.getCountriesBanned().contains(user.getCredentials().getCountry())) {
                subscriber.update(notification);
            }
        }
    }
}
