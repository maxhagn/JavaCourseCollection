import java.util.Arrays;

// Objects of CharStack are stacks holding chars.  The stack is
// implemented using an array, without using other classes.
// The array grows whenever the number of entries is insufficient.

public class CharStack {

    private char[] stack;
    private int lastpop;

    // Constructor
    public CharStack() {
        this.stack = new char[10];
    }

    // Push c on CharStack
    public void push(char c) {
        if (this.stack[this.stack.length - 1] != 0) {
            this.stack = Arrays.copyOf(this.stack, this.stack.length + 1);
        }

        for (int i = 0; i < this.stack.length; i++) {
            if (this.stack[i] == 0) {
                this.stack[i] = c;
                lastpop = i+1;
                break;
            }
        }
    }

    // Pop the most recent character that was pushed, but has not been popped yet
    public char pop() {
        if (!isEmpty()) {
            return this.stack[--lastpop];
        } else {
            return ' ';
        }
    }

    // returns true if all characters pushed on 'this' have been popped.
    public boolean isEmpty() {
        if (lastpop == 0)
        {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) { }
}
