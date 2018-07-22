package sandbox;

import java.util.ArrayList;

/**
 * Given an array of integers, return an array of integers such that
 * the value at index i of the output is the product of everything in the input
 * except the input value at index i.
 * An empty list should return [].
 * A list of length 1 should return [1] no matter what the input element is.
 *
 * Example:
 *
 * product_exclusion([2, 3, 4, 5])  #=> [60, 40, 30, 24]
 *
 * [3*4*5*1, 4*5*1, 5, 1]
 * [1, 2, 1*2*3, 1*2*3*4]
 *
 * [3*4*5, 2*4*5, 2*3*5, 2*3*4]
 *
 * 2*3*4*5
 * [1*3*4*5, 2*4*5, 1*2*3*5, 1*2*3*4]
 */
public class ProductExclusion {

    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<String>();
        strings.add("Hello, World!");
        strings.add("Welcome to CoderPad.");
        strings.add("This pad is running Java 8.");

        for (String string : strings) {
            System.out.println(string);
        }

        int[] result = mul(new int[] {2, 3, 4, 5});
        result = mul(new int[] {2});
        result = mul(new int[] {});
        System.out.println(result.length);
        for (int num : result) {
            System.out.println(num);
        }
    }

    private static int[] mul(int[] nums) {
        if (nums.length == 0) {
            return nums;
        }
        int[] mul1 = new int[nums.length];
        mul1[0] = 1;
        for (int pos = 1; pos < nums.length; pos++) {
            mul1[pos] = mul1[pos-1] * nums[pos-1];
        }

        int[] mul2 = new int[nums.length];
        mul2[nums.length - 1] = 1;
        for (int pos = nums.length-2; pos >= 0; pos--) {
            mul2[pos] = mul2[pos+1] * nums[pos+1];
        }

        int[] mul = new int[nums.length];
        for (int pos = 0; pos < nums.length; pos++) {
            mul[pos] = mul1[pos] * mul2[pos];
        }

        return mul;
    }
}
