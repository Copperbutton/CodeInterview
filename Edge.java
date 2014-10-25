package interviewquestions.other;

public class Edge implements Comparable<Edge> {
    private final int v;
    private final int w;
    private final double weight;

    public Edge(int v, int w, double weight) {
        if (v < 0 || w < 0)
            throw new IllegalArgumentException("Error: Invalid argument");
        if (Double.isNaN(weight))
            throw new IllegalArgumentException("Error: weight not a number");
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge arg) {
        if (this.weight() < arg.weight())
            return -1;
        else if (this.weight() > arg.weight())
            return 1;
        else
            return 0;
    }

    public double weight() {
        return this.weight;
    }

    public int either() {
        return v;
    }

    public int other(int vertex) {
        if (vertex == v)
            return w;
        else if (vertex == w)
            return v;
        else
            throw new IllegalArgumentException(
                    "Error: Illegal endpoint of the edge");
    }

    public String toString() {
        return String.format("%d-%d %.5f", v, w, weight);
    }

}
