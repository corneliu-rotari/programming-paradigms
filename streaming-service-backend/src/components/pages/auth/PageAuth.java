package components.pages.auth;

import app.features.FeatureFactory;
import components.pages.Page;
import components.pages.PageFactory;

import java.util.HashSet;
import java.util.Set;

public class PageAuth extends Page {
    public PageAuth() {
        super(new HashSet<>(Set.of(PageFactory.PageType.HOME, PageFactory.PageType.MOVIES,
                                    PageFactory.PageType.UPGRADE, PageFactory.PageType.LOGOUT)),
                new HashSet<>());
    }

    protected PageAuth(final Set<PageFactory.PageType> pagesToChange,
                    final Set<FeatureFactory.FeatureType> typeOfActions) {
        super(pagesToChange, typeOfActions);
    }
}
