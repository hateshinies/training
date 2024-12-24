package com.greatestsasha.training.leetcode;

class StringsAndSpaces {

    public static void main(String[] args) {
        int res = lengthOfLastWord("luffy is still joyboy");
        System.out.println(res);
    }

    public static int lengthOfLastWord(String s) {
        char[] chars = s.toCharArray();
        int length = chars.length;
        boolean firstLetterFound = false;
        int counter = 0;

        for (int i = length; i > 0; i--) {
            char cur = chars[i - 1];

            if (cur == ' ' && firstLetterFound) {
                return counter;
            } else if (cur != ' ') {
                if (!firstLetterFound) {
                    firstLetterFound = true;
                    counter++;
                } else {
                    counter++;
                }
            }
        }

        return counter;
    }
}