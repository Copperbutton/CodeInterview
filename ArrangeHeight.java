package interviewquestions.google;

import java.util.*;

/**
 * You are given two array, first array contain integer which represent heights
 * of persons and second array contain how many persons in front of him are
 * standing who are greater than him in term of height and forming a queue. Ex
 * A: 3 2 1 B: 0 1 1 It means in front of person of height 3 there is no person
 * standing, person of height 2 there is one person in front of him who has
 * greater height then he, similar to person of height 1. Your task to arrange
 * them Ouput should be. 3 1 2 Here - 3 is at front, 1 has 3 in front ,2 has 1
 * and 3 in front.
 */
/**
 * Main idea of this problem is that, when sorting in descending order, and we
 * insert from the back to the front, wont affects the already existed order.
 */

public class ArrangeHeight {
    class HeightNode implements Comparable<HeightNode> {
        int height;
        int tallerPersonInFront;

        HeightNode(int height, int tallerPersonInFront) {
            this.height = height;
            this.tallerPersonInFront = tallerPersonInFront;
        }

        @Override
        public int compareTo(HeightNode arg0) {
            return arg0.height - this.height;
        }
    }

    public void findArrange(int[] height, int[] tallerPerson) {
        int len = height.length;
        /** Create an array that contains all info */
        List<HeightNode> nodes = new LinkedList<HeightNode>();
        for (int i = 0; i < len; i++) {
            nodes.add(new HeightNode(height[i], tallerPerson[i]));
        }
        /** Sort them in descending order of their height */
        Collections.sort(nodes);

        /** Swap the nodes with what their position should be */
        for (int i = 1; i < len; i++) {
            HeightNode node = nodes.get(i);
            int tallerIndex = node.tallerPersonInFront;
            nodes.remove(i);
            nodes.add(tallerIndex, node);
        }

        /** Copy back all results */
        for (int i = 0; i < len; i++) {
            height[i] = nodes.get(i).height;
        }
        return;
    }
}
