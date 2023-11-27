/**
 * Hagn Maximilian
 * 11808237
 * Exercise 02
 **/

package at.tuwien.swtesting.shared;

public enum Resolution {
    FIXED("v2_resolution"),
    INVALID("v3_resolution"),
    WONT_FIX("v4_resolution"),
    DUPLICATE("v5_resolution"),
    WORKS_FOR_ME("v6_resolution");

    private final String text;

    Resolution(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }
}
