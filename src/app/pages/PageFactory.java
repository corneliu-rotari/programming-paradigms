package app.pages;

import app.App;
import app.action.Action;
import app.pages.auth.PageAuth;
import app.pages.auth.movies.details.PageDetails;
import app.pages.auth.movies.PageMovies;
import app.pages.auth.upgrade.PageUpgrades;
import app.pages.noauth.login.PageLogin;
import app.pages.noauth.PageNoAuth;
import app.pages.noauth.regitser.PageRegister;

public final class PageFactory {
    private static Action action;
    public static Page createPage(Action actionToTake) {
        action = actionToTake;
        return createPage(PageType.fromString(action.getPage()));
    }
    public static Page createPage() {
        return createPage(PageType.fromString(""));
    }

    public static Page createPage(PageType type) {
        switch (type) {
            case LOGIN:
                return new PageLogin();
            case REGISTER:
                return new PageRegister();
            case HOME:
                return new PageAuth();
            case MOVIES:
                return new PageMovies();
            case DETAILS: {
                if (App.getInstance().setChosenMovie(action.getMovie())) {
                    return new PageDetails();
                } else return App.getInstance().getCurrentPage();
            }
            case UPGRADE:
                return new PageUpgrades();
            default:
                return new PageNoAuth();
        }
    }
}
