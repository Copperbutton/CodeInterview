package interviewquestions.facebook;

/**
 * Given a matrix consisting of 0's and 1's, find the largest connected
 * component consisting of 1's.
 */

public class ConnectedComponents {
    public int largestConnectedComponent(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return 0;

        int ROW = matrix.length;
        int COL = matrix[0].length;

        // Simplified union find data structure
        int[] parent = new int[ROW * COL];
        int[] weight = new int[ROW * COL];

        // Initiate union find: set '1' cell
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (matrix[i][j] == 1) {
                    int index = i * COL + j;
                    parent[index] = index;
                    weight[index] = 1;
                }
            }
        }

        // Do uion on each cell
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (i < ROW - 1 && matrix[i][j] == 1 && matrix[i + 1][j] == 1)
                    union(parent, weight, i * COL + j, (i + 1) * COL + j);

                if (j < COL - 1 && matrix[i][j] == 1 && matrix[i][j + 1] == 1)
                    union(parent, weight, i * COL + j, i * COL + j + 1);
            }
        }

        // Count the largest weight, which is the largest connected component
        int max = 0;
        for (int val : weight)
            max = Math.max(val, max);

        return max;
    }

    // Pay attention to the union: union small to larger one
    private void union(int[] parent, int[] weight, int first, int second) {
        int root1 = root(parent, weight, first);
        int root2 = root(parent, weight, second);

        if (root1 == root2)
            return;

        if (weight[root1] > weight[root2]) {
            parent[root2] = root1;
            weight[root1] += weight[root2];
        } else {
            parent[root1] = root2;
            weight[root2] += weight[root1];
        }
    }

    // Find root of index, while doing path compression in the process.
    private int root(int[] parent, int[] weight, int index) {
        int parIndex = parent[index];
        while (parent[index] != parIndex) {
            int grandparent = parent[parIndex];
            if (grandparent != parIndex) {
                parent[index] = grandparent;
                weight[parIndex]--;
            }

            index = parent[grandparent];
            parIndex = parent[index];
        }

        return parIndex;
    }
}
