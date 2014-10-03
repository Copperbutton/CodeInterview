package interviewquestions.linkedin;

import java.util.*;

class Node {
    final Node parent;
    final Node left;
    final Node right;


    public Node(Node parent, Node left, Node right) {
        this.parent = parent;
        this.left = left;
        this.right = right;
    }

    boolean isRoot() {
        return parent == null;
    }
}

public class FirstCommonAncestor {
    public Node commonAncestor(Node one, Node two) {
        LinkedList<Node> pathOne = new LinkedList<Node> ();
        LinkedList<Node> pathTwo = new LinkedList<Node> ();
        while (one != null) {
            pathOne.addFirst(one);
            one = one.parent;
        }
        
        while (two != null) {
            pathTwo.addFirst(two);
            two = two.parent;
        }
        
        int index = 0;
        for (Node node : pathOne) {
            if(pathTwo.size() > index && pathTwo.get(index++) == node)
                return node;
        }
        return null;
    }
}



