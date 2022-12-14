package app.action.auth;

import app.App;
import app.action.Action;
import app.action.ActionTacker;
import components.movie.Movie;
import components.user.User;
import io.output.Output;
import io.output.response.Response;

import java.util.List;

public class WatchAction implements ActionTacker {
    @Override
    public void takeAction(Action action) {
        User user = App.getInstance().getCurrentUser();
        Movie active = App.getInstance().getChosenMovie();
        if (user.getPurchasedMovies().contains(active)) {
            user.addWatched(active);
            Output.getInstance().addToTree(new Response.Builder().user().movies().build());
        } else {
            Output.getInstance().addToTree(new Response.Builder().fail().build());
        }
    }
}
