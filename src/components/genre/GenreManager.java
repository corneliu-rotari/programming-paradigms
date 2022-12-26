package components.genre;

import components.movie.Movie;
import components.notification.Notification;
import components.user.User;

import java.util.HashSet;

public class GenreManager {
    private HashSet<GenreSubscriber> subscribers;

    public GenreManager() {
        this.subscribers = new HashSet<>();
    }

    public boolean subscribe(GenreSubscriber subscriber) {
        return this.subscribers.add(subscriber);
    }
    
    public void notifySubscribers(final Movie movie, final String message) {
        for (GenreSubscriber subscriber : this.subscribers) {
            User user = (User) subscriber;
            Notification notification = new Notification(message, movie.getName());
            if (user.getNotifications().size() != 0 && user.getNotifications().getLast().equals(notification)) {
                return;
            }
            if (!movie.getCountriesBanned().contains(user.getCredentials().getCountry())) {
                subscriber.update(notification);
            }
        }
    }

    public boolean containsSubscriber(GenreSubscriber subscriber) {
        return this.subscribers.contains(subscriber);
    }
}
