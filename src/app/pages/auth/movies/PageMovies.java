package app.pages.auth.movies;

import app.App;
import app.features.FeatureType;
import app.pages.PageFactory;
import app.pages.auth.PageAuth;
import io.output.Output;
import io.output.response.Response;

import java.util.HashSet;
import java.util.Set;

public class PageMovies extends PageAuth {
    public PageMovies() {
        super(new HashSet<>(Set.of(PageFactory.PageType.MOVIES, PageFactory.PageType.HOME,
                                    PageFactory.PageType.LOGOUT, PageFactory.PageType.DETAILS)),
                new HashSet<>(Set.of(FeatureType.FILTER, FeatureType.SEARCH)));
        App.getInstance().setCurrentMovieList();
        Output.getInstance().addToTree(new Response.Builder().user().movies().build());
    }

    public PageMovies(final Set<PageFactory.PageType> pagesToChange,
                      final Set<FeatureType> typeOfActions) {
        super(pagesToChange, typeOfActions);
    }
}
