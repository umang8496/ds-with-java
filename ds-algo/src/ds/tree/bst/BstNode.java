package ds.tree.bst;

public class BstNode {
	private int data;
	private BstNode leftNode;
	private BstNode rightNode;

	public BstNode() {
		super();
		this.leftNode = null;
		this.rightNode = null;
	}

	public BstNode(int data) {
		this();
		this.data = data;
		this.leftNode = null;
		this.rightNode = null;
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	public BstNode getLeftNode() {
		return leftNode;
	}

	public void setLeftNode(BstNode leftNode) {
		this.leftNode = leftNode;
	}

	public BstNode getRightNode() {
		return rightNode;
	}

	public void setRightNode(BstNode rightNode) {
		this.rightNode = rightNode;
	}
}
