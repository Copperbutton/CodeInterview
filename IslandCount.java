package interviewquestions.facebook;

import java.util.*;

public class IslandCount {
    public final static int SEA = 0;
    public final static int ISLAND = 1;

    class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public int countIslands(int[][] islands) {
        if (islands == null || islands.length == 0)
            return 0;
        int ROW = islands.length;
        int COL = islands[0].length;
        int count = 0;
        boolean[][] visited = new boolean[ROW][COL];
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (islands[i][j] == SEA || visited[i][j])
                    continue;
                serachAndMark(islands, visited, i, j);
                count++;
            }
        }
        return count;
    }

    public void serachAndMark(int[][] islands, boolean[][] visited, int x, int y) {
        Queue<Node> queue = new LinkedList<Node>();
        queue.offer(new Node(x, y));
        while (!queue.isEmpty()) {
            Node currNode = queue.poll();
            if (currNode.x < 0 || currNode.x >= islands.length
                    || currNode.y < 0 || currNode.y >= islands[0].length)
                continue;
            if (islands[currNode.x][currNode.y] == SEA
                    || visited[currNode.x][currNode.y])
                continue;
            visited[currNode.x][currNode.y] = true;
            queue.offer(new Node(currNode.x, currNode.y + 1));
            queue.offer(new Node(currNode.x, currNode.y - 1));
            queue.offer(new Node(currNode.x + 1, currNode.y));
            queue.offer(new Node(currNode.x - 1, currNode.y));
            queue.offer(new Node(currNode.x + 1, currNode.y - 1));
            queue.offer(new Node(currNode.x + 1, currNode.y + 1));
            queue.offer(new Node(currNode.x - 1, currNode.y + 1));
            queue.offer(new Node(currNode.x - 1, currNode.y - 1));
        }
    }

}
