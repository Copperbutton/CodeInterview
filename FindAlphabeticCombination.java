package interviewquestions.facebook;

import java.util.*;

/**
 * If a=1, b=2, c=3,....z=26. Given a string, find all possible codes that
 * string can generate. Give a count as well as print the strings.
 * 
 * For example: Input: "1123". You need to general all valid alphabet codes from
 * this string.
 * 
 * Output List aabc //a = 1, a = 1, b = 2, c = 3 kbc // since k is 11, b = 2, c=
 * 3 alc // a = 1, l = 12, c = 3 aaw // a= 1, a =1, w= 23 kw // k = 11, w = 23
 */
public class FindAlphabeticCombination {
    public List<String> getAlphabeticCombinations(String src) {
        List<String> result = new ArrayList<String>();
        if (src == null)
            return null;

        searchCombinations(src, 0, new LinkedList<Character>(), result);
        return result;
    }

    private void searchCombinations(String src, int index,
            LinkedList<Character> path, List<String> result) {
        if (index == src.length()) {
            StringBuilder builder = new StringBuilder();
            for (Character ch : path)
                builder.append(ch);
            result.add(builder.toString());
            return;
        }

        if (src.charAt(index) == '0')
            searchCombinations(src, index + 1, path, result);
        else {
            int num = (int) (src.charAt(index) - '0');
            path.add((char) (num - 1 + 'a'));
            searchCombinations(src, index + 1, path, result);
            path.removeLast();

            if (index == src.length() - 1)
                return;
            int num2 = (int) (src.charAt(index + 1) - '0');
            num = num * 10 + num2;
            if (num > 26)
                return;
            path.add((char) (num - 1 + 'a'));
            searchCombinations(src, index + 2, path, result);
            path.removeLast();
        }
    }

}
