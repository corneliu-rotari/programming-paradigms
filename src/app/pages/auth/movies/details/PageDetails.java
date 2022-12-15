package app.pages.auth.movies.details;

import app.features.FeatureType;
import app.pages.PageFactory;
import app.pages.auth.movies.PageMovies;

import java.util.HashSet;
import java.util.Set;

public final class PageDetails extends PageMovies {
    public PageDetails() {
        super(new HashSet<>(Set.of(PageFactory.PageType.DETAILS, PageFactory.PageType.MOVIES,
                                    PageFactory.PageType.UPGRADE, PageFactory.PageType.LOGOUT,
                                    PageFactory.PageType.HOME)),
                new HashSet<>(Set.of(FeatureType.LIKE, FeatureType.WATCH, FeatureType.PURCHASE,
                                    FeatureType.RATE)));
    }

}
