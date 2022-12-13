package app.action.noauth;

import app.action.ActionType;
import app.pages.PageType;
import app.pages.noauth.PageNoAuth;

import java.util.Arrays;
import java.util.HashSet;

public final class PageRegister extends PageNoAuth {
    public PageRegister() {
        super(new HashSet<>(Arrays.asList(PageType.LOGIN, PageType.NOAUTH)),
                new HashSet<>(Arrays.asList(ActionType.REGISTER)));
    }
}
