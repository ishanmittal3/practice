package tree;

import java.util.*;

// clari interview
public class LevelQuotaReport {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode parent;
        TreeNode(int x) { val = x; }

        @Override
        public String toString() {
            return String.valueOf(val);
        }
    }

    /*
     * Complete the 'getLevelReport' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts STRING treeNodes as parameter.
     *
     * At each level, sum up the nodes at that level, plus sum of all leaf nodes from all levels above.
     * The last value of the report will basically be the sum of all the tree's leaf nodes.
     */
    public static List<Integer> getLevelReport(String treeNodes) {
        // Write your code here
        // Step 1: Build the tree
        TreeNode root = buildTree(treeNodes);
        // Step 2: generate report
        return getReport(root, getDepth(root));
    }

    public static TreeNode buildTree(String treeNodes) {
        boolean left = true;
        String[] vals = treeNodes.split(",");
        Stack<TreeNode> stack = new Stack<>();
        TreeNode root = null;
        for (String s : vals) {
            int val = Integer.parseInt(s);
            if (val == -1) {
                if (left) {
                    left = false;
                } else {
                    TreeNode top = stack.pop();
                    while (!stack.isEmpty() && (top.parent != null) && (top.parent.right == top)) {
                        top = stack.pop();
                    }
                }
            } else {
                TreeNode node = new TreeNode(val);
                if (left) {
                    if (!stack.isEmpty()) {
                        node.parent = stack.peek();
                        node.parent.left = node;
                    } else {
                        root = node;
                    }
                } else { // stack will be empty only for the root, in which case we'll start from the left
                    // So we don't need to check for empty stack here
                    node.parent = stack.peek();
                    node.parent.right = node;
                }
                left = true; // start with left on the new node
                stack.push(node);
            }
        }
        return root;
    }

    public static List<Integer> getReport(TreeNode node, int depth) {
        List<Integer> res = new ArrayList<>();
        if (node == null) {
            return res;
        }
        Queue<TreeNode> q = new ArrayDeque<>();
        int counter = 0;
        int currDepth = 1;
        q.add(node);
        q.add(new TreeNode(-1));
        while (!q.isEmpty()) {
            TreeNode front = q.poll();
            if (front.val == -1) {
                res.add(counter);
                counter = 0;
                currDepth++;
                if (!q.isEmpty()) {
                    q.add(new TreeNode(-1));
                }
            } else {
                counter += front.val;
                if (front.left != null)  {
                    q.add(front.left);
                }
                if (front.right != null) {
                    q.add(front.right);
                }
                if (front.left == null && front.right == null && currDepth != depth) { // leaf node not at bottom level
                    q.add(front);
                }

            }
        }
        return res;
    }

    public static List<Integer> getReport(TreeNode node) {
        return getReport(node, getDepth(node));
    }

    public static int getDepth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(getDepth(node.left), getDepth(node.right));
    }

    public static void main(String[] args) {
        TreeNode root = buildTree("400,250,150,-1,-1,150,-1,-1,200,-1,-1");
//        TreeNode root = buildTree("400,250,100,-1,-1,-1,-1");
        System.out.println(getReport(root));
    }
}
