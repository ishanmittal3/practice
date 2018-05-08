package tree;

/**
 * Given a binary tree, count the number of unival subtrees
 * (all nodes have same value).
 */
public class UnivalSubtrees {

    int getUnivalSubtrees(Node node) {
        return countTrees(node).count;
    }

    Result countTrees(Node node) {
        if (node.left == null && node.right == null) {
            return new Result(true, 1);
        }
        int count = 0;
        boolean leftUnival = true;
        if (node.left != null) {
            Result leftResult = countTrees(node.left);
            leftUnival = leftResult.unival;
            count += leftResult.count;
        }
        boolean rightUnival = true;
        if (node.right != null) {
            Result rightResult = countTrees(node.right);
            rightUnival = rightResult.unival;
            count += rightResult.count;
        }
        boolean unival = leftUnival && rightUnival;
        if (node.left != null) {
            unival = unival && node.value == node.left.value;
        }
        if (node.right != null) {
            unival = unival && node.value == node.right.value;
        }
        if (unival) {
            count++;
        }
        return new Result(unival, count);
    }

    class Result {
        boolean unival;
        int count;

        public Result(boolean unival, int count) {
            this.unival = unival;
            this.count = count;
        }
    }
}
