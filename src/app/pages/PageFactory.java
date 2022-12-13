package app.pages;

import app.pages.auth.PageAuth;
import app.pages.auth.details.PageDetails;
import app.pages.auth.movies.PageMovies;
import app.pages.auth.upgrade.PageUpgrades;
import app.pages.noauth.login.PageLogin;
import app.pages.noauth.PageNoAuth;
import app.action.noauth.PageRegister;

public final class PageFactory {
    public static Page createPage(String type) {
        return createPage(PageType.fromString(type));
    }
    public static Page createPage() {
        return createPage("");
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
            case DETAILS:
                return new PageDetails();
            case UPGRADE:
                return new PageUpgrades();
            default:
                return new PageNoAuth();
        }
    }
}
