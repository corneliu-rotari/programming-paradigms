package app.action;

public enum ActionType {
    CHANGEPAGE("change page"),
    ONPAGE("on page"),
    LOGIN("login"),
    REGISTER("register"),
    SEARCH("search"),
    FILTER("filter"),
    PURCHASE("purchase"),
    WATCH("watch"),
    LIKE("like"),
    RATE("rate"),
    BUYPREAMIUM("buy premium account"),
    BUYTOKEN("buy tokens");


    private String type;
    ActionType(String type) {
        this.type = type;
    }

    public static ActionType fromString(String text) {
        for (ActionType actionType : ActionType.values()) {
            if (actionType.type.equalsIgnoreCase(text)) {
                return actionType;
            }
        }
        return null;
    }

}
