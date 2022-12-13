package components.pages;

import components.pages.types.PageLogin;
import components.pages.types.PageRegister;

public final class PageFactory {
    public static Page createPage(String type) {
        switch (PageType.fromString(type)) {
            case LOGIN :
                return new PageLogin();
            case REGISTER :
                return new PageRegister();
            default:
                throw new IllegalStateException("Unexpected value: " + PageType.fromString(type));
        }

    }
}
