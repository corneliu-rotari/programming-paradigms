package components.movie;

import app.App;
import com.fasterxml.jackson.annotation.JsonIgnore;
import components.user.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@EqualsAndHashCode
@Getter @Setter
public final class Movie {
    private String name;
    private int year;
    private int duration;
    private ArrayList<String> genres;
    private ArrayList<String> actors;
    private ArrayList<String> countriesBanned;
    private int numLikes = 0;
    private Double rating = (double) 0;
    private int numRatings = 0;

    @JsonIgnore
    public static final int PRICE = 2;
    @JsonIgnore
    private HashMap<User, Integer> mapOfRatings = new HashMap<>();

    /**
     * Sets a rating to a movie form the current User.
     * @param ratingAdded - the new rating from the current user
     */
    public void addRating(final int ratingAdded) {
        User user = App.getInstance().getCurrentUser();

        /* If the user already rated the movie the rating is changed. */
        mapOfRatings.put(user, ratingAdded);

        /* After each change in the mapOfRatings, the rating is recalculated.*/
        double sum = 0;
        for (Map.Entry<User, Integer> entry : mapOfRatings.entrySet()) {
            sum += entry.getValue();
        }
        this.numRatings = this.mapOfRatings.size();
        this.rating = sum / (double) this.numRatings;
    }

}
