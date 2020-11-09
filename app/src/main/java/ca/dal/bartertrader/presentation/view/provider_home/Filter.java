package ca.dal.bartertrader.presentation.view.provider_home;

public enum Filter {
    ELECTRONIC("Electronics"),
    KITCHEN("Kitchen"),
    CLOTHING("Clothing"),
    VEHICLE("Vehicle"),
    FURNITURE("Furniture"),
    TOYS_GAMES("Toys & Games"),
    ART("Art"),
    VIDEO_GAMES("Video Games");

    private String displayName;

    Filter(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static String[] getFilterStrings() {
        Filter[] filters = Filter.class.getEnumConstants();
        String [] filterStrings = new String[filters.length];

        for(int i = 0; i < filters.length; i++) {
            filterStrings[i] = filters[i].getDisplayName();
        }
        return filterStrings;
    }
}
