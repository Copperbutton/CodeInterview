package interviewquestions.other;

import java.util.*;

/**
 * Implement a Kruskal MST algorithm. Kruskal basic principle is to find out the
 * current minimal edge form all edges, and skip those that could make a cycle.
 * For find out the minimal edge, its best to use a priority queue. For
 * considering if adding one edge will result in a cycle, the best way is to use
 * a union find.
 */
public class KruskalMST {
    public Edge[] generateMST(WeightedGraph graph) {
        int vertexNum = graph.vertexNum();
        UnionFind<Integer> uf = new UnionFind<Integer>(vertexNum);
        PriorityQueue<Edge> minHeap = new PriorityQueue<Edge>();
        for (Edge e : graph.getEdges())
            minHeap.add(e);
        for (int i = 0; i < vertexNum; i++)
            uf.add(i);
        
        Edge[] MST = new Edge[vertexNum - 1];
        for (int count = 0; count < vertexNum - 1;) {
            Edge nextEdge = minHeap.poll();
            int v = nextEdge.either();
            int w = nextEdge.other(v);
            if (uf.connected(v, w))
                continue;
            MST[count++] = nextEdge;
            uf.union(v, w);
        }
        return MST;
    }

}
