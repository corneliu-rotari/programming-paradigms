package app.pages.auth.movies;

import app.App;
import app.action.Action;
import app.action.ActionType;
import app.pages.PageType;
import app.pages.auth.PageAuth;
import io.output.Output;
import io.output.response.Response;

import java.util.Arrays;
import java.util.HashSet;

public final class PageMovies extends PageAuth {
    public PageMovies() {
        super(new HashSet<>(Arrays.asList(PageType.HOME, PageType.LOGOUT, PageType.DETAILS)),
                new HashSet<>(Arrays.asList(ActionType.FILTER, ActionType.SEARCH)));
        App.getInstance().setCurrentMovieList();
        Output.getInstance().addToTree(new Response.Builder().user().movies().build());
    }


}
