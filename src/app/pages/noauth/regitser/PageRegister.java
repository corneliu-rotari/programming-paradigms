package app.pages.noauth.regitser;

import app.features.FeatureFactory;
import app.pages.PageFactory;
import app.pages.noauth.PageNoAuth;

import java.util.HashSet;
import java.util.Set;

public final class PageRegister extends PageNoAuth {
    public PageRegister() {
        super(new HashSet<>(Set.of(PageFactory.PageType.REGISTER, PageFactory.PageType.NOAUTH)),
                new HashSet<>(Set.of(FeatureFactory.FeatureType.REGISTER)));
    }
}
