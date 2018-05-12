package tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class Cousins {

    List<Node> getCousins(Node root, Node node) {
        Stack<StackNode> stack = new Stack<>();
        Map<Integer, List<StackNode>> levels = new HashMap<>();
        int level = 1;
        StackNode stackNode = null;
        stack.push(new StackNode(root, null, 1));
        while (!stack.empty()) {
            StackNode top = stack.pop();
            addToMap(top, levels);
            if (top.node == node) {
                level = top.level;
                stackNode = top;
            }
            if (top.node.left != null) {
                stack.push(new StackNode(top.node.left, top.node, 1 + top.level));
            }
            if (top.node.right != null) {
                stack.push(new StackNode(top.node.right, top.node, 1 + top.level));
            }
        }
        List<StackNode> levelNodes = levels.get(level);
        List<Node> cousins = new ArrayList<>();
        for (StackNode levelNode : levelNodes) {
            if (levelNode.parent != stackNode.parent) {
                cousins.add(levelNode.node);
            }
        }
        return cousins;
    }

    private void addToMap(StackNode top, Map<Integer,List<StackNode>> levels) {
        int level = top.level;
        List<StackNode> stackNodes = levels.containsKey(level)
            ? levels.get(level)
            : new ArrayList<>();
        stackNodes.add(top);
        levels.put(level, stackNodes);
    }

    class StackNode {
        Node node;
        Node parent;
        int level;

        public StackNode(Node node, Node parent, int level) {
            this.node = node;
            this.parent = parent;
            this.level = level;
        }
    }
}
