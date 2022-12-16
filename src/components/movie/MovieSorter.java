package components.movie;

import components.filter.Filter;

import java.util.Comparator;

/**
 * Sorter for a Movie list based on a filter
 */
public class MovieSorter implements Comparator<Movie> {
    private final Filter.Sort sort;

    public MovieSorter(final Filter.Sort sortBy) {
        this.sort = sortBy;
    }

    /**
     * Compares to movies based on input criteria.
     * @param movie1 the first object to be compared.
     * @param movie2 the second object to be compared.
     * @return 1 if movie1 should be before movie2
     *         -1 if movie2 should be before movie1
     *         0 if they are equal
     */
    @Override
    public int compare(final Movie movie1, final Movie movie2) {
        if (sort == null) {
            return 0;
        }

        if (sort.getDuration() == null || movie1.getDuration() == movie2.getDuration()) {
            return ratingCompare(movie1.getRating(), movie2.getRating());
        } else {
            return durationCompare(movie1.getDuration(), movie2.getDuration());
        }
    }

    private int ratingCompare(final Double rating1, final  Double rating2) {
        if (sort.getRating() == null) {
            return 0;
        }
        if (sort.getRating().equals("decreasing")) {
            return Double.compare(rating2, rating1);
        } else {
            return Double.compare(rating1, rating2);
        }
    }

    private int durationCompare(final int duration1, final int duration2) {
        if (sort.getDuration().equals("decreasing")) {
            return Integer.compare(duration2, duration1);
        } else {
            return Integer.compare(duration1, duration2);
        }
    }
}
