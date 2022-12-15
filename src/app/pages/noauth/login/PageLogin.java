package app.pages.noauth.login;

import app.features.FeatureType;
import app.pages.PageFactory;
import app.pages.noauth.PageNoAuth;

import java.util.HashSet;
import java.util.Set;

public final class PageLogin extends PageNoAuth {
    public PageLogin() {
        super(new HashSet<>(Set.of(PageFactory.PageType.LOGIN, PageFactory.PageType.REGISTER,
                                    PageFactory.PageType.NOAUTH)),
                new HashSet<>(Set.of(FeatureType.LOGIN)));
    }
}
