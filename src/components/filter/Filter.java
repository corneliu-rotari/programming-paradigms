package components.filter;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
@Getter @Setter
public final class Filter {
    private Sort sort;
    private OptionalFilters contains;

    @Getter @Setter
    public static final class Sort {
        private String rating;
        private String duration;
    }

    @Getter @Setter
    public static final class OptionalFilters {
        private ArrayList<String> actors;
        private ArrayList<String> genre;
    }
}
