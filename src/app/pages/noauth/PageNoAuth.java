package app.pages.noauth;

import app.features.FeatureType;
import app.pages.Page;
import app.pages.PageFactory;

import java.util.HashSet;
import java.util.Set;

/**
 * The page the user sees when he is unauthenticated
 */
public class PageNoAuth extends Page {
    public PageNoAuth() {
        super(new HashSet<>(Set.of(PageFactory.PageType.NOAUTH, PageFactory.PageType.LOGIN,
                                    PageFactory.PageType.REGISTER)),
                new HashSet<>(Set.of(FeatureType.CHANGEPAGE)));
    }

    public PageNoAuth(final Set<PageFactory.PageType> pagesToChange,
                      final Set<FeatureType> typeOfActions) {
        super(pagesToChange, typeOfActions);
    }
}
