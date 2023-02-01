package components.pages.auth.movies.details;

import app.features.FeatureFactory;
import components.pages.PageFactory;
import components.pages.auth.movies.PageMovies;

import java.util.HashSet;
import java.util.Set;

public final class PageDetails extends PageMovies {
    public PageDetails() {
        super(new HashSet<>(Set.of(PageFactory.PageType.DETAILS, PageFactory.PageType.MOVIES,
                                    PageFactory.PageType.UPGRADE, PageFactory.PageType.LOGOUT,
                                    PageFactory.PageType.HOME)),

                new HashSet<>(Set.of(FeatureFactory.FeatureType.LIKE,
                                    FeatureFactory.FeatureType.WATCH,
                                    FeatureFactory.FeatureType.PURCHASE,
                                    FeatureFactory.FeatureType.RATE,
                                    FeatureFactory.FeatureType.SUBSCRIBE)));
    }

}
