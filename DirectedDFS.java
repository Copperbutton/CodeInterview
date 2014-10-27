package interviewquestions.other;

/**
 * Given a directed graph and a source or a set of source, find out if there is
 * a directed path to any of other vertex. This is because for directed graph,
 * not every vertex is reachable.
 */
public class DirectedDFS {
    private boolean[] reachable;

    /** Number of vertex that reachable from s */
    private int count;

    public DirectedDFS(DirectedGraph graph, int s) {
        reachable = new boolean[graph.vertexNum()];
        count = 0;
        DFS(graph, s);
    }

    public DirectedDFS(DirectedGraph graph, Iterable<Integer> sources) {
        reachable = new boolean[graph.vertexNum()];
        this.count = 0;
        for (int s : sources)
            DFS(graph, s);
    }

    /**
     * Reachable is also used for marking if the vertex has been visited.
     * 
     */
    public void DFS(DirectedGraph graph, int v) {
        count++;
        reachable[v] = true;
        for (int w : graph.getAdjacencies(v))
            if (!reachable[w])
                DFS(graph, w);
    }

    public boolean reachable(int v) {
        return reachable[v];
    }

    public int getCount() {
        return count;
    }
}
