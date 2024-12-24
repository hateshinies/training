package com.greatestsasha.training.leetcode;

import java.util.ArrayList;
import java.util.List;

public class MaxLengthGreedy {

    public static void main(String[] args) {
        List<String> wrdsLst1 = List.of("This", "is", "an");
        List<String> wrdsLst2 = List.of("example", "of", "text");
        List<String> wrdsLst3 = List.of("justification.");
        String string = countPositions(wrdsLst2, 16);


        String[] words = {"This", "is", "an", "example", "of", "text", "justification."};
        List<String> result = fullJustify(words, 16);
        System.out.println(result);
    }

    public static List<String> fullJustify(String[] words, int maxWidth) {
        List<Integer> indexes = new ArrayList<>();
        int position = 0;
        for (int i = position; position < words.length; i++) {
            String word = words[position];
            if (word.length() < maxWidth) {//надо найти сколько слов вместится
                position = findPosition(words, maxWidth, 0, position);
                indexes.add(position);
                System.out.println("cur =" + position + " line = " + i);
            }
        }
        return fillList(words, indexes, maxWidth);
    }

    private static List<String> fillList(String[] words, List<Integer> indexes, int maxLength) {
        List<String> result = new ArrayList<>();
        int startPos = 0;
        for (int index : indexes) {
            String cur = "";//count sum between startPos and next. then calc spaces count!
            List<String> curWords = new ArrayList<>();
            for (int j = startPos; j < index; j++) {
                cur += words[j] + " ";
                curWords.add(words[j]);
            }
            if (index == words.length) {
                cur = fillWithSpaces(cur, maxLength);
                //fill with spaces List curWords todo!
            }
            result.add(cur);
            startPos = index;
        }
        return result;
    }

    private static String fillWithSpaces(String cur, int maxLength) {
        for (int i = cur.length(); i < maxLength; i++) {
            cur += " ";
        }
        return cur;
    }

    private static int findPosition(String[] words, int maxWidth, int previousSum, int position) {
        if (position == words.length) {//end reached
            return position;
        }
        int curSum = words[position].length() + previousSum;
        if (maxWidth > curSum) {
            return findPosition(words, maxWidth, curSum + 1, position + 1);
        } else if (maxWidth == curSum) {
            return position;
        }
        return position;
    }

    private static String countPositions(List<String> words, int maxLength) {
        int sumOfWordsLength = words.stream().map(String::length).reduce(0, Integer::sum);//длина всех слов
        int spacesCount = maxLength - sumOfWordsLength;//кол-во пробелов к-е надо вставить
        int wordsCount = words.size();//кол-во слов
        int step = spacesCount / (wordsCount - 1);//сколько пробелов вставляем за раз

        String tail = "";//остаток из пробелов который впихнем в конце
        int rest = spacesCount % (wordsCount + 1);
        for (int i = 0; i < rest; i++) {
            tail += " ";//неправильно считалось! todo
        }

//        if (rest <= spacesCount) {
//            return smallestCase(words, rest, step);
//        }
        if (rest != 0) {
            //then lastone + rest
        }


        String result = "";
        String spacesStep = "";
        for (int i = 0; i < step; i++) {
            spacesStep += " ";
        }

        for (int i = 0; i < words.size(); i++) {
            if (words.size() - 1 != i) {
                result += words.get(i) + spacesStep;
            } else {
                result += words.get(i);
            }
        }
        if (tail != "") {
            int firstSpaceIndex = result.indexOf(" ");
            result = result.substring(firstSpaceIndex) + tail + result.substring(firstSpaceIndex + 1);
        }

        return result;
    }

    private static String smallestCase(List<String> words, int rest, int step) {
        //если одно слово то в конец, если более - то в середину
        String tail = "";
        for (int i = 0; i < rest; i++) {
            tail += " ";
        }

        String result = "";
        String spacesStep = "";
        for (int i = 0; i < step; i++) {
            spacesStep += " ";
        }

        for (int i = 0; i < words.size(); i++) {
            result += spacesStep + words.get(i);
        }

        result += tail;

        return result;
    }
}