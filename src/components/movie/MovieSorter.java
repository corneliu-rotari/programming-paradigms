package components.movie;

import components.filter.Filter;

import java.util.Comparator;

public class MovieSorter implements Comparator<Movie> {
    private final Filter filter;

    public MovieSorter(Filter filter) {
        this.filter = filter;
    }

    @Override
    public int compare(Movie movie1, Movie movie2) {
        if (filter.getSort() == null) {
            return 0;
        }

        if (filter.getSort().getDuration() == null || movie1.getDuration() == movie2.getDuration()) {
            return ratingCompare(movie1.getRating(), movie2.getRating());
        }

        if (filter.getSort().getDuration().equals("decreasing")) {
            return Integer.compare(movie2.getDuration(), movie1.getDuration());
        } else {
            return Integer.compare(movie1.getDuration(), movie2.getDuration());
        }
    }

    private int ratingCompare(Double rating1, Double rating2) {
        if (filter.getSort().getDuration() == null) {
            return 0;
        }
        if (filter.getSort().getDuration().equals("decreasing")) {
            return Double.compare(rating2, rating1);
        } else {
            return Double.compare(rating1, rating2);
        }
    }
}
