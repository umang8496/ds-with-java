package ds.tree.bst;

import java.util.ArrayDeque;
import java.util.Queue;

import ds.exp.NoRootAvailableException;
import ds.exp.NodeCreationException;
import ds.exp.ObjectCreationException;

public class BinarySearchTree {
	private BstNode rootNode;

	public BinarySearchTree() {
		if (this.rootNode == null) {
			throw new ObjectCreationException("unable to create root element");
		}
	}

	public BinarySearchTree(int data) {
		super();
		this.rootNode = this._createNode(data);
		if (this.rootNode == null) {
			throw new ObjectCreationException("unable to create root element");
		}
	}

	public BstNode getRootNode() {
		return this.rootNode;
	}

	private boolean _hasLeftNode(BstNode node) {
		return node.getLeftNode() != null;
	}

	private boolean _hasRightNode(BstNode node) {
		return node.getRightNode() != null;
	}

	public void addNode(int data) {
		if (this.rootNode != null) {
			this._addNodeIntoBst(data, this.getRootNode());
		}
	}

	private BstNode _createNode(int data) {
		try {
			BstNode node = new BstNode(data);
			return node;
		} catch (Exception exp) {
			throw new NodeCreationException("unable to create node", exp);
		}
	}

	private void _addNodeIntoBst(int data, BstNode currentNode) {
		if (this.getRootNode() == null) {
			throw new NoRootAvailableException("root node is not available");
		} else {
		
			if (data >= currentNode.getData()) {
				if (this._hasRightNode(currentNode)) {
					this._addNodeIntoBst(data, currentNode.getRightNode());
				} else {
					currentNode.setRightNode(this._createNode(data));
				}
			} else {
				if (this._hasLeftNode(currentNode)) {
					this._addNodeIntoBst(data, currentNode.getLeftNode());
				} else {
					currentNode.setLeftNode(this._createNode(data));
				}
			}
		}
	}

	public void displayInOrder() {
		if (this.getRootNode() == null) {
			throw new NoRootAvailableException("root node is not available");
		} else {
			System.out.print("InOrder : [ ");
			this._inOrderTraversal(this.getRootNode());
			System.out.println("]");
		}
	}

	private void _inOrderTraversal(BstNode node) {
		if (this._hasLeftNode(node)) {
			this._inOrderTraversal(node.getLeftNode());
		}
		System.out.print(node.getData() + " ");
		if (this._hasRightNode(node)) {
			this._inOrderTraversal(node.getRightNode());
		}
	}

	public void displayPreOrder() {
		if (this.getRootNode() == null) {
			throw new NoRootAvailableException("root node is not available");
		} else {
			System.out.print("PreOrder : [ ");
			this._preOrderTraversal(this.getRootNode());
			System.out.println("]");
		}
	}

	private void _preOrderTraversal(BstNode node) {
		System.out.print(node.getData() + " ");
		if (this._hasLeftNode(node)) {
			this._preOrderTraversal(node.getLeftNode());
		}
		if (this._hasRightNode(node)) {
			this._preOrderTraversal(node.getRightNode());
		}
	}

	public void displayPostOrder() {
		if (this.getRootNode() == null) {
			throw new NoRootAvailableException("root node is not available");
		} else {
			System.out.print("PostOrder : [ ");
			this._postOrderTraversal(this.getRootNode());
			System.out.println("]");
		}
	}

	private void _postOrderTraversal(BstNode node) {
		if (this._hasLeftNode(node)) {
			this._postOrderTraversal(node.getLeftNode());
		}
		if (this._hasRightNode(node)) {
			this._postOrderTraversal(node.getRightNode());
		}
		System.out.print(node.getData() + " ");
	}

	public void displayLevelOrder() {
		if (this.getRootNode() == null) {
			throw new NoRootAvailableException("root node is not available");
		} else {
			System.out.print("LevelOrder : [ ");
			this._levelOrderTraversal(this.getRootNode());
			System.out.println("]");
		}
	}

	private void _levelOrderTraversal(BstNode node) {
		Queue<BstNode> queue = new ArrayDeque<>();
		queue.add(this.getRootNode());
		BstNode current = null;
		while (!queue.isEmpty()) {
			current = queue.poll();
			System.out.print(current.getData() + " ");
			if (this._hasLeftNode(current)) {
				queue.add(current.getLeftNode());
			}
			if (this._hasRightNode(current)) {
				queue.add(current.getRightNode());
			}
		}
	}

	public boolean isLeafNode(BstNode node) {
		if (this.getRootNode() == null) {
			throw new NoRootAvailableException("root node is not available");
		} else {
			return this._isLeafNode(node);
		}
	}

	private boolean _isLeafNode(BstNode node) {
		return ((node.getRightNode() == null) && (node.getLeftNode() == null));
		// OR
		// return ((!this._hasLeftNode(node)) && !(this._hasRightNode(node)));
	}

	public Integer getLargest() {
		if (this.getRootNode() == null) {
			throw new NoRootAvailableException("root node is not available");
		} else {
			return this._getLargest(this.getRootNode());
		}
	}

	private Integer _getLargest(BstNode node) {
		if (node.getRightNode() == null) {
			return node.getData();
		}
		return this._getLargest(node.getRightNode());
	}

	public Integer getSmallest() {
		if (this.getRootNode() == null) {
			throw new NoRootAvailableException("root node is not available");
		} else {
			return this._getSmallest(this.getRootNode());
		}
	}

	private Integer _getSmallest(BstNode node) {
		if (node.getLeftNode() == null) {
			return node.getData();
		}
		return this._getSmallest(node.getLeftNode());
	}

	public int getNumberOfLeafNodes() {
		if (this.getRootNode() == null) {
			throw new NoRootAvailableException("root node is not available");
		} else {
			return this._getNumberOfLeafNodes(this.getRootNode());
		}
	}

	private int _getNumberOfLeafNodes(BstNode node) {
		if (this._isLeafNode(node)) {
			return 1;
		}

		int leftLeaves = 0;
		int rightLeaves = 0;

		if (this._hasLeftNode(node))
			leftLeaves = this._getNumberOfLeafNodes(node.getLeftNode());

		if (this._hasRightNode(node))
			rightLeaves = this._getNumberOfLeafNodes(node.getRightNode());

		return leftLeaves + rightLeaves;
	}

	public boolean findNode(int value) {
		if (this.getRootNode() == null) {
			throw new NoRootAvailableException("root node is not available");
		} else {
			boolean found = this._findNodeInTree(value, this.getRootNode());
			return (found ? true : false);
		}
	}

	private boolean _findNodeInTree(int value, BstNode node) {
		if (node.getData() == value) {
			return true;
		}
		
		if (node.getData() != value && this.isLeafNode(node)) {
			return false;
		}

		if (node.getData() < value && this._hasRightNode(node)) {
			return this._findNodeInTree(value, node.getRightNode());
		}

		if (node.getData() > value && this._hasLeftNode(node)) {
			return this._findNodeInTree(value, node.getLeftNode());
		}

		return false;
	}

	public int getHeightOfTheTree() {
		// The height of a binary tree is the number of edges between the tree's root and its furthest leaf.
		// Height of Tree with only root node is 0.
		// Depth of the tree is calculated from the root node; Whereas, height of the tree is calculated from the leafnode.
		if (this.getRootNode() == null) {
			return -1; // no root node
		} else {
			return this._getHeight(this.getRootNode());
		}
	}
	
	private int _getHeight(BstNode node) {
		if (this._isLeafNode(node)) {
			return 0;
		} else {
			int leftSubTreeHeight = 0;
			int rightSubTreeHeight = 0;

			if (this._hasLeftNode(node)) {
				leftSubTreeHeight = this._getHeight(node.getLeftNode());
			}
			if (this._hasRightNode(node)) {
				rightSubTreeHeight = this._getHeight(node.getRightNode());
			}

			return (rightSubTreeHeight >= leftSubTreeHeight) ? (rightSubTreeHeight + 1) : (leftSubTreeHeight + 1);
		}
	}
	
	public void deleteTreeNode(int value) {
		if (this.getRootNode() == null) {
			throw new NoRootAvailableException("root node is not available");
		} else {
			this.rootNode = (this._deleteTreeNode(this.getRootNode(), value));
		}
	}

	private BstNode getInOrderSuccessorOfRightSubTree(BstNode current) {
		while (this._hasLeftNode(current)) {
			current = current.getLeftNode();
		}
		return current;
	}

	@SuppressWarnings("unused")
	private BstNode getInOrderPredeccesorOfLeftSubTree(BstNode current) {
		while (this._hasRightNode(current)) {
			current = current.getRightNode();
		}
		return current;
	}

	private BstNode _deleteTreeNode(BstNode root, int value) {
		// pointer to store parent node of current node
		BstNode parent = null;
		// start with root node
		BstNode current = root;

		// search key in BST and set its parent pointer
		while (current != null && current.getData() != value) {
			// update parent node as current node
			parent = current;

			// if given key is less than the current node, go to left subtree
			// else go to right subtree
			if (value < current.getData()) {
				current = current.getLeftNode();
			} else {
				current = current.getRightNode();
			}
		}

		// return if key is not found in the tree, then return null
		if (current == null) {
			return root;
		}

		if (this.isLeafNode(current)) { // Case 1: node to be deleted has no children (leaf node)
			// if node to be deleted is not a root node, then set its parent left/right child to null
			if (current != root) {
				if (parent.getLeftNode() == current) {
					parent.setLeftNode(null);
				} else {
					parent.setRightNode(null);
				}
			} else { // if tree has only root node, delete it and set root to null
				root = null;
			}
		} else if (this._hasLeftNode(current) && this._hasRightNode(current)) { // Case 2: node to be deleted has two children
			// find its in-order successor node
			BstNode successor = this.getInOrderSuccessorOfRightSubTree(current.getRightNode());
			// store successor value
			int val = successor.getData();

			// recursively delete the successor. Note that the successor will have at-most one child (right child)
			this._deleteTreeNode(root, successor.getData());
			// Copy the value of successor to current node
			current.setData(val);
		} else { // Case 3: node to be deleted has only one child
			// find child node
			BstNode child = (current.getLeftNode() != null) ? (current.getLeftNode()) : (current.getRightNode());

			// if node to be deleted is not a root node, then set its parent to its child
			if (current != root) {
				if (current == parent.getLeftNode()) {
					parent.setLeftNode(child);
				} else {
					parent.setRightNode(child);
				}
			} else { // if node to be deleted is root node, then set the root to child
				root = child;
			}
		}
		return root;
	}
    
}
