package app.action.auth;

import app.App;
import app.action.Action;
import app.action.ActionTacker;
import components.movie.Movie;
import components.user.User;
import io.output.Output;
import io.output.response.Response;

public class PurchaseAction implements ActionTacker {

    @Override
    public void takeAction(Action action) {
        User user = App.getInstance().getCurrentUser();
        Movie movie = App.getInstance().getChosenMovie();

        int nrFreeMovies = user.getNumFreePremiumMovies();
        if (nrFreeMovies > 0) {
            user.setNumFreePremiumMovies(nrFreeMovies - 1);
        } else {
            user.setTokensCount(user.getTokensCount() - Movie.price);
        }
        user.addPurchased(movie);
        Output.getInstance().addToTree(new Response.Builder().user().movies().build());
    }
}
