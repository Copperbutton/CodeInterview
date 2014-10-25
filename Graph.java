package interviewquestions.other;

import java.util.*;

/**
 * Implement graph with adjacent list. Instead of implementing a graph node and
 * then put all of them into an array, its better to use an set array graph
 * directly. One draw back of this implementation is that the weight of the
 * graph can not be represented.
 */
public class Graph {
    private Set<Integer>[] vertex;
    private int vertexNum;
    private int edgeNum;

    @SuppressWarnings("unchecked")
    public Graph(int vertexNum) {
        if (vertexNum <= 0)
            throw new IllegalArgumentException(
                    "Vertex number must be positive.");
        this.vertexNum = vertexNum;
        this.edgeNum = 0;
        this.vertex = (Set<Integer>[]) (new Set[vertexNum]);
        for (int i = 0; i < vertexNum; i++) {
            vertex[i] = new HashSet<Integer>();
        }
    }

    public int getEdgeNum() {
        return this.edgeNum;
    }

    public int getVettexNum() {
        return this.vertexNum;
    }

    public void addEdge(int v, int w) {
        if (v < 0 || v >= vertexNum || w < 0 || w >= vertexNum)
            throw new IndexOutOfBoundsException();
        this.edgeNum++;
        vertex[v].add(w);
        vertex[w].add(v);
    }

    public Iterator<Integer> getAdjcencyIterator(int v) {
        if (v < 0 || v >= vertexNum)
            throw new IndexOutOfBoundsException();
        return vertex[v].iterator();
    }

    public Set<Integer> getAdjcency(int v) {
        if (v < 0 || v >= vertexNum)
            throw new IndexOutOfBoundsException();
        return new HashSet<Integer>(vertex[v]);
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        String NEW_LINE = System.getProperty("line.seperator");
        for (int v = 0; v < vertexNum; v++) {
            builder.append(v);
            builder.append(": ");
            for (int adj : vertex[v]) {
                builder.append(adj);
                builder.append(", ");
            }
            builder.append(NEW_LINE);
        }
        return builder.toString();
    }
}
