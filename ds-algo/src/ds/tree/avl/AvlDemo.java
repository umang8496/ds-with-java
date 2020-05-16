package ds.tree.avl;

public class AvlDemo {
	public static void main(String[] args) {
		AvlTree avl = new AvlTree();
		avl.insertNode(20);
		avl.insertNode(10);
		avl.insertNode(30);
		avl.displayInOrder();
		avl.displayPreOrder();
		avl.displayPostOrder();
	}
}
