package interviewquestions.facebook;

public class SerializeBinaryTree {
    public String serializeBinaryTree(BinaryTreeNode root) {
        if (root == null)
            return "{}";
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        builder.append(root.val);
        builder.append(serializeBinaryTree(root.left));
        builder.append(serializeBinaryTree(root.right));
        builder.append("}");
        return builder.toString();
    }
    
    public BinaryTreeNode deserizlizeBinaryTree(String str) {
        if (str == null || str.length() == 0)
            return null;
        int endOfVal = getEndOfVal(str);
        if (endOfVal == 1)
            return null;
        BinaryTreeNode root = new BinaryTreeNode(Integer.parseInt(str.substring(1, endOfVal)));
        int endOfLeftSub = getEndOfSub(str, endOfVal + 2);
        root.left = deserizlizeBinaryTree(str.substring(endOfVal + 2, endOfLeftSub));
        root.right = deserizlizeBinaryTree(str.substring(endOfLeftSub + 2, str.length() - 1));
        return root;
    }
    
    private int getEndOfVal(String str) {
        int endIndex = 1;
        while (endIndex < str.length() && str.charAt(endIndex) != '{')
            endIndex ++;
        return endIndex;
    }
    
    private int getEndOfSub(String str, int start) {
        int end = start + 1;
        int leftBracket = 1;
        while (leftBracket != 0) {
            if (str.charAt(end) == '{')
                leftBracket ++;
            else if (str.charAt(end) == '}')
                leftBracket --;
            end++;
        }
        return end;
    }
    

}
