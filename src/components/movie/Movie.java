package components.movie;

import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;
public class Movie {
    @Getter @Setter private String name;
    @Getter @Setter private int year;
    @Getter @Setter private int duration;
    @Getter @Setter private ArrayList<String> genres;
    @Getter @Setter private ArrayList<String> actors;
    @Getter @Setter private ArrayList<String> countriesBanned;
    @Getter @Setter private int numLikes;
    @Getter @Setter private Double rating;
    @Getter @Setter private int numRatings;
}
