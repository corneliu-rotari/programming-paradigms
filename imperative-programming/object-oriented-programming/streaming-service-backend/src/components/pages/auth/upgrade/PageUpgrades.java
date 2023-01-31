package components.pages.auth.upgrade;

import app.features.FeatureFactory;
import components.pages.PageFactory;
import components.pages.auth.PageAuth;

import java.util.HashSet;
import java.util.Set;

public final class PageUpgrades extends PageAuth {
    public PageUpgrades() {
        super(new HashSet<>(Set.of(PageFactory.PageType.UPGRADE, PageFactory.PageType.MOVIES,
                                    PageFactory.PageType.LOGOUT, PageFactory.PageType.HOME)),
                new HashSet<>(Set.of(FeatureFactory.FeatureType.BUYTOKEN,
                                    FeatureFactory.FeatureType.BUYPREAMIUM)));
    }
}
