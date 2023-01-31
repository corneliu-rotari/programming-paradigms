package components.pages.noauth.login;

import app.features.FeatureFactory;
import components.pages.PageFactory;
import components.pages.noauth.PageNoAuth;

import java.util.HashSet;
import java.util.Set;

public final class PageLogin extends PageNoAuth {
    public PageLogin() {
        super(new HashSet<>(Set.of(PageFactory.PageType.LOGIN, PageFactory.PageType.REGISTER,
                                    PageFactory.PageType.NOAUTH)),
                new HashSet<>(Set.of(FeatureFactory.FeatureType.LOGIN)));
    }
}
