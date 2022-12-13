package app.pages.noauth.login;

import app.action.ActionType;
import app.pages.PageType;
import app.pages.noauth.PageNoAuth;

import java.util.Arrays;
import java.util.HashSet;

public final class PageLogin extends PageNoAuth {
    public PageLogin() {
        super(new HashSet<>(Arrays.asList(PageType.REGISTER, PageType.NOAUTH)),
                new HashSet<>(Arrays.asList(ActionType.LOGIN)));
    }
}
