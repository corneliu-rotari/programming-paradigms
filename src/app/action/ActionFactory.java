package app.action;

import app.action.auth.*;
import app.action.noauth.LoginAction;
import app.action.noauth.RegisterAction;

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
                return new PurchaseAction();
            case WATCH:
                return new WatchAction();
            case LIKE:
                return new LikeAction();
            case RATE:
                return new RateAction();
            case BUYPREAMIUM:
                return new BuyPremiumAction();
            case BUYTOKEN:
                return new BuyTokenAction();
            default:
                return new DefaultAction();
        }
    }
}
