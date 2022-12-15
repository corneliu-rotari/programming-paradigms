package app.pages.auth;

import app.features.FeatureFactory;
import app.pages.Page;
import app.pages.PageFactory;

import java.util.HashSet;
import java.util.Set;

public class PageAuth extends Page {
    public PageAuth() {
        super(new HashSet<>(Set.of(PageFactory.PageType.HOME, PageFactory.PageType.MOVIES,
                                    PageFactory.PageType.UPGRADE, PageFactory.PageType.LOGOUT)),
                new HashSet<>());
    }

    public PageAuth(final Set<PageFactory.PageType> pagesToChange,
                    final Set<FeatureFactory.FeatureType> typeOfActions) {
        super(pagesToChange, typeOfActions);
    }
}
