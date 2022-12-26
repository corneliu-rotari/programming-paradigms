package app.strategies.strategy;

import app.App;
import app.pages.PageFactory;
import app.strategies.Strategy;
import components.genre.GenreManager;
import io.input.action.Request;
import io.output.Output;
import io.output.response.Response;

import java.util.HashMap;

public class SubscribeStrategy extends Strategy {
    public SubscribeStrategy(Request request) {
        super(request);
    }

    @Override
    public void execute() {
        if (request == null) {
            return;
        }

        App app = App.getInstance();

        if (app.getHistory().size() != 0) {
            Request currentPage = app.getHistory().peek();
            if (!PageFactory.PageType.DETAILS.equals(PageFactory.PageType.fromString(currentPage.getPage()))) {
                Output.getInstance().addToTree(new Response.Builder().fail().build());
                return;
            }
        } else {
            Output.getInstance().addToTree(new Response.Builder().fail().build());
            return;
        }


        HashMap<String, GenreManager> database = app.getDatabase().getSubscribedGenres();
        GenreManager genreManager;

        if (database.containsKey(request.getSubscribedGenre())) {
            genreManager = database.get(request.getSubscribedGenre());
            if (!genreManager.containsSubscriber(app.getCurrentUser())) {
                genreManager.subscribe(app.getCurrentUser());
            } else {
                Output.getInstance().addToTree(new Response.Builder().fail().build());
            }
        } else {
            genreManager = new GenreManager();
            genreManager.subscribe(app.getCurrentUser());
            database.put(request.getSubscribedGenre(), genreManager);
        }
    }
}
