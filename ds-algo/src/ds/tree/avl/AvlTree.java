package ds.tree.avl;

import java.util.ArrayList;

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
			this.rootNode = new AvlNode(data);
		} else {
			this._insertNode(data, this.rootNode);
		}
	}

	private void _insertNode(int data, AvlNode currentNode) {
		if (this._getRootNode() == null) {
			throw new NoRootAvailableException("root node is not available");
		} else {
			if (data >= currentNode.getData()) {
				if (this._hasRightNode(currentNode)) {
					this._insertNode(data, currentNode.getRightNode());
				} else {
					currentNode.setRightNode(this._createNode(data));
					currentNode.getRightNode().setParent(currentNode);
				}
			} else {
				if (this._hasLeftNode(currentNode)) {
					this._insertNode(data, currentNode.getLeftNode());
				} else {
					currentNode.setLeftNode(this._createNode(data));
					currentNode.getLeftNode().setParent(currentNode);
				}
			}
		}
	}

	public int getHeight() {
		if (this._getRootNode() != null) {
			return this._getHeight(this._getRootNode(), 0);
		} else {
			return 0;
		}
	}

	private int _getHeight(AvlNode currentNode, int currentHeight) {
		if (currentNode == null) {
			return currentHeight;
		}

		int leftHeight = this._getHeight(currentNode.getLeftNode(), currentHeight + 1);
		int rightHeight = this._getHeight(currentNode.getRightNode(), currentHeight + 1);

		return Math.max(leftHeight, rightHeight);
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
	
}
