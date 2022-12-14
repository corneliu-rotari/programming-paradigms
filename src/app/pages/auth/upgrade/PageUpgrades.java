package app.pages.auth.upgrade;

import app.action.ActionType;
import app.pages.PageType;
import app.pages.auth.PageAuth;

import java.util.HashSet;
import java.util.Set;

public final class PageUpgrades extends PageAuth {
    public PageUpgrades() {
        super(new HashSet<>(Set.of(PageType.UPGRADE, PageType.MOVIES, PageType.LOGOUT, PageType.HOME)),
                new HashSet<>(Set.of(ActionType.BUYTOKEN, ActionType.BUYPREAMIUM)));
    }
}
