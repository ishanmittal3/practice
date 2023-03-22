package string;

import java.util.ArrayList;
import java.util.List;

public class BalanceBrackets {

    public static void main(String args[]) {
        System.out.println(balance(")()((hb))())"));
    }

    public static String balance(String str) {
        //int pos = 0;
        int opens = 0;
        List<Character> chars = getChars(str);
        List<Character> ref1 = new ArrayList<>();
        for (char c : chars) {
            if (c == '(') {
                opens++;
                ref1.add(c);
            } else {
                if (c == ')') {
                    if (opens > 0) {
                        opens--;
                        ref1.add(c);
                    }
                } else {
                    ref1.add(c);
                }
            }
        }
        StringBuffer ref2 = new StringBuffer();
        int close = 0;
        for (int pos = ref1.size()-1; pos >= 0; pos--) {
            char c = ref1.get(pos);
            if (c == ')') {
                close++;
                ref2.append(c);
            } else {
                if (c == '(') {
                    if (close > 0) {
                        close--;
                        ref2.append(c);
                    }
                } else {
                    ref2.append(c);
                }
            }
        }
        //StringBuffer sb = new StringBuffer(ref2);
        return ref2.reverse().toString();
    }

    public static List<Character> getChars(String str) {
        List<Character> list = new ArrayList<>();
        for (int pos = 0; pos < str.length(); pos++) {
            list.add(str.charAt(pos));
        }
        return list;
    }
}