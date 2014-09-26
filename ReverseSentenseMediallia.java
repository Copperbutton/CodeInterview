package interviewquestions.other;

/**
 * Interview Question from Medallia.
 * 
 * Reverse sentense, make the words stay they same and keep the order of
 * punction.
 * 
 * */
public class ReverseSentense {
    public String reverseSentense(char[] sentense) {
        int start = 0;
        int end = sentense.length - 1;
        while (sentense[start] == ' ')
            start++;
        while (sentense[end] == ' ' || isPuncuation(sentense[end]))
            end--;
        reverseCharArray(sentense, start, end);

        boolean space = false;
        for (int next = start; next <= end;) {
            if (space) {
                while (next <= end
                        && (sentense[next] == ' ' || isPuncuation(sentense[next])))
                    next++;
                reverseCharArray(sentense, start, next - 1);
                start = next;
                space = false;
            } else {
                while (next <= end && sentense[next] != ' '
                        && !isPuncuation(sentense[next]))
                    next++;
                reverseCharArray(sentense, start, next - 1);
                start = next;
                space = true;
            }
        }
        return new String(sentense);
    }

    private void reverseCharArray(char[] array, int start, int end) {
        while (start < end) {
            char tmp = array[start];
            array[start++] = array[end];
            array[end--] = tmp;
        }
    }

    private static char[] puncts = { ',', '.', '!', '?' };

    private boolean isPuncuation(char ch) {
        for (char pun : puncts)
            if (ch == pun)
                return true;
        return false;
    }
}
