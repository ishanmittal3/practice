package linkedin;

import java.util.ArrayList;
import java.util.List;

public class FirstCommonAncestorImpl implements FirstCommonAncestor {

	@Override
	public Node commonAncestor(Node node1, Node node2) {
        List<Node> path1 = pathToRoot(node1);
        List<Node> path2 = pathToRoot(node2);
        int pos1 = path1.size() - 1;
        int pos2 = path2.size() - 1;
        Node curr1 = path1.get(pos1);
        Node curr2 = path2.get(pos2);
        while(pos1 >= 0 && pos2 >=0 && curr1.equals(curr2)) {
            pos1--;
            pos2--;
            curr1 = path1.get(pos1);
            curr2 = path2.get(pos2);
        }
        return path1.get(pos1 + 1);
    }
    
    List<Node> pathToRoot(Node node) {
        List<Node> path = new ArrayList<Node>();
        while(!node.isRoot()) {
            path.add(node);
            node = node.parent;
        }
        path.add(node);
        return path;
    }
}
