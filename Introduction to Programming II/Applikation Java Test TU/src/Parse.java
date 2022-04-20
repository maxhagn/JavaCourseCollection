import java.util.HashMap;

// returns true, if s is a properly parenthesized string,
// otherwise false.  The opening parenthesis '(' is closed with
// ')', '[' with ']', '{' with '}', and '<' with '>'.

public class Parse {

    public static boolean parseRec(String s)
    {
        if (s == null || s.length() == 0) {
            return true;
        }
        for (int i = 0; i < s.length(); i++) {
            char currentChar = s.charAt(i);
            if (i + 1 <= s.length() - 1) {
                //Check if next char is closing parenthesis
                if (s.charAt(i + 1) == MyParenthesisPair.getInverse(currentChar)) {
                    String newString = s.substring(0, i) + s.substring(i + 2);
                    return parseRec(newString);
                }
            } else {
                //We searched through the whole array and didn't found any closing parenthesis
                //For example if the string length is uneven = 1
                return false;
            }
        }
        return false;
    }

    private static class MyParenthesisPair {

        private static HashMap<Character, Character> pairs = new HashMap<Character, Character>() {{
            put('(', ')');
            put('<', '>');
            put('{', '}');
            put('[', ']');
        }};

        public static Character getInverse(char s) {
            if (pairs.containsKey(s)) {
                return pairs.get(s);
            }
            return ' ';
        }

        public static boolean isClosingParenthesis(char s) {
            return pairs.containsValue(s);
        }
    }

    // returns true, if s is a properly parenthesized string, otherwise false
    public static boolean parseStack(String s)
    {
        if (s == null)
            return false;
        CharStack charStack = new CharStack();
        for (char currentChar : s.toCharArray()) {
            if (MyParenthesisPair.isClosingParenthesis(currentChar)) {
                //Found closing parenthesis but there is no matching opening parenthesis
                if (charStack.isEmpty()) {
                    return false;
                }
                //Closing parenthesis dosen't match opening parenthesis
                if (currentChar != MyParenthesisPair.getInverse(charStack.pop())) {
                    return false;
                }
            } else {
                charStack.push(currentChar);
            }
        }
        return charStack.isEmpty();
    }

    public static void main(String[] args) { }
}
