package app.pages;

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
    PageType(String type) {
        this.type = type;
    }

    public static PageType fromString(String text) {
        for (PageType pageType : PageType.values()) {
            if (pageType.type.equalsIgnoreCase(text)) {
                return pageType;
            }
        }
        return PageType.NOAUTH;
    }
}
