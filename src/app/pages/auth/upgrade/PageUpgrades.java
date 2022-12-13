package app.pages.auth.upgrade;

import app.action.ActionType;
import app.pages.PageType;
import app.pages.auth.PageAuth;

import java.util.Arrays;
import java.util.HashSet;

public final class PageUpgrades extends PageAuth {
    public PageUpgrades() {
        super(new HashSet<>(Arrays.asList(PageType.MOVIES, PageType.LOGOUT, PageType.HOME)),
                new HashSet<>(Arrays.asList(ActionType.BUYTOKEN, ActionType.BUYPREAMIUM)));
    }
}
