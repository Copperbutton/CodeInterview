package interviewquestions.google;

import java.util.*;

/**
 * Replace all 1 bit at an binary string with all candidates. If there is an
 * restriction that one candidates should be used for once, can add an array
 * indicating if candidates has been used.
 */
public class BinaryStringCombination {
    public List<String> genCombination(String binary, char[] candidates) {
        List<String> comb = new ArrayList<String>();
        if (binary == null || binary.length() == 0)
            return comb;
        searchCombination(binary, binary.toCharArray(), candidates, comb, 0);
        return comb;
    }

    /**
     * Search combinations.
     */
    private void searchCombination(String binary, char[] path,
            char[] cadidates, List<String> comb, int start) {

        boolean hasComb = false;
        for (int index = start; index < binary.length(); index++) {
            if (binary.charAt(index) == '0')
                continue;

            hasComb = true;
            for (char ch : cadidates) {
                path[index] = ch;
                searchCombination(binary, path, cadidates, comb, index + 1);
            }
            path[index] = binary.charAt(index);
            break;
        }

        /**
         * Pay attention to the checking here: the ending condition is quite
         * different from other problems: we needs to find the next position to
         * replace and replace it with all possible chars. This should be done
         * one iteration by anther. When there is no combination anymore, taht
         * means the iteration should end then.
         */
        if (!hasComb) {
            comb.add(new String(path));
        }
    }
}
