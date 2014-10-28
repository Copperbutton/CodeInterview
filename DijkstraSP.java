package interviewquestions.other;

import java.util.*;

/**
 * Find the shortest path of an edge weighted directed graph using Dijkstra
 * algorithm.
 */
public class DijkstraSP {
    /**
     * As more edges are included into the T, the frontier edges should be
     * updated, their path to the V should be updated accordingly. This
     * operation requires a index heap, in which the nodes can be accessed by
     * their index. To implement this with existing data structures, we can use
     * a customed Node and a map to map those nodes.
     * 
     */
    class Node implements Comparable<Node> {
        int v;
        double weight;

        public Node(int v, double weight) {
            this.v = v;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node anotherNode) {
            if (this.weight < anotherNode.weight)
                return -1;
            else if (this.weight > anotherNode.weight)
                return 1;
            else
                return 0;
        }
    }

    private PriorityQueue<Node> minPQ;
    private Map<Integer, Node> nodeMap;
    private double[] distToS;
    private Edge[] edgeTo;

    public DijkstraSP(EdgeWeightedDiagram graph, int s) {
        if (s < 0 || s >= graph.getVertexNum())
            throw new IndexOutOfBoundsException(
                    "Error: starting vertex not in range.");

        /**
         * Needs to check negative cycle here, since Dijkstra can not process
         * negative cycle.
         */
        
        /** Use a priority queue to store the nodes on the frontier */
        minPQ = new PriorityQueue<Node>();
        /** Use a map to track those nodes so that they can be updated. */
        nodeMap = new HashMap<Integer, Node>();
        /**
         * Use an array to track the distacen of each nodes to s. Set all the
         * distance to positive infinity, set s distnce to 0
         */
        distToS = new double[graph.getVertexNum()];
        Arrays.fill(distToS, Double.POSITIVE_INFINITY);
        distToS[s] = 0;

        /** Use an array to track the path of the Dijkstra path */
        edgeTo = new Edge[graph.getVertexNum()];

        Node start = new Node(s, distToS[s]);
        minPQ.offer(start);
        nodeMap.put(s, start);
        while (!minPQ.isEmpty()) {
            Node minEdge = minPQ.poll();
            int v = minEdge.v;
            nodeMap.remove(v);
            for (Edge e : graph.getAdjs(v)) {
                int w = e.other(v);
                if (distToS[w] > distToS[v] + e.weight()) {
                    distToS[w] = distToS[v] + e.weight();
                    /** The directed edge to w is e */
                    edgeTo[w] = e;
                    /**
                     * Check if the nodes in heap or not, if in heap, then needs
                     * to update the edge. Else put into heap.
                     */
                    if (nodeMap.containsKey(w)) {
                        Node wNode = nodeMap.get(w);
                        minPQ.remove(wNode);
                        wNode.weight = distToS[w];
                        minPQ.offer(wNode);
                    } else {
                        Node wNode = new Node(w, distToS[w]);
                        nodeMap.put(w, wNode);
                        minPQ.offer(wNode);
                    }
                }
            }
        }

    }

    public double distTo(int v) {
        /** Check */
        return distToS[v];
    }

}
