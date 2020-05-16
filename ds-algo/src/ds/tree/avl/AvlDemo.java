package ds.tree.avl;

public class AvlDemo {
	public static void main(String[] args) {
		AvlTree avl = new AvlTree();
		avl.insertNode(10);
		avl.insertNode(20);
		avl.insertNode(30);
		
		avl.displayInOrder();		// InOrder : [ 10 20 30 ]
		avl.displayPreOrder();		// PreOrder : [ 20 10 30 ]
		avl.displayPostOrder();		// PostOrder : [ 10 30 20 ]
		avl.displayLevelOrder();
		
		System.out.println();
		System.out.println();
		
		avl.insertNode(40);
		avl.insertNode(50);
		
		avl.displayInOrder();		// InOrder : [ 10 20 30 40 50 ]
		avl.displayPreOrder();		// PreOrder : [ 20 10 40 30 50 ]
		avl.displayPostOrder();		// PostOrder : [ 10 30 50 40 20 ]
		avl.displayLevelOrder();
		
//		avl.insertNode(60);
//		avl.insertNode(70);
//		
//		avl.displayInOrder();		// InOrder : [ 10 20 30 40 50 ]
//		avl.displayPreOrder();		// PreOrder : [ 20 10 40 30 50 ]
//		avl.displayPostOrder();		// PostOrder : [ 10 30 50 40 20 ]
//		avl.displayLevelOrder();
	}
	
}
