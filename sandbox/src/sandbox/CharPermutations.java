package sandbox;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Given an integer n and a set of characters, return all the strings
 * of length n that use characters only from the given set
 * e.g.
 *
 * 3
 * a,b
 *
 * =>
 *
 * aaa,aab,aba,abb,baa,bab,bba,bbb
 *
 * ----------
 *
 * 2
 * abc
 *
 * aa,ab,ac,ba,bb,bc,ca,cb,cc
 *
 *
 * -----------
 */
public class CharPermutations {

    class Result {
        String str;
        List<Character> chars;
        String last;

        Result(List<Character> chars, int length) {
            this.str = getRepeatedString(chars.get(0), length);
            this.chars = chars;
            this.last = getRepeatedString(chars.get(chars.size() - 1), length);
        }
        Iterator getIterator() {
            return new Iterator() {
                @Override
                public boolean hasNext() {
                    return !str.equals(last);
                }

                @Override
                public String next() {
                    if (str.equals(last)) {
                        return null;
                    }
                    String curr = str;
                    str = increment(str);
                    return curr;
                }
            };
        }

        private String increment(String str) {
            return increment(str.toCharArray(), str.length() - 1);
        }

        private String getRepeatedString(char c, int length) {
            char[] chars = new char[length];
            for (int pos = 0; pos < length; pos++) {
                chars[pos] = c;
            }
            return new String(chars);
        }

        private String increment(char[] currChars, int pos) {
            char c = currChars[pos];
            int posInChars = chars.indexOf(c);
            if (posInChars == chars.size() - 1) {
                currChars[pos] = chars.get(0);
                increment(currChars, pos - 1);
            } else {
                currChars[pos] = chars.get(posInChars + 1);
            }
            return new String(currChars);
        }
    }
}
