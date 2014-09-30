package interviewquestions.google;

public class ArrayPartitionII {
    public void partitionArray(int[] array) {
        int len = array.length;
        int[] result = new int[len];
        int currNeg = 0;
        for (int i = 0; i < len; i++) {
            if (array[i] < 0)
                result[currNeg ++] = array[i];
        }
        
        for (int j = len -1, currPos = len - 1; j >= 0; j--) {
            if (array[j] >= 0)
                array[currPos --] = array[j];
        }

        for (int i = 0; i < currNeg; i++)
            array[i] = result[i];
    }
}