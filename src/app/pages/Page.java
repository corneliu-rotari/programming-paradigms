package app.pages;

import app.App;
import app.action.Action;
import app.action.ActionFactory;
import app.action.ActionTacker;
import app.action.ActionType;
import io.output.Output;
import io.output.response.Response;
import lombok.Getter;

import java.util.Set;

public abstract class Page implements ActionTacker {
    @Getter protected final Set<PageType> pagesToChange;
    @Getter protected final Set<ActionType> typeOfActions;

    public Page(Set<PageType> pagesToChange, Set<ActionType> typeOfActions) {
        this.pagesToChange = pagesToChange;
        this.typeOfActions = typeOfActions;
    }

    @Override
    public void takeAction(Action action) {
        if (ActionType.CHANGEPAGE == ActionType.fromString(action.getType())) {
            changePage(action);
        } else {
            onPage(action);
        }
    }

    public void changePage(Action action) {
        App app = App.getInstance();
        if (pagesToChange.contains(PageType.fromString(action.getPage()))) {
            app.setCurrentPage(PageFactory.createPage(action.getPage()));
            return;
        }
        Output.getInstance().addToTree(new Response.Builder().fail().build());
    }

    public void onPage(Action action) {
        if (typeOfActions.contains(ActionType.fromString(action.getFeature()))) {
            ActionTacker actionTacker = ActionFactory.createAction(action.getFeature());
            actionTacker.takeAction(action);
        }
    }


}
