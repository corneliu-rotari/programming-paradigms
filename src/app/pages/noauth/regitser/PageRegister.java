package app.pages.noauth.regitser;

import app.action.ActionType;
import app.pages.PageType;
import app.pages.noauth.PageNoAuth;

import java.util.HashSet;
import java.util.Set;

public final class PageRegister extends PageNoAuth {
    public PageRegister() {
        super(new HashSet<>(Set.of(PageType.REGISTER, PageType.NOAUTH)),
                new HashSet<>(Set.of(ActionType.REGISTER)));
    }
}
