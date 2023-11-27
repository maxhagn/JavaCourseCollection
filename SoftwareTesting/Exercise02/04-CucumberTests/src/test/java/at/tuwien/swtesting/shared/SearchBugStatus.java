/**
 * Hagn Maximilian
 * 11808237
 * Exercise 02
 **/

package at.tuwien.swtesting.shared;

public enum SearchBugStatus {
    CLOSED("__closed__"),
    OPEN("__open__"),
    ALL("__all__");

    private final String text;

    SearchBugStatus(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public static SearchBugStatus fromText(String text) {
        for (SearchBugStatus value : SearchBugStatus.values()) {
            if (value.getText().equals(text)) {
                return value;
            }
        }
        throw new IllegalArgumentException("Conversion Error");
    }
}
