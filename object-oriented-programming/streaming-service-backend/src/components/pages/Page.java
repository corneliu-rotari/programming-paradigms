package components.pages;

import app.features.FeatureFactory;

import java.util.Set;

/**
 * Every page subclasses from this page
 * Stores information about page allowed actions and moves
 */
public class Page {
    protected final Set<PageFactory.PageType> pagesToChange;
    protected final Set<FeatureFactory.FeatureType> typeOfActions;

    protected Page(final Set<PageFactory.PageType> pagesToChange,
                final Set<FeatureFactory.FeatureType> typeOfActions) {
        this.pagesToChange = pagesToChange;
        this.typeOfActions = typeOfActions;
    }

    /**
     * Checks if the current page can access a specified page.
     * {@link PageFactory}
     * @param nextPage the title of the next page
     * @return if the next page exists.
     */
    public boolean containsNextPage(final String nextPage) {
        return pagesToChange.contains(PageFactory.PageType.fromString(nextPage));
    }

    /**
     * Checks if the current page can access a specified feature.
     * {@link FeatureFactory}
     * @param featureName the title of the feature.
     * @return if the creation of the feature was successful exists.
     */
    public boolean containsFeature(final String featureName) {
        return typeOfActions.contains(FeatureFactory.FeatureType.fromString(featureName));
    }
}
