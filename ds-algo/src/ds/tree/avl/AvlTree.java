package ds.tree.avl;

import java.util.ArrayDeque;
import java.util.Queue;

import ds.exp.NoRootAvailableException;
import ds.exp.NodeCreationException;

public class AvlTree {
	private AvlNode rootNode;

	public AvlTree() {
		super();
		this.rootNode = null;
	}

	private AvlNode _getRootNode() {
		return this.rootNode;
	}

	private boolean _hasLeftNode(AvlNode node) {
		return node.getLeftNode() != null;
	}

	private boolean _hasRightNode(AvlNode node) {
		return node.getRightNode() != null;
	}

	private AvlNode _createNode(int data) {
		try {
			AvlNode node = new AvlNode(data);
			return node;
		} catch (Exception exp) {
			throw new NodeCreationException("unable to create node", exp);
		}
	}

	public void insertNode(int data) {
		if (this.rootNode == null) {
			this.rootNode = this._createNode(data);
		} else {
			this.rootNode = this._insertNode(data, this.rootNode);
		}
	}

	private AvlNode _insertNode(int data, AvlNode currentNode) {
		if (this._getRootNode() == null) {
			throw new NoRootAvailableException("root node is not available");
		} else {
			if (data >= currentNode.getData()) {
				if (this._hasRightNode(currentNode)) {
					this._insertNode(data, currentNode.getRightNode());
				} else {
					currentNode.setRightNode(this._createNode(data));
					// currentNode.rightNode = this._createNode(data);
					currentNode.getRightNode().setParent(currentNode);
					// currentNode.rightNode.parent = currentNode;
				}
			} else {
				if (this._hasLeftNode(currentNode)) {
					this._insertNode(data, currentNode.getLeftNode());
				} else {
					currentNode.setLeftNode(this._createNode(data));
					// currentNode.leftNode = this._createNode(data);
					currentNode.getLeftNode().setParent(currentNode);
					// currentNode.leftNode.parent = currentNode;
				}
			}
		}

		return this._rebalance(currentNode);
	}

	private AvlNode _rebalance(AvlNode z) {
		this._updateHeight(z);
		int balance = this._getBalance(z);
		if (balance > 1) {
			if (this._computeHeight(z.getRightNode().getRightNode()) > this
					._computeHeight(z.getRightNode().getLeftNode())) {
				z = this._rotateLeft(z);
			} else {
				z.setRightNode(this._rotateRight(z.getRightNode()));
				z = this._rotateLeft(z);
			}
		} else if (balance < -1) {
			if (this._computeHeight(z.getLeftNode().getLeftNode()) > this
					._computeHeight(z.getLeftNode().getRightNode()))
				z = this._rotateRight(z);
			else {
				z.setLeftNode(this._rotateLeft(z.getLeftNode()));
				z = this._rotateRight(z);
			}
		}
		return z;
	}
	
	private void _updateHeight(AvlNode node) {
		node.setHeight(1 + Math.max(this._computeHeight(node.getLeftNode()), this._computeHeight(node.getRightNode())));
	}

	private int _getBalance(AvlNode node) {
		return (node == null) ? 0 : this._computeHeight(node.getRightNode()) - this._computeHeight(node.getLeftNode());
	}

	private int _computeHeight(AvlNode node) {
		return node == null ? -1 : node.getHeight();
	}
	
	/**
	 * Assume we have a BST called T1, with Y as the root node, X as the left  child of Y, and Z as the right child of X.
	 * Given the characteristics of a BST, we know that X < Z < Y.
	 * After a right rotation of Y, we have a tree called T2 with X as the root and Y as the right child of X and Z as the left child of Y.
	 * T2 is still a BST because it keeps the order X < Z < Y.
	 * 
	 * @param y
	 * @return
	 */
	private AvlNode _rotateRight(AvlNode y) {
		AvlNode x = y.getLeftNode();
		AvlNode z = x.getRightNode();
		x.setRightNode(y);
		y.setLeftNode(z);
		this._updateHeight(y);
		this._updateHeight(x);
		return x;
	}

	/**
	 * Assume a BST called T1, with Y as the root node, X as the right child of Y, and Z as the left child of X.
	 * Given this, we know that Y < Z < X.
	 * After a left rotation of Y, we have a tree called T2 with X as the root and Y as the left child of X and Z as the right child of Y.
	 * T2 is still a BST because it keeps the order Y < Z < X.
	 * 
	 * @param y
	 * @return
	 */
	private AvlNode _rotateLeft(AvlNode y) {
		AvlNode x = y.getRightNode();
		AvlNode z = x.getLeftNode();
		x.setLeftNode(y);
		y.setRightNode(z);
		this._updateHeight(y);
		this._updateHeight(x);
		return x;
	}

	public void displayInOrder() {
		if (this._getRootNode() == null) {
			throw new NoRootAvailableException("root node is not available");
		} else {
			System.out.print("InOrder : [ ");
			this._inOrderTraversal(this._getRootNode());
			System.out.println("]");
		}
	}

	private void _inOrderTraversal(AvlNode node) {
		if (this._hasLeftNode(node)) {
			this._inOrderTraversal(node.getLeftNode());
		}
		System.out.print(node.getData() + " ");
		if (this._hasRightNode(node)) {
			this._inOrderTraversal(node.getRightNode());
		}
	}

	public void displayPreOrder() {
		if (this._getRootNode() == null) {
			throw new NoRootAvailableException("root node is not available");
		} else {
			System.out.print("PreOrder : [ ");
			this._preOrderTraversal(this._getRootNode());
			System.out.println("]");
		}
	}

	private void _preOrderTraversal(AvlNode node) {
		System.out.print(node.getData() + " ");
		if (this._hasLeftNode(node)) {
			this._preOrderTraversal(node.getLeftNode());
		}
		if (this._hasRightNode(node)) {
			this._preOrderTraversal(node.getRightNode());
		}
	}

	public void displayPostOrder() {
		if (this._getRootNode() == null) {
			throw new NoRootAvailableException("root node is not available");
		} else {
			System.out.print("PostOrder : [ ");
			this._postOrderTraversal(this._getRootNode());
			System.out.println("]");
		}
	}

	private void _postOrderTraversal(AvlNode node) {
		if (this._hasLeftNode(node)) {
			this._postOrderTraversal(node.getLeftNode());
		}
		if (this._hasRightNode(node)) {
			this._postOrderTraversal(node.getRightNode());
		}
		System.out.print(node.getData() + " ");
	}

	public void displayLevelOrder() {
		if (this._getRootNode() == null) {
			throw new NoRootAvailableException("root node is not available");
		} else {
			System.out.print("LevelOrder : [ ");
			this._levelOrderTraversal(this._getRootNode());
			System.out.println("]");
		}
	}

	private void _levelOrderTraversal(AvlNode node) {
		Queue<AvlNode> queue = new ArrayDeque<>();
		queue.add(this._getRootNode());
		AvlNode current = null;
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
	
	public int getNumberOfLeafNodes() {
		if (this._getRootNode() == null) {
			throw new NoRootAvailableException("root node is not available");
		} else {
			return this._getNumberOfLeafNodes(this._getRootNode());
		}
	}

	private int _getNumberOfLeafNodes(AvlNode node) {
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
	
	public boolean isLeafNode(AvlNode node) {
		if (this._getRootNode() == null) {
			throw new NoRootAvailableException("root node is not available");
		} else {
			return this._isLeafNode(node);
		}
	}

	private boolean _isLeafNode(AvlNode node) {
		return ((node.getRightNode() == null) && (node.getLeftNode() == null));
	}
	
	public Integer getLargest() {
		if (this._getRootNode() == null) {
			throw new NoRootAvailableException("root node is not available");
		} else {
			return this._getLargest(this._getRootNode());
		}
	}

	private Integer _getLargest(AvlNode node) {
		if (node.getRightNode() == null) {
			return node.getData();
		}
		return this._getLargest(node.getRightNode());
	}

	public Integer getSmallest() {
		if (this._getRootNode() == null) {
			throw new NoRootAvailableException("root node is not available");
		} else {
			return this._getSmallest(this._getRootNode());
		}
	}

	private Integer _getSmallest(AvlNode node) {
		if (node.getLeftNode() == null) {
			return node.getData();
		}
		return this._getSmallest(node.getLeftNode());
	}


	public int getHeightOfTheTree() {
		// The height of a binary tree is the number of edges between the tree's root and its furthest leaf.
		// Height of Tree with only root node is 0.
		// Depth of the tree is calculated from the root node; Whereas, height of the tree is calculated from the leafnode.
		if (this._getRootNode() == null) {
			return -1; // no root node
		} else {
			return this._getHeight(this._getRootNode());
		}
	}
	
	private int _getHeight(AvlNode node) {
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
	
}
