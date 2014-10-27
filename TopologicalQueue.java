package interviewquestions.other;

import java.util.*;

/**
 * Calculate the in-degree of graph.
 * 
 */
public class TopologicalQueue {
    public List<Integer> topologicalSort(DirectedGraph dag) {
        List<Integer> sorted = new ArrayList<Integer>();
        int[] degree = new int[dag.vertexNum()];

        /**
         * Here is one problem: how can we calculate the degree of all vertex on
         * a DAG? Actually this is a question: how do you traversal a graph?
         * Graph is different from a tree, you don't know which one is the
         * starting vertex.
         */
        // for (int v : dag.getVertexes()) {
        // if (!visited[v])
        // traversalDAG(dag, degree, visited, v);
        // }

        /**
         * The correct method of calculating in-degree is to traversal all
         * vertexes and their adjacency.
         */
        for (int i = 0; i < dag.vertexNum(); i++) {
            for (int w : dag.getAdjacencies(i)) {
                degree[w]++;
            }
        }

        /**
         * Use a queue to record all vertexes that has a incoming degree of 0
         */
        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < dag.vertexNum(); i++)
            if (degree[i] == 0)
                queue.offer(i);

        /**
         * Sort all vertex on the queue and then update their adjacency.
         */
        while (!queue.isEmpty()) {
            int v = queue.poll();
            sorted.add(v);
            for (int w : dag.getAdjacencies(v)) {
                degree[w]--;
                if (degree[w] == 0)
                    queue.offer(w);
            }
        }
        return sorted;
    }

    @SuppressWarnings("unused")
    private void traversalDAG(DirectedGraph dag, int[] degree,
            boolean[] visited, int start) {
        degree[start]++;
        if (visited[start])
            return;

        visited[start] = true;
        for (int w : dag.getAdjacencies(start))
            traversalDAG(dag, degree, visited, w);
    }

    public boolean isDag(int[] degree) {
        for (int dg : degree)
            if (dg == 0)
                return true;
        return false;
    }
}
