package tree;

import static org.junit.Assert.*;

import org.junit.Test;

public class BinaryTreeTest {

	@Test
	public void testPrintInOrder() {
		BinaryTree bTree = createSampleBinaryTree();
		bTree.printInOrder();
	}
	
	@Test
	public void testPrintPreOrder() {
		BinaryTree bTree = createSampleBinaryTree();
		bTree.printPreOrder();
	}
	
	@Test
	public void testSize(){
		BinaryTree bTree = new BinaryTree(0); 
		assertEquals(bTree.size(), 1);
		bTree = createSampleBinaryTree();
		assertEquals(bTree.size(), 5);
	}
	
	@Test
	public void testMaxDepth() {
		BinaryTree bTree = new BinaryTree(0);
		assertEquals(bTree.maxDepth(), 1);
		assertEquals(createSampleBinaryTree().maxDepth(), 3);
	}
	
	@Test
	public void testHasPathSum() {
		BinaryTree bTree = createSampleBinaryTree();
		assertTrue(bTree.hasPathSum(4));
		assertTrue(bTree.hasPathSum(3));
		assertFalse(bTree.hasPathSum(2));
	}
	
	@Test
	public void testPrintPaths() {
		BinaryTree bTree = createSampleBinaryTree();
		bTree.printPaths();
	}
	
	@Test
	public void testMirror() {
		BinaryTree bTree = createSampleBinaryTree();
		bTree.mirror();
		bTree.printPaths();
	}
	
	@Test
	public void testDoubleTree() {
		BinaryTree bTree = createSampleBinaryTree();
		bTree.doubleTree();
	}

	@Test
	public void testSameTree() {
		BinaryTree bTree1 = createSampleBinaryTree();
		BinaryTree bTree2 = createSampleBinaryTree();
		assertTrue(bTree1.sameTree(bTree2));
		assertFalse(bTree1.sameTree(new BinaryTree(0)));
	}
	
	@Test
	public void testIsBst() {
		BinaryTree bTree = createSampleBinaryTree();
		assertFalse(bTree.isBST());
		BinarySearchTree bst = BinarySearchTreeTest.createSampleBst();
		assertTrue(bst.isBST());
	}
	
	/*        0
	 *       / \
	 *      1   4
	 *     / \
	 *    2   3
	 */    
	public static BinaryTree createSampleBinaryTree() {		
		BinaryTree bTree = new BinaryTree(0);
		
		Node node1 = new Node(1);
		node1.left = new Node(2);
		node1.right = new Node(3);
		
		bTree.root.left = node1;
		bTree.root.right = new Node(4);
		
		return bTree; 
	}

}
