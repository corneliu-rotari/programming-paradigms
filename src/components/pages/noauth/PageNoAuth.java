package components.pages.noauth;

import app.features.FeatureFactory;
import components.pages.Page;
import components.pages.PageFactory;

import java.util.HashSet;
import java.util.Set;

/**
 * The page the user sees when he is unauthenticated
 */
public class PageNoAuth extends Page {
    public PageNoAuth() {
        super(new HashSet<>(Set.of(PageFactory.PageType.NOAUTH, PageFactory.PageType.LOGIN,
                                    PageFactory.PageType.REGISTER)),
                new HashSet<>());
    }

    public PageNoAuth(final Set<PageFactory.PageType> pagesToChange,
                      final Set<FeatureFactory.FeatureType> typeOfActions) {
        super(pagesToChange, typeOfActions);
    }
}
