package app.features.noauth;

import app.App;
import io.input.action.Action;
import app.features.ActionTacker;
import app.pages.PageFactory;
import components.user.User;
import io.output.Output;
import io.output.response.Response;

public final class LoginFeature implements ActionTacker {
    /**
     * Checks if the user exist in the database.
     * Authenticates the user if he has valid credentials
     * @param action - input for the feature
     */
    @Override
    public void takeAction(final Action action) {
        App app = App.getInstance();
        User user = app.getDatabase().authUser(action.getCredentials());
        if (user != null) {
            app.setCurrentPage(PageFactory.createPage(PageFactory.PageType.HOME));
            app.setCurrentUser(user);
            Output.getInstance().addToTree(new Response.Builder().user().build());
            return;
        }
        Output.getInstance().addToTree(new Response.Builder().fail().build());
    }
}
