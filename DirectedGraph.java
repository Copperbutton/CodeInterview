package interviewquestions.other;

import java.util.*;

/**
 * Implement a directed graph.
 */
public class DirectedGraph {
    private int edgeNum;
    private final int vertexNum;
    private Set<Integer>[] adj;
    private boolean[] hasVertex;

    @SuppressWarnings("unchecked")
    public DirectedGraph(int vertexNum) {
        if (vertexNum < 0)
            throw new IllegalArgumentException(
                    "Error: vertex number must be positive.");

        this.edgeNum = 0;
        this.vertexNum = vertexNum;
        this.adj = (Set<Integer>[]) new Set[vertexNum];
        for (int i = 0; i < vertexNum; i++)
            adj[i] = new HashSet<Integer>();
        this.hasVertex = new boolean[vertexNum];
    }

    public int edgeNum() {
        return this.edgeNum;
    }

    public int vertexNum() {
        return this.vertexNum;
    }

    public void addEdge(int from, int to) {
        if (from < 0 || from >= vertexNum || to < 0 || to >= vertexNum)
            throw new IndexOutOfBoundsException();
        adj[from].add(to);
        edgeNum++;
        hasVertex[from] = true;
        hasVertex[to] = true;
    }

    public Iterable<Integer> getVertexes() {
        List<Integer> vertexs = new ArrayList<Integer>();
        for (int i = 0; i < this.vertexNum; i++) {
            if (hasVertex[i])
                vertexs.add(i);
        }
        return vertexs;
    }

    public Iterable<Integer> getAdjacencies(int v) {
        if (v < 0 || v >= vertexNum)
            throw new IndexOutOfBoundsException();
        return new HashSet<Integer>(adj[v]);
    }

    public DirectedGraph reverse() {
        DirectedGraph R = new DirectedGraph(vertexNum);
        for (int v = 0; v < vertexNum; v++) {
            for (int w : getAdjacencies(v))
                R.addEdge(w, v);
        }
        return R;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        String NEW_LINE = System.getProperty("line.separator");
        builder.append(vertexNum + " vertices, " + edgeNum + " edge num"
                + NEW_LINE);
        for (int v = 0; v < vertexNum; v++) {
            builder.append(String.format("%d:", v));
            for (int w : getAdjacencies(v))
                builder.append(String.format(" %d", w));
            builder.append(NEW_LINE);
        }
        return builder.toString();
    }

}
