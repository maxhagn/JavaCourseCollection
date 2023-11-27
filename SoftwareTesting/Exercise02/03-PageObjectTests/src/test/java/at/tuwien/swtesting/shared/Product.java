/**
 * Hagn Maximilian
 * 11808237
 * Exercise 02
 **/

package at.tuwien.swtesting.shared;

public enum Product {
    TEST_PRODUCT("TestProduct");

    private final String text;

    Product(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public static Product fromText(String text) {
        for (Product value : Product.values()) {
            if (value.getText().equals(text)) {
                return value;
            }
        }
        throw new IllegalArgumentException("Conversion Error");
    }
}
