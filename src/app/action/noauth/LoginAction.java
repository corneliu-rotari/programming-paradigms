package app.action.noauth;

import app.App;
import app.action.Action;
import app.action.ActionTacker;
import app.pages.PageFactory;
import app.pages.PageType;
import components.user.User;
import io.output.Output;
import io.output.response.Response;

public class LoginAction implements ActionTacker {
    @Override
    public void takeAction(Action action) {
        App app = App.getInstance();
        User user = app.getDatabase().authUser(action.getCredentials());
        if (user != null) {
            app.setCurrentPage(PageFactory.createPage(PageType.HOME));
            app.setCurrentUser(user);
            Output.getInstance().addToTree(new Response.Builder().user().build());
            return;
        }
        Output.getInstance().addToTree(new Response.Builder().fail().build());
    }
}
