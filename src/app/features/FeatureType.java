package app.features;

/**
 * Type of Feature that can happen in the application
 */
public enum FeatureType {
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


    private final String type;

    FeatureType(final String type) {
        this.type = type;
    }

    /**
     * Converts a string to a Feature type
     * @param text - string to convert
     * @return Type of Feature / null
     */
    public static FeatureType fromString(final String text) {
        for (FeatureType actionType : FeatureType.values()) {
            if (actionType.type.equalsIgnoreCase(text)) {
                return actionType;
            }
        }
        return null;
    }

}
