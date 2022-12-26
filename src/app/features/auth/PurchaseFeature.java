package app.features.auth;

import app.App;
import app.features.FeatureFactory;
import io.input.action.Request;
import app.features.FeatureCommand;
import components.movie.Movie;
import components.user.User;
import components.user.account.Premium;
import io.output.Output;
import io.output.response.Response;

public final class PurchaseFeature implements FeatureCommand {

    /**
     * Purchases a movie (if the user has active free movies, they are used first)
     * @param request - input for the feature
     */
    @Override
    public void doCommand(final Request request) {
        User user = App.getInstance().getCurrentUser();
        Movie movie = App.getInstance().getChosenMovie();
        int nrFreeMovies = user.getNumFreePremiumMovies();

//        if (user.getPurchasedMovies().stream().filter(movie1 -> movie1.getName().equals(movie.getName())).toList().size() != 0) {
//            System.out.println("Tired to purchase again");
//            Output.getInstance().addToTree(new Response.Builder().fail().build());
//            return;
//        }
        if (user.getPurchasedMovies().contains(movie)) {
            Output.getInstance().addToTree(new Response.Builder().fail().build());
            return;
        }

        if (nrFreeMovies > 0 && user.getCredentials().getAccountType().equals(Premium.TYPE)) {
            user.setNumFreePremiumMovies(nrFreeMovies - 1);
        } else if (user.getTokensCount() >= Movie.PRICE) {
            user.setTokensCount(user.getTokensCount() - Movie.PRICE);
        } else {
            Output.getInstance().addToTree(new Response.Builder().fail().build());
            return;
        }
        user.addMovieByFeature(movie, FeatureFactory.FeatureType.PURCHASE);
        Output.getInstance().addToTree(new Response.Builder().user().movies().build());
    }
}
