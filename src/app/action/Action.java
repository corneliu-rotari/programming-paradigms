package app.action;

import components.filter.Filter;
import components.user.Credentials;
import lombok.Getter;
import lombok.Setter;

public class Action {
    @Getter @Setter private String type;
    @Getter @Setter private String page;
    @Getter @Setter private String movie;
    @Getter @Setter private String feature;
    @Getter @Setter private Credentials credentials;
    @Getter @Setter private String startsWith;
    @Getter @Setter private Filter filters;
    @Getter @Setter private int count;
    @Getter @Setter private int rate;
}
