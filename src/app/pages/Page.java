package app.pages;

import app.App;
import app.features.ActionTacker;
import app.features.FeatureFactory;
import app.features.FeatureType;
import io.input.action.Action;
import io.output.Output;
import io.output.response.Response;

import java.util.Set;

/**
 * Every page subclasses from this page
 * Stores information about page allowed actions and moves
 */
public abstract class Page implements ActionTacker {
    protected final Set<PageFactory.PageType> pagesToChange;
    protected final Set<FeatureType> typeOfActions;

    public Page(final Set<PageFactory.PageType> pagesToChange,
                final Set<FeatureType> typeOfActions) {
        this.pagesToChange = pagesToChange;
        this.typeOfActions = typeOfActions;
    }

    /**
     * Checks if the Command to take is on page or to change the page
     * @param action - input for the feature
     */
    @Override
    public void takeAction(final Action action) {
        if (FeatureType.CHANGEPAGE == FeatureType.fromString(action.getType())) {
            changePage(action);
        } else {
            onPage(action);
        }
    }

    /**
     * Goes to the next allowed page or outputs an error
     * @param action
     */
    public void changePage(final Action action) {
        App app = App.getInstance();
        if (pagesToChange.contains(PageFactory.PageType.fromString(action.getPage()))) {
            app.setCurrentPage(PageFactory.createPage(action));
        } else {
            Output.getInstance().addToTree(new Response.Builder().fail().build());
        }
    }

    /**
     * Implements an allowed feature or outputs an error
     * @param action
     */
    public void onPage(final Action action) {
        if (typeOfActions.contains(FeatureType.fromString(action.getFeature()))) {
            ActionTacker actionTacker = FeatureFactory.createAction(action.getFeature());
            actionTacker.takeAction(action);
        } else {
            Output.getInstance().addToTree(new Response.Builder().fail().build());
        }
    }


}
