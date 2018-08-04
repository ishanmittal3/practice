package sandbox;

/**
 * Write a program to print the following output
 * 1
 * 11
 * 21
 * 1211
 * 111221
 */
public class CountNums {

    private static String getCounts(String input) {
        char[] chars = input.toCharArray();
        int count = 1;
        char curr = chars[0];
        StringBuilder sb = new StringBuilder();
        for (int pos = 1; pos < chars.length; pos++) {
            if (chars[pos] == curr) {
                count++;
            } else {
                sb.append(count).append(curr);
                count = 1;
                curr = chars[pos];
            }
        }
        sb.append(count).append(curr);
        return sb.toString();
    }
}
