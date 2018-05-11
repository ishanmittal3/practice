package tree;

public class SumTree {

    /**
     * Given a binary tree, check if it's a sum tree or not.
     * In a sum tree, value of each
     */
    boolean isSumTree(Node root) {
        return getSum(root) == root.value;
    }

    private Integer getSum(Node node) {
        if (node.left == null && node.right == null) {
            return node.value;
        }
        int sum = 0;
        if (node.left != null) {
            Integer leftSum = getSum(node.left);
            if (leftSum == null) {
                return null;
            } else {
                sum += leftSum;
            }
        }
        if (node.right != null) {
            Integer rightSum = getSum(node.right);
            if (rightSum == null) {
                return null;
            } else {
                sum += rightSum;
            }
        }
        if (sum == node.value) {
            return sum + node.value;
        } else {
            return null;
        }
    }
}
