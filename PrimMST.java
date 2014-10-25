package interviewquestions.other;

import java.util.*;

/**
 * Implement an Prim MST algorithm. This is an lazy implementation. I use the
 * the edge to represent the distance to the tree. This is OK, but not
 * efficient.
 * 
 */
public class PrimMST {
    /**
     * This priority queue is used to get the minimal length of crossing edge.
     */
    public int[] generateMSTwithPrim(WeightedGraph graph) {
        int vertexNum = graph.vertexNum();
        boolean[] visited = new boolean[vertexNum];
        int[] parent = new int[vertexNum];
        PriorityQueue<Edge> heap = new PriorityQueue<Edge>();
        /**
         * Starting from any given vertex, for instance 0. get current minimal
         * distance cross edge from heap.
         */
        scan(graph, 0, heap, visited);
        while (!heap.isEmpty()) {
            Edge nextMin = heap.poll();
            int v = nextMin.either();
            int w = nextMin.other(v);
            if (visited[v] && visited[w])
                continue;
            if (!visited[v]) {
                scan(graph, v, heap, visited);
                parent[v] = w;
            }
            if (!visited[w]) {
                scan(graph, w, heap, visited);
                parent[w] = v;
            }
        }
        return parent;
    }

    /**
     * Scan every edge of given vertex. If not visited, add to the heap.
     */
    public void scan(WeightedGraph graph, int v, PriorityQueue<Edge> heap,
            boolean[] visited) {
        if (visited[v])
            return;
        visited[v] = true;
        for (Edge adj : graph.getAdjacency(v)) {
            if (!visited[adj.other(v)])
                heap.offer(adj);
        }
    }
    
    
}
