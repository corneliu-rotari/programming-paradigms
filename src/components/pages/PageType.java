package components.pages;

public enum PageType {
    LOGIN("login"),
    REGISTER("register");

    private String type;
    PageType(String type) {
        this.type = type;
    }

    public static PageType fromString(String text) {
        for (PageType pageType : PageType.values()) {
            if (pageType.type.equalsIgnoreCase(text)) {
                return pageType;
            }
        }
        return null;
    }
}
