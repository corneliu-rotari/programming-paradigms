package components.genre;

import components.notification.Notification;

public interface GenreSubscriber {
    void update(Notification notification);
}
