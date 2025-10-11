/**
 * The StringManipulator class provides basic string operations such as concatenation,
 * case conversion, and substring checking.
 */
public class StringManipulator {

    /**
     * Concatenates two input strings and returns the result.
     *
     * @param str1 First string
     * @param str2 Second string
     * @return Concatenated string
     */
    public String concatenate(String str1, String str2) {
        return str1 + str2;
    }

    /**
     * Returns the length of the input string.
     *
     * @param str Input string
     * @return Length of the string
     */
    public int findLength(String str) {
        return str.length();
    }

    /**
     * Converts the input string to uppercase.
     *
     * @param str Input string
     * @return Uppercase version of the string
     */
    public String convertToUpperCase(String str) {
        return str.toUpperCase();
    }

    /**
     * Converts the input string to lowercase.
     *
     * @param str Input string
     * @return Lowercase version of the string
     */
    public String convertToLowerCase(String str) {
        return str.toLowerCase();
    }

    /**
     * Checks if the input string contains the given substring.
     *
     * @param str Input string
     * @param subStr Substring to search for
     * @return true if str contains subStr, false otherwise
     */
    public boolean containsSubstring(String str, String subStr) {
        return str.contains(subStr);
    }
}
