package interviewquestions.google;

import java.util.*;

/**
 * You are given a dictionary, in the form of a file that contains one word per
 * line. E.g., abacus deltoid gaff giraffe microphone reef qar You are also
 * given a collection of letters. E.g., {a, e, f, f, g, i, r, q}. The task is to
 * find the longest word in the dictionary that can be spelled with the
 * collection of letters. For example, the correct answer for the example values
 * above is “giraffe”. (Note that “reef” is not a possible answer, because the
 * set of letters contains only one “e”.)
 * 
 * @author zhangxiaokang
 */
public class LongestWordInDict {
    public String findLongestWordComb(Set<String> dict, char[] letters) {
        int[] countLetters = new int[25];
        int[] countWord = new int[25];
        String longestWord = "";
        genLetterStatics(new String(letters), countLetters);
        for (String word : dict) {
            genLetterStatics(word, countWord);
            if (canFormWordfromLetter(countWord, countLetters)
                    && word.length() > longestWord.length())
                longestWord = word;
            Arrays.fill(countWord, 0);
        }
        return longestWord;
    }

    public void genLetterStatics(String word, int[] count) {
        for (int i = 0; i < word.length(); i++) {
            count[word.charAt(i) - 'a']++;
        }
    }

    public boolean canFormWordfromLetter(int[] countWord, int[] countLetters) {
        for (int i = 0; i < countWord.length; i++) {
            if (countWord[i] > countLetters[i])
                return false;
        }
        return true;
    }
}
