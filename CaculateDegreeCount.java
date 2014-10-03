package interviewquestions.medallia;

import java.util.*;

public class CalculateDegreeCount {

    /**
     * For the graph traversing algorithm.
     */
    private static class Node {
        private final Set<Node> neighbors;

        Node() {
            this.neighbors = new HashSet<Node>();
        }
    }

    /** @return a map from degree to number of nodes of that degree */
    private static Map<Integer, Integer> calculateDegreeCount(Node node) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer> ();
        Set<Node> visited = new HashSet<Node> ();
        Stack<Node> stack = new Stack<Node> ();
        stack.push(node);
        while (!stack.isEmpty()) {
            Node tmp = stack.pop();
            visited.add(tmp);
            int degree = tmp.neighbors.size();
            int num = 1;
            if (map.containsKey(degree))
                num = map.get(degree) + 1;
            map.put(degree, num);
            
            for (Node neighbor : tmp.neighbors) {
                if (visited.contains(neighbor))
                    continue;
                stack.push(neighbor);
            }
        }
        return map;
    }

    /**
     * Parses the input edge list into connected {@link Node} graph.
     * 
     * Do not modify this method.
     */
    public static void main(String[] args) {
        String line;
        Scanner stdin = new Scanner(System.in);
        Map<Integer, Node> graph = new HashMap<Integer, Node>();
        Node firstNode = null;

        while (stdin.hasNextLine() && !(line = stdin.nextLine()).equals("")) {
            String[] tokens = line.split(" ");
            // Edge list can only be one or two nodes per line.
            if (tokens.length != 1 && tokens.length != 2) {
                throw new RuntimeException("Unknown format");
            }

            for (int i = 0; i < tokens.length; i++) {
                int id = Integer.parseInt(tokens[i]);
                if (!graph.containsKey(id)) {
                    graph.put(id, new Node());
                }
            }

            Node node0 = graph.get(Integer.parseInt(tokens[0]));
            if (firstNode == null) {
                firstNode = node0;
            }

            if (tokens.length == 2) {
                Node node1 = graph.get(Integer.parseInt(tokens[1]));

                node0.neighbors.add(node1);
                node1.neighbors.add(node0);
            }

        }
        stdin.close();

        TreeMap<Integer, Integer> treeMap = new TreeMap<Integer, Integer>(calculateDegreeCount(firstNode));
        System.out.println(treeMap);
    }

}