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
    @Getter @Setter private int numLikes;
    @Getter @Setter private Double rating = (double) 0;
    @Getter @Setter private int numRatings;

    @JsonIgnore
    @Getter public static final int price = 2;
}
