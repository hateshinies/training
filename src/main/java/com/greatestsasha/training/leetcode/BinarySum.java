package com.greatestsasha.training.leetcode;

public class BinarySum {
    public static void main(String[] args) {
        String s = addBinary("100", "111");
        System.out.println(s);
    }


    public static String addBinary(String a, String b) {
        return sum(a, b);
    }

    private static String sum(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int carry = 0;//Keeps track of the carry value during addition.
        int i = a.length() - 1;//Pointer for the end of string a
        int j = b.length() - 1;//Pointer for the end of string b

        while (i >= 0 || j >= 0 || carry == 1) {
            if (i >= 0) {
                carry += a.charAt(i--) - '0';
            }
            if (j >= 0) {
                carry += b.charAt(j--) - '0';
            }
            sb.append(carry % 2);//Appends the current digit (0 or 1) to sb.
            carry /= 2;//Updates the carry for the next iteration
        }

        return sb.reverse().toString();
    }
}
