package components.notification;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Notification {
    private String movieName;
    private String message;


    public Notification(final String message, final String movieName) {
        this.message = message;
        this.movieName = movieName;
    }
}
