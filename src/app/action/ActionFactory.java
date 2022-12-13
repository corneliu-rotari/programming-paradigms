package app.action;

import app.action.auth.FilterAction;
import app.action.auth.SearchAction;
import app.action.noauth.LoginAction;
import app.pages.noauth.regitser.RegisterAction;

public class ActionFactory {
    public static ActionTacker createAction(String type) {
        switch (ActionType.fromString(type)) {
            case LOGIN:
                return new LoginAction();
            case REGISTER:
                return new RegisterAction();
            case SEARCH:
                return new SearchAction();
            case FILTER:
                return new FilterAction();
            case PURCHASE:
//                return new PageDetails();
            case WATCH:
//                return new PageUpgrades();
            case LIKE:
//                return new PageUpgrades();
            case RATE:
//                return new PageUpgrades();
            case BUYPREAMIUM:
//                return new PageUpgrades();
            case BUYTOKEN:
//                return new PageUpgrades();
            default:
                return new DefaultAction();
        }
    }
}
