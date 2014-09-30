package interviewquestions.google;

/**
 * Solve this problem in O(n) time.
 * 
 * O(n) time, O(n) space.
 * 
 * Algorithm from Career cup implemented by
 * 
 * @author zhangxiaokang
 */
public class MaxDifferentSubArrayII {
    public int maxDifferentSubArray(int[] array) {
        int maxDiff = -1;
        int len = array.length;
        if (len < 2)
            return maxDiff;

        int[] leftMax = new int[len];
        int[] leftMin = new int[len];
        int[] rightMax = new int[len];
        int[] rightMin = new int[len];

        leftMax[0] = array[0];
        rightMin[0] = array[0];
        for (int i = 1; i < len; i++) {
            leftMax[i] = Math.max(array[i], array[i] + leftMax[i - 1]);
            leftMin[i] = Math.min(array[i], array[i] + leftMin[i - 1]);
        }

        rightMax[len - 1] = array[len - 1];
        rightMin[len - 1] = array[len - 1];
        for (int i = len - 2; i >= 0; i--) {
            rightMax[i] = Math.max(array[i], array[i] + rightMax[i + 1]);
            rightMin[i] = Math.min(array[i], array[i] + rightMax[i + 1]);
        }

        for (int i = 0; i < len - 1; i++) {
            int currMax = Math.abs(leftMax[i] - rightMin[i + 1]);
            if (currMax > maxDiff)
                maxDiff = currMax;
            currMax = Math.abs(leftMin[i] - rightMax[i + 1]);
            if (currMax > maxDiff)
                maxDiff = currMax;
        }

        return maxDiff;
    }
}