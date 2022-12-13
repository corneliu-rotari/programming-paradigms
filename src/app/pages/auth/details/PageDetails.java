package app.pages.auth.details;

import app.action.ActionType;
import app.pages.PageType;
import app.pages.auth.PageAuth;

import java.util.Arrays;
import java.util.HashSet;

public final class PageDetails extends PageAuth {
    public PageDetails() {
        super(new HashSet<>(Arrays.asList(PageType.MOVIES, PageType.UPGRADE, PageType.LOGOUT, PageType.HOME)),
                new HashSet<>(Arrays.asList(ActionType.LIKE, ActionType.WATCH, ActionType.PURCHASE, ActionType.RATE)));
    }
}
