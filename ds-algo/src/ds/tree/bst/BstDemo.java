package ds.tree.bst;

public class BstDemo {
	public static void main(String[] args) {
		BinarySearchTree bst = new BinarySearchTree(100);
		// BinarySearchTree bst = new BinarySearchTree();	// throws Exception 
		
		bst.addNode(200);
		bst.addNode(50);
		bst.addNode(75);
		bst.addNode(300);
		bst.addNode(150);
		bst.addNode(25);
		
		bst.displayInOrder();		// InOrder : [ 25 50 75 100 150 200 300 ]
		bst.displayPreOrder();		// PreOrder : [ 100 50 25 75 200 150 300 ]
		bst.displayPostOrder();		// PostOrder : [ 25 75 50 150 300 200 100 ]
		bst.displayLevelOrder();	// LevelOrder : [ 100 50 200 25 75 150 300 ]
		
		System.out.println("Largest: " + bst.getLargest());
		System.out.println("Smallest: " + bst.getSmallest());
		
		System.out.println("Number of Leaf Nodes: " + bst.getNumberOfLeafNodes());
		System.out.println("Is 150 present in the tree: " + bst.findNode(150));
		System.out.println("Is 250 present in the tree: " + bst.findNode(250));
		
		System.out.println("Height of the tree: " + bst.getHeightOfTheTree());
		
		bst.deleteTreeNode(25);
		bst.displayInOrder();		// InOrder : [ 50 75 100 150 200 300 ]
		bst.deleteTreeNode(125);	// value not available
		bst.displayInOrder();		// InOrder : [ 50 75 100 150 200 300 ]		
	}
}

