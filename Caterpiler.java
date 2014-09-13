package interviewquestions.google;

public class Caterpiler {
    public int countUneatenLeave(int N, int[] A) {
        int[] leaves = new int[N];
        int count = N;
        for (int catp : A) {
            int pos = catp;
            while (pos <= N) {
                if (leaves[pos - 1] == 0) {
                    leaves[pos - 1] = 1;
                    count --;
                }
                pos += catp;
            }
        }
        return count;
    }
}
