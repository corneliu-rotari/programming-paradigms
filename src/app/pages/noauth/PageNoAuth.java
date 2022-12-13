package app.pages.noauth;

import app.action.ActionType;
import app.pages.Page;
import app.pages.PageType;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


public class PageNoAuth extends Page {
    public PageNoAuth() {
        super(new HashSet<>(Arrays.asList(PageType.LOGIN, PageType.REGISTER)),
                new HashSet<>(Arrays.asList(ActionType.CHANGEPAGE)));
    }

    public PageNoAuth(Set<PageType> pagesToChange, Set<ActionType> typeOfActions) {
        super(pagesToChange, typeOfActions);
    }
}
