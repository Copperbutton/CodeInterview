package interviewquestions.google;

import java.util.*;

/**
 * Given a undirected graph with corresponding edges. Find the number of
 * possible triangles?
 */
class GraphNode {
    int val;
    Set<GraphNode> neighbors;

    GraphNode(int val) {
        this.val = val;
        this.neighbors = new HashSet<GraphNode>();
    }
}

public class DetectTriangle {
    /**
     * There are two ways to detect duplicates: 1) ignore duplicates, divide the
     * result by 2 at end. Because for each triangle, two edge will be
     * visited(why third will not? Because we used a global hash map to record
     * visited nodes.), will generate double triangle. 2) Use an extra set to
     * record the neighbor nodes that has been visited, skip that from the third
     * node list. Because, when we visit the visited neighbor nodes, current
     * node has not been visited, and will be found as triangle, thus current
     * must be duplicate.
     */
    public int detectTriangleInGraph(GraphNode node) {
        Queue<GraphNode> queue = new LinkedList<GraphNode>();
        Set<GraphNode> visited = new HashSet<GraphNode>();
        queue.offer(node);
        visited.add(node);
        int triangleNum = 0;
        while (!queue.isEmpty()) {
            GraphNode currNode = queue.poll();
            Set<GraphNode> visitedNeighbors = new HashSet<GraphNode>();
            for (GraphNode neighbor : currNode.neighbors) {
                if (visited.contains(neighbor))
                    continue;
                List<GraphNode> triangleNodes = findTriangle(currNode, neighbor);
                for (GraphNode thrid : triangleNodes)
                    if (!visitedNeighbors.contains(thrid))
                        triangleNum++;
                queue.add(neighbor);
                visited.add(neighbor);
                visitedNeighbors.add(neighbor);
            }
        }
        return triangleNum;
    }

    /**
     * One must be neighbor of two.
     */
    public List<GraphNode> findTriangle(GraphNode one, GraphNode two) {
        List<GraphNode> threes = new ArrayList<GraphNode>();
        Set<GraphNode> oneNeighbors = one.neighbors;
        for (GraphNode twoNeighbor : two.neighbors) {
            if (oneNeighbors.contains(twoNeighbor))
                threes.add(twoNeighbor);
        }
        return threes;
    }

}