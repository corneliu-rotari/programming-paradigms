package app.pages.auth.movies.details;

import app.action.ActionType;
import app.pages.PageType;
import app.pages.auth.movies.PageMovies;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

public final class PageDetails extends PageMovies {
    @Setter @Getter private String movie;
    public PageDetails() {
        super(new HashSet<>(Set.of(PageType.DETAILS, PageType.MOVIES, PageType.UPGRADE, PageType.LOGOUT, PageType.HOME)),
                new HashSet<>(Set.of(ActionType.LIKE, ActionType.WATCH, ActionType.PURCHASE, ActionType.RATE)));
    }

}
