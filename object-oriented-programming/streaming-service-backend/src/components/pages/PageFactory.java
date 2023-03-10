package components.pages;

import app.App;
import components.pages.auth.PageAuth;
import components.pages.auth.movies.PageMovies;
import components.pages.auth.movies.details.PageDetails;
import components.pages.auth.upgrade.PageUpgrades;
import components.pages.noauth.PageNoAuth;
import components.pages.noauth.login.PageLogin;
import components.pages.noauth.regitser.PageRegister;
import io.input.request.Request;
import io.output.Output;
import io.output.response.Response;

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
                app.initHistory();
                return new PageAuth();
            }
            case MOVIES -> {
                app.addToHistory(request);
                App.getInstance().setCurrentMovieList();
                Output.getInstance().addToTree(new Response.Builder().user().movies().build());
                return new PageMovies();
            }
            case DETAILS -> {
                if (app.setChosenMovie(request.getMovie())) {
                    app.addToHistory(request);
                    return new PageDetails();
                } else {
                    return App.getInstance().getCurrentPage();
                }
            }
            case UPGRADE -> {
                app.addToHistory(request);
                return new PageUpgrades();
            }
            default -> {
                if (app != null) {
                    app.setCurrentUser(null);
                    app.setHistory(null);
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

        @Override
        public String toString() {
            return type;
        }
    }
}
