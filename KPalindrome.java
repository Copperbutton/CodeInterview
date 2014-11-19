package interviewquestions.facebook;

/**
 * Check if one string can be transfered into a palindrome with k deleteion. 
 * */
public class KPalindrome {
    public boolean isKPalindrome(String str, int K) {
        if (str == null || K < 0)
            return false;
        
        String revers = new StringBuilder(str).reverse().toString();
        return modifiedEditDistance(str, revers, K) <= 2*K + 1;
    }
    
    private int modifiedEditDistance(String str, String rever, int K) {
        int len = str.length();
        int[] editDist = new int[len + 1];
        for (int i = 0; i < len + 1; i++)
            editDist[i] = i;        

        for (int r = 1; r <= len; r++) {
            int replace = editDist[0];
            editDist[0] = r;
            int from = Math.max(1, r - K);
            int to = Math.min(r + K, len);
            for (int c = from; c <= to; c++) {
                int insert = editDist[c];
                if (str.charAt(r - 1) == rever.charAt(c - 1))
                    editDist[c] = replace;
                
                // delete distance
                editDist[c] = Math.min(editDist[c], editDist[c - 1] + 1);    
                // insert distance
                editDist[c] = Math.min(editDist[c], insert + 1);

                // update replace 
                replace = insert;
            }
        }
        return editDist[len];
    }
    
    private int modifiedEditDistanceII(String str, String rev, int K) {
        int len = str.length();
        
        // Array to record the min dist
        int[] dist = new int[len + 1];
        for (int i = 0; i <= len; i++)
            dist[i] = i;
        
        // Compute edit distance;
        for (int i = 1; i <= len; i++) {
            dist[0] = i;
            int from = Math.max(i - K, 1);
            int to = Math.min(i + K, len);
            int replace = dist[Math.max(from - 1, 0)];
            for (int j = from; j <= to; j++) {
                int currDist = Math.min(dist[j - 1], dist[j]) + 1;
                
                // Only when str[i - 1] == rev[i - 1] should we compare this
                if (str.charAt(i - 1) == rev.charAt(j - 1))
                    currDist = Math.min(replace, currDist);
                
                // Update
                replace = dist[j];
                dist[j] = currDist;
            }
        }   
        return dist[len];
    }
}
