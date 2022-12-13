package app.pages.auth;

import app.action.ActionType;
import app.pages.Page;
import app.pages.PageType;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class PageAuth extends Page {
    public PageAuth() {
        super(new HashSet<>(Arrays.asList(PageType.MOVIES, PageType.UPGRADE, PageType.LOGOUT)),
                new HashSet<>());
    }

    public PageAuth(Set<PageType> pagesToChange, Set<ActionType> typeOfActions) {
        super(pagesToChange, typeOfActions);
    }
}
