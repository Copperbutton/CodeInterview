/**
 * Given a start position and an target position on the grid. You can move
 * up,down,left,right from one node to another adjacent one on the grid. However
 * there are some walls on the grid that you cannot pass. Now find the shortest
 * path from the start to the target. (This is easy done by BFS) Extend. If you
 * can remove three walls, then what is the shortest path from start to the
 * target.
 */
package interviewquestions.google;

public class ShortestPathII {
    private static final int WALL = 1;
    private static final int VISITED = -1;
    private static final int UNVISITED = 0;

    public int findShortestPath(int[][] grid, Point start, Point end) {
        return findShortestPath(grid, start, end, 0);
    }

    private int findShortestPath(int[][] grid, Point curr, Point end,
            int crossWall) {
        int ROW = grid.length;
        int COL = (ROW == 0) ? 0 : grid[0].length;
        if (curr.x < 0 || curr.x >= ROW || curr.y < 0 || curr.y >= COL
                || grid[curr.x][curr.y] == VISITED
                || grid[curr.x][curr.y] == WALL && crossWall == 3)
            return -1;

        if (curr.x == end.x && curr.y == end.y)
            return 0;

        int step = -1, crossWallNew = crossWall;
        if (grid[curr.x][curr.y] != WALL) {
            grid[curr.x][curr.y] = VISITED;
        } else
            crossWallNew++;

        int count = findShortestPath(grid, new Point(curr.x + 1, curr.y), end,
                crossWallNew);
        if (count >= 0)
            step = step >= 0 ? Math.min(count + 1, step) : count + 1;
        count = findShortestPath(grid, new Point(curr.x - 1, curr.y), end,
                crossWallNew);
        if (count >= 0)
            step = step >= 0 ? Math.min(count + 1, step) : count + 1;
        count = findShortestPath(grid, new Point(curr.x, curr.y + 1), end,
                crossWallNew);
        if (count >= 0)
            step = step >= 0 ? Math.min(count + 1, step) : count + 1;
        count = findShortestPath(grid, new Point(curr.x, curr.y - 1), end,
                crossWallNew);
        if (count >= 0)
            step = step >= 0 ? Math.min(count + 1, step) : count + 1;

        if (grid[curr.x][curr.y] != WALL) {
            grid[curr.x][curr.y] = UNVISITED;
        } else
            crossWallNew--;
        return step;
    }

}
