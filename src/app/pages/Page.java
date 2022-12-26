package app.pages;

import app.features.FeatureFactory;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

/**
 * Every page subclasses from this page
 * Stores information about page allowed actions and moves
 */
public abstract class Page {
    protected final Set<PageFactory.PageType> pagesToChange;
    protected final Set<FeatureFactory.FeatureType> typeOfActions;
    @Setter @Getter protected PageFactory.PageType pageTitle = null;

    public Page(final Set<PageFactory.PageType> pagesToChange,
                final Set<FeatureFactory.FeatureType> typeOfActions) {
        this.pagesToChange = pagesToChange;
        this.typeOfActions = typeOfActions;
    }


    public boolean containsNextPage(final String nextPage) {
        return pagesToChange.contains(PageFactory.PageType.fromString(nextPage));
    }

    public boolean containsFeature(final String featureName) {
        return typeOfActions.contains(FeatureFactory.FeatureType.fromString(featureName));
    }

}
