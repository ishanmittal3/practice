package tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Stack;

public class BinaryTree {
	
	public Node root;
	
	/*public ArrayList<int> inOrderTraversal() {
		if ()
	}*/
	
	public BinaryTree(int data) {
		root = new Node(data);
	}
	
	public void printInOrder() {
		printInOrder(root);
	}
	
	public void printPreOrder() {
		printPreOrder(root);
	}

	public static void printInOrder(Node node) {
		if (node == null) {
			return;
		}
		printInOrder(node.left);
		System.out.println(node.data);
		printInOrder(node.right);
	}
	
	public static void printPreOrder(Node node) {
		if (node == null) {
			return;
		}
		System.out.println(node.data);
		printPreOrder(node.left);
		printPreOrder(node.right);
	}
	
	public int size() {
	    return size(root);
	}

	public static int size(Node node) {
	    if (node == null) {
	        return 0;
	    }
	    return 1 + size(node.left) + size(node.right);
	}
	
	public Node dfs(int key) {
		return dfs(this.root, key);
	}
	
	public static Node dfs(Node root, int key) {
	    
	    if (root == null) {
	        return null;
	    }

	    Stack<Node> stack = new Stack<Node>();
	    stack.push(root);
	    
	    while (!stack.empty()) {
	        Node top = stack.pop();
	        if (top.data == key) {
	            return top;
	        }
	        if (top.left != null) {
	            stack.push(top.left);
	        }
	        if (top.right != null) {
	            stack.push(top.right);
	        }
	    }
	    return null;
	}
	
	public static Node bfs(Node root, int key) {

	    if (root == null) {
	        return null;
	    }

	    ArrayDeque<Node> queue = new ArrayDeque<Node> ();
	    queue.addLast(root);

	    while (!queue.isEmpty()) {
	        Node front = queue.poll();

	        if (front.data == key) {
	            return front;
	        }

	        if (front.left != null) {
	            queue.addLast(front.left);
	        }

	        if (front.right != null) {
	            queue.addLast(front.right);
	        }
	    }
	    return null;
	}
	
	public int maxDepth() {
	    return maxDepth(this.root);
	}

	public int maxDepth(Node node) {
	    if (node == null) {
	        return 0;
	    }
	    return 1 + Math.max(maxDepth(node.left), 
	                        maxDepth(node.right));
	}
	
	public boolean hasPathSum(int sum) {
	    return hasPathSum(this.root, sum);
	}

	public static boolean hasPathSum(Node node, int sum) {
	    if (node == null) {
	    	return sum == 0;
	    } 
	    
	    return hasPathSum(node.left,  sum - node.data) ||
	           hasPathSum(node.right, sum - node.data);
	}
	
	public void printPaths() {
	    printPaths(this.root, new ArrayList<Integer>());
	}

	public static void printPaths(Node node, ArrayList<Integer> path) {
	    if (node == null) {
	        return;
	    }
	    		    
	    ArrayList<Integer> newPath = (ArrayList<Integer>) path.clone();
	    newPath.add(Integer.valueOf(node.data));
	    
	    if (node.isLeaf()) {
	    	printPath(newPath);
	    	return;
	    }
	    
	    printPaths(node.left , newPath);
	    printPaths(node.right, newPath);
	    
	    /* The following alternate approach won't work
	     * because we'll append a node to the same list every time,
	     * even when we move back up the tree and start on a new branch.
	     *    
	     * path.add(Integer.valueOf(node.data));
	     
	     if (node.isLeaf()) {
		    	printPath(path);
		    	return;
	     }
	     
	     printPaths(node.left , path);
		 printPaths(node.right, path);
		 */
	}

	public static void printPath(ArrayList<Integer> path) {
	    for (Integer i : path) {
	        System.out.print(i.intValue() + " ");
	    }
	    System.out.println();
	}
	
	public void mirror() {
	    mirror(this.root);
	}

	public static void mirror(Node node) {
	    if (node == null) {
	        return;
	    }
	    Node temp = node.left;
	    node.left = node.right;
	    node.right = temp;
	    
	    mirror(node.left);
	    mirror(node.right);
	}
	
	public void doubleTree() {
	    doubleTree(this.root);
	}

	public static void doubleTree(Node node) {
	    if (node == null) {
	        return;
	    }
	    doubleTree(node.right);
	    Node newNode = new Node(node.data);
	    newNode.left = node.left;
	    node.left = newNode;
	    
	    doubleTree(newNode.left);
	}
	
	public boolean sameTree(BinaryTree otherBTree) {
	    return sameNode(this.root, otherBTree.root);
	}

	public static boolean sameNode(Node node1, Node node2) {
	    if (node1 == null && node2 == null) {
	        return true;
	    }
	    if (node1 == null || node2 == null) {
	        return false;
	    }
	    if (node1.data != node2.data) {
	        return false;
	    }
	    return sameNode(node1.left , node2.left ) &&
	           sameNode(node1.right, node1.right);
	}
	
	public boolean isBST() {
	    return isBST(this.root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	public boolean isBST(Node node, int min, int max) {
	    if (node == null) {
	        return true;
	    }
	    if (node.data > max || node.data < min) {
	        return false;
	    }

	    return isBST(node.left, min, node.data -1) && 
	           isBST(node.right, node.data, max);
	}
}
