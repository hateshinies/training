package com.greatestsasha.training.leetcode;

import java.util.Arrays;

public class DigitsAsArray {

    public static void main(String[] args) {
        int[] array = {9};
        int[] arr = Solution.plusOne(array);
        System.out.println(Arrays.toString(arr));
    }

    static class Solution {
        public static int[] plusOne(int[] digits) {
            int lastOne = digits.length - 1;
            if (digits[lastOne] == 9) {//разделить на девятки и остальные
                return increment(digits, 0);
            }
            digits[lastOne] = digits[lastOne] + 1;//случай без нового десятка
            return digits;

        }
    }

    private static int[] increment(int[] digits, int lastNineOffset) {
        int currentPosition = digits.length - lastNineOffset - 1;//or start with 1 and remove this -1
        if (currentPosition == 0) {
            if (digits[currentPosition] != 9) {
                digits[currentPosition] = digits[currentPosition] + 1;
                return digits;
            }
            int[] longArray = new int[digits.length + 1];
            if (digits.length - 1 - lastNineOffset == 0) {
                longArray[0] = 1;
                return longArray;
            }
            for (int j = 0; j <= digits.length - lastNineOffset; j++) {
                longArray[j] = digits[j];
            }
            longArray[lastNineOffset] = 1;

            return longArray;
        }

        int current = digits[currentPosition];
        if (current == 9) {
            digits[currentPosition] = 0;
            return increment(digits, lastNineOffset + 1);
        } else {
            digits[currentPosition] = digits[currentPosition] + 1;
            return digits;
        }
    }
    //по сути надо найти на какой цифре закончатся девятки и инкрементировать ее, взяв из массива начало
    //либо понять что мы на последней 9(первой) и вставить 1
}