package interviewquestions.facebook;

import java.util.Arrays;

public class LongestNoCompleteSubstring {
    public int findLongestNonCompleteSubstring(String str) {
        if (str == null)
            return 0;
        
        int unique = 0, total = 1;
        int[] found = new int[256];
        for (int i = 0; i < str.length(); i++) {
            if(++found[str.charAt(i)] == 1)
                unique ++;;
        }
        if (unique <= 1)
            return total;
        
        Arrays.fill(found, 0);
        for (int left = 0, right = 0, count = 0; right < str.length(); right++) {
            if (++found[str.charAt(right)] == 1)
                count ++;
            while (left < right && count == unique) {
                char ch = str.charAt(left++);
                if (--found[ch] == 0)
                    count --;
            }
            total += (right - left + 1);
        }
        return total;
    }

}
