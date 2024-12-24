package com.greatestsasha.training.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Solution {

    public static void main(String[] args) {
        letterCombinations("23");
    }

    private static final Map<Character, String> map = Map.of(
            '2', "abc",
            '3', "def",
            '4', "ghi",
            '5', "jkl",
            '6', "mno",
            '7', "pqrs",
            '8', "tuv",
            '9', "wxyz"
    );

    public static void letterCombinations(String digits) {
        char[] digitsCharArray = digits.toCharArray();
        List<String> result = new ArrayList<>();

        for (Character digit : digitsCharArray) {
            String strings = map.getOrDefault(digit, "");
            result.add(strings);
        }
        var results = permutation(result);
        System.out.println(results);
    }

    private static List<String> permutation(List<String> strings) {
        List<String> result = new ArrayList<>();//abc, def -> ad, be, cf ...
        String buffer = "";
        //a - d - x
        //b - e - z
        //c - f - y
        //      - w

        int biggestLength = getBiggestLength(strings);
        System.out.println("biggestLength: " + biggestLength);

        for (int j = 0; j < biggestLength; j++) {
            for (String cur : strings) {
                if (cur.length() > j) {
                    char c = cur.charAt(j);
                    buffer += String.valueOf(c);//берем ad, be, cf
                }
            }
            permutation("", buffer, result);
            buffer = "";
        }

        return result;
    }

    private static int getBiggestLength(List<String> strings) {
        return strings.stream().map(String::length).sorted().reduce(0, Integer::max);
    }

    private static void permutation(String prefix, String str,
                                    List<String> result) {
        int length = str.length();
        if (length != 0) {
            for (int i = 0; i < length; i++) {
                permutation(prefix + str.charAt(i),  str.substring(0, i) + str.substring(i + 1, length),    result);
            }
        } else {
            result.add(prefix);
        }
    }
}