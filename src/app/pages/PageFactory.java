package app.pages;

import app.App;
import io.input.action.Request;
import app.pages.auth.PageAuth;
import app.pages.auth.movies.details.PageDetails;
import app.pages.auth.movies.PageMovies;
import app.pages.auth.upgrade.PageUpgrades;
import app.pages.noauth.login.PageLogin;
import app.pages.noauth.PageNoAuth;
import app.pages.noauth.regitser.PageRegister;

import java.util.LinkedList;

/**
 * Creates a specific Page based on the Page type
 */
public final class PageFactory {
    private static Request request;

    /**
     * Creates a page based on input
     * @param requestToTake - request information
     * @return a child object of the Page class
     */
    public static Page createPage(final Request requestToTake) {
        request = requestToTake;
        return createPage(PageType.fromString(request.getPage()));
    }

    /**
     * Creates a default page
     * @return - PageNoAuth
     */
    public static Page createPage() {
        return new PageNoAuth();
    }


    /**
     * Creates a page Based on a page type
     * @param type - Page type enum
     * @return new Page object
     */
    public static Page createPage(final PageType type) {
        App app = App.getInstance();
        switch (type) {
            case LOGIN -> {
                return new PageLogin();
            }
            case REGISTER -> {
                return new PageRegister();
            }
            case HOME -> {
                return new PageAuth();
            }
            case MOVIES -> {
                return new PageMovies();
            }
            case DETAILS -> {
                if (App.getInstance().setChosenMovie(request.getMovie())) {
                    return new PageDetails();
                } else {
                    app.getHistory().pop();
                    return App.getInstance().getCurrentPage();
                }
            }
            case UPGRADE -> {
                return new PageUpgrades();
            }
            default -> {
                if (App.getInstance() != null) {
                    App.getInstance().setCurrentUser(null);
                    App.getInstance().setHistory(new LinkedList<>());
                }
                return new PageNoAuth();
            }
        }
    }
    private PageFactory() {
    }

    public enum PageType {
        NOAUTH("noAuth"),
        LOGIN("login"),
        REGISTER("register"),
        HOME("home"),
        MOVIES("movies"),
        DETAILS("see details"),
        UPGRADE("upgrades"),
        LOGOUT("logout");

        private final String type;
        PageType(final String type) {
            this.type = type;
        }

        /**
         * Maps a string to a specific Page type.
         *
         * @param text the name of the Page type
         * @return the Page type found, else if the string page does not exist NoAuth is returned.
         */
        public static PageType fromString(final String text) {
            for (PageType pageType : PageType.values()) {
                if (pageType.type.equalsIgnoreCase(text)) {
                    return pageType;
                }
            }
            return PageType.NOAUTH;
        }
    }
}
