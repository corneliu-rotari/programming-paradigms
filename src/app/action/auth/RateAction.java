package app.action.auth;

import app.App;
import app.action.Action;
import app.action.ActionTacker;
import components.movie.Movie;
import components.user.User;
import io.output.Output;
import io.output.response.Response;

public class RateAction implements ActionTacker {
    @Override
    public void takeAction(Action action) {
        User user = App.getInstance().getCurrentUser();
        Movie active = App.getInstance().getChosenMovie();
        if (user.getWatchedMovies().contains(active) && action.getRate() <= 5 && action.getRate() >= 0) {
            user.addRated(active);
            active.addRating(action.getRate());
            Output.getInstance().addToTree(new Response.Builder().user().movies().build());
        } else {
            Output.getInstance().addToTree(new Response.Builder().fail().build());
        }
    }
}
