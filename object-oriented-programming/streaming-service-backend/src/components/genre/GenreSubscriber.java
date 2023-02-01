package components.genre;

import components.notification.Notification;

public interface GenreSubscriber {
    /**
     * Receives a notification and updates the information
     * {@link Notification}
     * @param notification the Notification
     */
    void update(Notification notification);
}
