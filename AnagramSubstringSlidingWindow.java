package interviewquestions.google;

/**
 * Solved this problem with a sliding widow
 */
public class AnagramSubstringSlidingWindow {
    public boolean isAnagramSubString(String src, String target) {
        if (src == null || target == null || src.length() < target.length())
            return false;

        int[] expected = new int[256];
        int[] found = new int[256];
        for (int i = 0; i < target.length(); i++) {
            expected[target.charAt(i)]++;
            found[src.charAt(i)]++;
        }

        /**
         * Sliding window is better than not, since each time you only have to
         * update two slot, instead of updating the entier one
         */
        for (int i = 0; i < src.length() - target.length(); i++) {
            for (int j = 0; j < target.length(); j++) {
                if (expected[src.charAt(i + j)] != found[src.charAt(i + j)])
                    break;
                if (j == target.length() - 1)
                    return true;
            }
            /** Window sliding right */
            found[src.charAt(i)]--;
            found[src.charAt(i + target.length())]++;
        }
        return false;
    }

}
