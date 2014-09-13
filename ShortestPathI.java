/**
 * Given a start position and an target position on the grid. You can move
 * up,down,left,right from one node to another adjacent one on the grid. However
 * there are some walls on the grid that you cannot pass. Now find the shortest
 * path from the start to the target. (This is easy done by BFS)
 */
package interviewquestions.google;

public class ShortestPathI {
    private static final int WALL = 1;
    private static final int VISITED = -1;
    private static final int UNVISITED = 0;

    public int findShortestPath(int[][] grid, Point start, Point end) {
        if (start.x == end.x && start.y == end.y)
            return 0;

        int ROW = grid.length;
        int COL = ROW == 0 ? 0 : grid[0].length;
        if (start.x < 0 || start.x >= ROW || start.y < 0 || start.y >= COL
                || grid[start.x][start.y] == WALL
                || grid[start.x][start.y] == VISITED)
            return -1;

        int step = -1;
        grid[start.x][start.y] = VISITED;
        int count = findShortestPath(grid, new Point(start.x + 1, start.y), end);
        if (count >= 0)
            step = step > 0 ? Math.min(count + 1, step) : count + 1;
        count = findShortestPath(grid, new Point(start.x - 1, start.y), end);
        if (count >= 0)
            step = step > 0 ? Math.min(count + 1, step) : count + 1;
        count = findShortestPath(grid, new Point(start.x, start.y + 1), end);
        if (count >= 0)
            step = step > 0 ? Math.min(count + 1, step) : count + 1;
        count = findShortestPath(grid, new Point(start.x, start.y - 1), end);
        if (count >= 0)
            step = step > 0 ? Math.min(count + 1, step) : count + 1;
        grid[start.x][start.y] = UNVISITED;

        return step;
    }
}
