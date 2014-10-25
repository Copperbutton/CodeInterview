package interviewquestions.other;

import java.util.*;

public class WeightedGraph {
    private int edgeNum;
    private final int vertexNum;
    private final Set<Edge>[] adjs;

    @SuppressWarnings("unchecked")
    public WeightedGraph(int vertexNum) {
        if (vertexNum <= 0)
            throw new IllegalArgumentException(
                    "Error: vertex number must be positive");
        this.edgeNum = 0;
        this.vertexNum = vertexNum;
        this.adjs = (Set<Edge>[]) (new Set[vertexNum]);
        for (int i = 0; i < vertexNum; i++)
            adjs[i] = new HashSet<Edge>();
    }

    public int edgeNum() {
        return this.edgeNum;
    }

    public int vertexNum() {
        return this.vertexNum;
    }

    public void addEdge(Edge e) {
        int v = e.either();
        int w = e.other(v);
        if (v < 0 || v >= vertexNum || w < 0 || w >= vertexNum)
            throw new IndexOutOfBoundsException("Error: vretex index from 0 ~ "
                    + vertexNum);
        adjs[v].add(e);
        adjs[w].add(e);
        this.edgeNum++;
    }

    public Iterable<Edge> getAdjacency(int v) {
        if (v < 0 || v >= vertexNum)
            throw new IndexOutOfBoundsException();
        return new HashSet<Edge>(adjs[v]);
    }

    public Iterable<Edge> getEdges() {
        Set<Edge> edges = new HashSet<Edge>();
        for (int i = 0; i < vertexNum; i++) {
            int selfLoop = 0;
            for (Edge e : getAdjacency(i)) {
                if (e.other(i) > i)
                    edges.add(e);
                /** Add one time only for each self loop */
                else if (e.other(i) == i) {
                    if (selfLoop % 2 == 0)
                        edges.add(e);
                    selfLoop++;
                }

            }
        }
        return edges;
    }
    
    public String toString() {
        String NEW_LINE = System.getProperty("line.separator");
        StringBuilder builder = new StringBuilder();
        builder.append(vertexNum + " " + edgeNum + NEW_LINE);
        for (int v = 0; v < vertexNum; v++) {
            builder.append(v);
            builder.append(": ");
            for (Edge e : getAdjacency(v)) {
                builder.append(e + " ");
            }
            builder.append(NEW_LINE);
        }
        return builder.toString();
    }

}
