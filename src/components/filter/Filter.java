package components.filter;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

public class Filter {
    @Getter @Setter Sort sort;
    @Getter @Setter OptionalFilters contains;
    public class Sort {
        @Getter @Setter private String rating;
        @Getter @Setter private String duration;
    }

    public class OptionalFilters {
        @Getter @Setter private ArrayList<String> actors;
        @Getter @Setter private ArrayList<String> genre;
    }
}
