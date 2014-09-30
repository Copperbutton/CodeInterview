package interviewquestions.other;

/**
 * Given an array, shift it by K in place.
 * 
 * @author zhangxiaokang
 */
public class ShiftArrayByK {
    public void shiftArray(int[] array, int k) {
        int len = array.length;
        if (len == 0 || (k %= len) == 0)
            return;
        for (int count = 1; count < len/k; count ++) {
            swapK(array, len - k * (count + 1), len - k * count, k);
        }
        
        int firstStart = 0;
        int firstLen = len % k;
        int secondStart = firstStart + firstLen;
        int secondLen = k;
        while (firstStart != secondStart) {
            if (firstLen < secondLen) {
                secondLen = secondLen - firstLen;
                swapK(array, firstStart, secondStart + secondLen, firstLen);
            } else if (firstLen >= secondLen){
                firstLen = firstLen - secondLen;
                swapK(array, firstStart + firstLen, secondStart, secondLen);
                secondStart = firstStart + firstLen;
            }
        }
    } 
    
    /**
     * Swap an array by k parts.
     * */
    public void swapK(int[] array, int first, int second, int K) {
        for (int offset = 0; offset < K; offset ++) {
            int tmp = array[first + offset];
            array[first + offset] = array[second + offset];
            array[second + offset] = tmp;
        }
    }

}
