package interviewquestions.facebook;

/**
 * Code a function that receives a string composed by words separated by spaces
 * and returns a string where words appear in the same order but than the
 * original string, but every word is inverted.
 */
public class ReverseStentense {
    public String reverseStr(String src) {
        if (src == null || src.length() == 0)
            return "";

        char[] array = src.toCharArray();
        for (int start = 0; start < array.length;) {
            // Skip leading space
            while (start < array.length && array[start] == ' ')
                start++;

            if (start == array.length)
                break;

            // Find the end of this non space substring
            int end = start;
            while (start < array.length && array[start] != ' ')
                start++;

            // reverse
            reverseSubarray(array, end, start - 1);
        }

        StringBuilder builder = new StringBuilder();
        for (char ch : array)
            builder.append(ch);

        return builder.toString();
    }

    private void reverseSubarray(char[] array, int start, int end) {
        while (start < end) {
            char tmp = array[start];
            array[start++] = array[end];
            array[end--] = tmp;
        }
    }

}
