package interviewquestions.other;

import java.util.*;

/**
 * Detect cycle from a directed graph. Detect cycle from directed graph. Only an
 * cycle when all the next edge the same with the start edge. This required at
 * most v * v time. which is not very efficiency.
 */
public class DirectedCycle {
    public List<String> detectDirectedCycle(DirectedGraph graph) {
        List<String> cycle = new ArrayList<String>();
        for (int v : graph.getVertexes()) {
            boolean[] visited = new boolean[graph.vertexNum()];
            searcCycle(graph, cycle, new LinkedList<Integer>(), visited, v, v);
        }
        return cycle;
    }

    private void searcCycle(DirectedGraph graph, List<String> result,
            LinkedList<Integer> path, boolean[] visited, int start, int curr) {
        if (visited[curr]) {
            if (curr == start) {
                path.add(curr);
                StringBuilder builder = new StringBuilder();
                for (int v : path)
                    builder.append(" " + v);
                result.add(builder.toString());
                path.removeLast();
            }
            return;
        }

        visited[curr] = true;
        path.add(curr);
        for (int w : graph.getAdjacencies(curr)) {
            searcCycle(graph, result, path, visited, start, w);
        }
        path.removeLast();
        visited[curr] = false;
    }
}
