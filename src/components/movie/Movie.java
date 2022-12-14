package components.movie;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@EqualsAndHashCode
public class Movie {
    @Getter @Setter private String name;
    @Getter @Setter private int year;
    @Getter @Setter private int duration;
    @Getter @Setter private ArrayList<String> genres;
    @Getter @Setter private ArrayList<String> actors;
    @Getter @Setter private ArrayList<String> countriesBanned;
    @Getter @Setter private int numLikes = 0;
    @Getter @Setter private Double rating = (double) 0;
    @Getter @Setter private int numRatings = 0;

    @JsonIgnore
    @Getter public static final int price = 2;
    @JsonIgnore
    @Getter private ArrayList<Integer> listOfRatings = new ArrayList<>();

    public void addRating(int ratingAdded) {
        int sum = ratingAdded;
        for (Integer rate: this.listOfRatings) {
            sum += rate;
        }
        this.numRatings++;
        this.rating = (double) sum / (double) this.numRatings;
    }

}
