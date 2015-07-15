package tree;

import static org.junit.Assert.*;

import org.junit.Test;

public class BinarySearchTreeTest {

	@Test
	public void testInsert() {
		BinarySearchTree bst = createSampleBst();
		bst.printPreOrder();
	}

	@Test
	public void testCreateMinimalHeightBst() {
		BinarySearchTree bst = BinarySearchTree.createMinimalHeightBst(
				new int[] {0,1,2,3,4});
		bst.printPreOrder();
	}
	
	@Test
	public void testMinValue() {
		BinarySearchTree bst = createSampleBst();
		assertEquals(bst.minValue(), 0);
	}
	
	@Test
	public void testCountTrees() {
		assertEquals(BinarySearchTree.countTrees(1), 1);
		assertEquals(BinarySearchTree.countTrees(2), 2);
		assertEquals(BinarySearchTree.countTrees(3), 5);
	}
	
	/*        3
	 *       / \
	 *      1   4
	 *     / \
	 *    0   2
	 */
	public static BinarySearchTree createSampleBst() {
		BinarySearchTree bst = new BinarySearchTree(3);
		bst.insert(1);
		bst.insert(4);
		bst.insert(2);
		bst.insert(0);
		
		return bst;
	}
}
