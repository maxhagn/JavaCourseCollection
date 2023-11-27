package at.ac.tuwien.qs.movierental;

import java.util.Arrays;

public enum Genre {

    NO_GENRE("Kein Genre", 1f),
    NORMAL("Normal", 1f),
    CHILDREN("Kinder", 0.75f),
    CLASSIC("Klassiker", 0.9f),
    HORROR("Horror", 1.1f),
    SCI_FI("SciFi", 1.15f),
    FANTASY("Fantasy", 1.25f);

    private final float priceFactor;
    private final String viewName;

    Genre(String viewName, float priceFactor) {
        this.viewName = viewName;
        this.priceFactor = priceFactor;
    }

    public float getPriceFactor() {
        return priceFactor;
    }

    public String getViewName() {
        return viewName;
    }

    public static String[] getViewNames() {
        return Arrays.stream(Genre.values()).map(Genre::toString).toArray(String[]::new);
    }

    public static Genre fromString(String viewName) {
        for (Genre genre : Genre.values()) {
            if (genre.getViewName().equals(viewName)) {
                return genre;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return viewName;
    }
}
