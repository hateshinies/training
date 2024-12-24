package com.greatestsasha.training.leetcode;

import java.util.Arrays;

public class Stairs {

    public static int climbStairs(int n) {
        int[] array = new int[n];
        Arrays.fill(array, -1);
        return backTrack(n - 1, array);
    }

    private static int backTrack(int cur, int[] array) {
        if (cur <= 0) {        //условие выхода когда в результате вычитания получилось 0 или меньше
            return 1;
        }
        if (array[cur] != -1) {
            return array[cur];//что это за случай?
        }
        int result = array[cur] =
                backTrack(cur - 1, array) +          //return 1 from beginning
                        backTrack(cur - 2, array);      //return 1 from beginning

        return result;//заходим в рекурсию и ищем сколько раз спустимся
    }


    public static void main(String[] args) {
        int result = climbStairs(4);
        System.out.println(result);
    }
}