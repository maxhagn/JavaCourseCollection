/**
 * Hagn Maximilian
 * 11808237
 * Exercise 02
 **/

package at.tuwien.swtesting.shared;

public enum CreateBugStatus {
    CONFIRMED("v2_bug_status"),
    IN_PROGRESS("v3_bug_status"),
    RESOLVED("v4_bug_status");

    private final String text;

    CreateBugStatus(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }
}
