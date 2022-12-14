package app.pages.auth.movies;

import app.App;
import app.action.ActionType;
import app.pages.PageType;
import app.pages.auth.PageAuth;
import io.output.Output;
import io.output.response.Response;

import java.util.HashSet;
import java.util.Set;

public class PageMovies extends PageAuth {
    public PageMovies() {
        super(new HashSet<>(Set.of(PageType.MOVIES, PageType.HOME, PageType.LOGOUT, PageType.DETAILS)),
                new HashSet<>(Set.of(ActionType.FILTER, ActionType.SEARCH)));
        App.getInstance().setCurrentMovieList();
        Output.getInstance().addToTree(new Response.Builder().user().movies().build());
    }

    public PageMovies(Set<PageType> pagesToChange, Set<ActionType> typeOfActions) {
        super(pagesToChange, typeOfActions);
    }
}
