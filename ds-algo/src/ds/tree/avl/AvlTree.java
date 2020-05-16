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

}
