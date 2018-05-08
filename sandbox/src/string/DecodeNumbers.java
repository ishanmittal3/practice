package string;

/**
 *  Decode Numbers
 *
 *   A message containing letters from A-Z is being encoded to numbers using the following mapping:
 *
 *   'A' -> 1
 *   'B' -> 2
 *   ...
 *   'Z' -> 26
 *   Given a non-empty string containing only digits, determine the total number of ways to decode it.
 *
 *   Examples:
 *
 *   Input: "12"
 *   Output: 2
 *   (It could be decoded as "AB" (1 2) or "L" (12))
 *
 *
 *   Input: "226"
 *   Output: 3
 *   (It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).)
 */
public class DecodeNumbers {

    public static void main(String[] args) {
        // System.out.println('2' >= '0' && '2' <= '6');
        // System.out.println(decodeWays("226"));
        // System.out.println(decodeWays("01234"));
        // System.out.println(decodeWays("10123"));
        // System.out.println(decodeWays("301"));

        System.out.println(decodeWays("01"));
        System.out.println(decodeWays("1"));
        System.out.println(decodeWays("0"));
        System.out.println(decodeWays("10"));

        System.out.println(decodeWays("100"));
        System.out.println(decodeWays("101"));
        System.out.println(decodeWays("200"));
        System.out.println(decodeWays("300"));

        System.out.println(decodeWays("900"));
        System.out.println(decodeWays("401"));
        System.out.println(decodeWays("223"));
        System.out.println(decodeWays("1232132121321"));

        // /
        // assert decodeWays('01') == 0;
        // assert decodeWays('1') == 1;
        // assert decodeWays('0') == 0;
        // assert decodeWays('10') == 1;

        // assert decodeWays('100') == 0;
        // assert decodeWays('101') == 1;
        // assert decodeWays('200') == 0;
        // assert decodeWays('300') == 0;

        // assert decodeWays('900') == 0;
        // assert decodeWays('401') == 0;
        // assert decodeWays('223') == 3;
        // assert decodeWays('226') == 3;
        // assert decodeWays('227') == 2;
        // assert decodeWays('') == 0;
        // assert decodeWays('1111111') == 21;
        // assert decodeWays('2222222') == 21;
        // assert decodeWays('3333333') == 1;
        // assert decodeWays('9999999') == 1;
        // assert decodeWays('7591047212') == 3;
        // assert decodeWays('214321851') == 9;
        // assert decodeWays('1232132121321') == 144;
    }
    public static int decodeWays(String str) {
        return decodeWays(str.toCharArray(), 0);
    }

    private static int decodeWays(char[] chars, int start) {
        int end = chars.length - 1;
        if (start > end) {
            return 1;
        }
        if (chars[start] == '0') {
            return 0;
        }
        if (start == end) {
            return 1;
        }
        int count = decodeWays(chars, start+1);
        if (isValid(chars[start], chars[start+1])) {
            count += decodeWays(chars, start+2);
        }
        return count;
    }

    private static boolean isValid(char c1, char c2) {
        if (c1 == '1') {
            return (c2 >= '0' && c2 <= '9');
        }
        if (c1 == '2') {
            return (c2 >= '0' && c2 <= '6');
        }
        return false;
    }
}
