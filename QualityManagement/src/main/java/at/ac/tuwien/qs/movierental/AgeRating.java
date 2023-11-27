package at.ac.tuwien.qs.movierental;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum AgeRating {

    NOT_RATED("Nicht Bewertet", 21),
    FSK_0("FSK 0", 0),
    FSK_6("FSK 6", 6),
    FSK_12("FSK 12", 12),
    FSK_16("FSK 16", 16),
    FSK_18("FSK 18", 18),
    RATED_21("RATED 21", 21);

    private final int minAge;
    private final String viewName;

    AgeRating(String viewName, int minAge) {
        this.viewName = viewName;
        this.minAge = minAge;
    }

    public int getMinAge() {
        return minAge;
    }

    public String getViewName() {
        return viewName;
    }

    public static String[] getViewNames() {
        return Arrays.stream(AgeRating.values()).map(AgeRating::toString).toArray(String[]::new);
    }

    public static AgeRating fromString(String viewName) {
        for (AgeRating ageRating : AgeRating.values()) {
            if (ageRating.getViewName().equals(viewName)) {
                return ageRating;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return viewName;
    }

}