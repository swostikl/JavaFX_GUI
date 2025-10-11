package Task1;

public class PalindromeChecker {
    public boolean isPalindrome(String str) {
        if (str == null) {
            return false;
        }

        // Normalize string: remove non-alphanumeric, lowercase everything
        String clean = str.replaceAll("[^A-Za-z0-9]", "").toLowerCase();
        String reversed = new StringBuilder(clean).reverse().toString();
        return clean.equals(reversed);
    }
}
