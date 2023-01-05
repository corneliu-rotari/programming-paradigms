package components.pages.auth.movies;

import app.features.FeatureFactory;
import components.pages.PageFactory;
import components.pages.auth.PageAuth;

import java.util.HashSet;
import java.util.Set;

/**
 * Shows the specific movies for him
 */
public class PageMovies extends PageAuth {
    public PageMovies() {
        super(new HashSet<>(Set.of(PageFactory.PageType.MOVIES, PageFactory.PageType.HOME,
                                    PageFactory.PageType.LOGOUT, PageFactory.PageType.DETAILS,
                                    PageFactory.PageType.UPGRADE)),
                new HashSet<>(Set.of(FeatureFactory.FeatureType.FILTER,
                                    FeatureFactory.FeatureType.SEARCH)));
    }

    protected PageMovies(final Set<PageFactory.PageType> pagesToChange,
                         final Set<FeatureFactory.FeatureType> typeOfActions) {
        super(pagesToChange, typeOfActions);
    }
}
