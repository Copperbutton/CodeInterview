package interviewquestions.other;

import java.util.*;

/**
 * Implement an eager Prim MST algorithm. On the lazy Prim algorithm, we use the
 * edge as the way to measure the distance to the MST, but we do not
 * distinguish which edge is longer.
 */
public class EagerPrimMST {
    class Node implements Comparable<Node> {
        int v;
        double distToTree;

        @Override
        public int compareTo(Node node) {
            if (this.distToTree > node.distToTree)
                return 1;
            else if (this.distToTree < node.distToTree)
                return -1;
            else
                return 0;
        }

        public Node(int v, double distToTree) {
            this.v = v;
            this.distToTree = distToTree;
        }
    }

    public int[] genPrimMST(WeightedGraph graph) {
        int vertexNum = graph.vertexNum();
        int[] parent = new int[vertexNum];
        boolean[] visited = new boolean[vertexNum];
        double[] distToTree = new double[vertexNum];
        Arrays.fill(distToTree, Double.POSITIVE_INFINITY);

        PriorityQueue<Node> heap = new PriorityQueue<Node>();
        Map<Integer, Node> map = new HashMap<Integer, Node>();
        distToTree[0] = 0;
        scan(graph, 0, visited, heap, map, distToTree, parent);
        /**
         * This is the reason why we need to store both node number and their
         * distance on the node, because we need to know which distance belongs
         * to which.
         */
        while (!heap.isEmpty()) {
            Node nextMin = heap.poll();
            int v = nextMin.v;
            map.remove(v);
            scan(graph, v, visited, heap, map, distToTree, parent);
        }

        return parent;
    }

    private void scan(WeightedGraph graph, int v, boolean[] visited,
            PriorityQueue<Node> heap, Map<Integer, Node> map,
            double[] disitToTree, int[] parent) {
        visited[v] = true;
        for (Edge adj : graph.getAdjacency(v)) {
            int w = adj.other(v);
            if (visited[w])
                continue;
            if (adj.weight() < disitToTree[w]) {
                disitToTree[w] = adj.weight();
                parent[w] = v;

                /**
                 * Here is the key, from one node, we only store one edge which
                 * is the minimal edge on the heap. In order to update the dist,
                 * we need an map to map the node. If this ndoe not on the heap,
                 * should add it.
                 */
                if (map.containsKey(w)) {
                    Node node = map.get(w);
                    heap.remove(node);
                    node.distToTree = disitToTree[w];
                    heap.add(node);
                } else {
                    Node node = new Node(w, disitToTree[w]);
                    heap.add(node);
                    map.put(w, node);
                }
            }

        }
    }

}
