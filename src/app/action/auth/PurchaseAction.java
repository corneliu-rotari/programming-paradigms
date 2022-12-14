package app.action.auth;

import app.App;
import app.action.Action;
import app.action.ActionTacker;
import app.pages.auth.movies.details.PageDetails;
import components.movie.Movie;
import components.user.User;

import java.util.List;

public class PurchaseAction implements ActionTacker {

    @Override
    public void takeAction(Action action) {
        User user = App.getInstance().getCurrentUser();
        List<Movie> movieList = App.getInstance().getCurrentMovieList();
        PageDetails page = (PageDetails) App.getInstance().getCurrentPage();
        String movieName = page.getMovie();

        for (Movie movie: movieList) {
            if (action.getMovie().equals("")) {
                System.out.println("In purchase action movie name is null");
                break;
            }
            if (movie.getName().equals(movieName)) {
                int nrFreeMovies = user.getNumFreePremiumMovies();
                if (nrFreeMovies > 0) {
                    user.setNumFreePremiumMovies(nrFreeMovies - 1);
                } else {
                    user.setTokensCount(user.getTokensCount() - Movie.price);
                }
                user.addPurchase(movie);
                break;
            }
        }
    }
}
