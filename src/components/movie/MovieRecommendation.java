package components.movie;

import app.App;
import lombok.Getter;

import java.util.*;

public class MovieRecommendation {
    @Getter private String movieName = "No recommendation";
    public MovieRecommendation(Set<Movie> likedMovies) {
        ArrayList<Movie> moviesInDb = new ArrayList<>(App.getInstance().getAllowedMovies());
        if (likedMovies.size() == 0 || moviesInDb.size() == 0) {
            return;
        }
        moviesInDb.sort((o1,o2) -> Integer.compare(o2.getNumLikes(), o1.getNumLikes()));

        HashMap<String, Integer> topGenres = new HashMap<>();
        likedMovies.forEach(movie -> movie.getGenres().forEach(genre -> {
            if (topGenres.containsKey(genre)) {
                topGenres.put(genre, topGenres.get(genre) + 1);
            } else {
                topGenres.put(genre, 1);
            }
        }));
        int maxNrOfLikes = Collections.max(topGenres.values());
        List<String> genresFound = new ArrayList<>(topGenres.entrySet().stream()
                .filter(entry -> entry.getValue() == maxNrOfLikes)
                .map(Map.Entry::getKey).toList());

        Collections.sort(genresFound);


        Set<Movie> watchedMovies = App.getInstance().getCurrentUser().getWatchedMovies();

        for (String genre : genresFound) {
            for (Movie movie : moviesInDb) {
                if (movie.getGenres().contains(genre) && !watchedMovies.contains(movie)) {
                    movieName = movie.getName();
                    return;
                }
            }
        }
    }
}
