package app.pages.noauth.login;

import app.action.ActionType;
import app.pages.PageType;
import app.pages.noauth.PageNoAuth;

import java.util.HashSet;
import java.util.Set;

public final class PageLogin extends PageNoAuth {
    public PageLogin() {
        super(new HashSet<>(Set.of(PageType.LOGIN, PageType.REGISTER, PageType.NOAUTH)),
                new HashSet<>(Set.of(ActionType.LOGIN)));
    }
}
