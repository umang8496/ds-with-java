package ds.tree.avl;

public class AvlNode {
	private Integer data;
	private AvlNode parent;
	private AvlNode leftNode;
	private AvlNode rightNode;
	private int height;

	private AvlNode() {
		super();
	}

	public AvlNode(int data) {
		this();
		this.parent = null;
		this.leftNode = null;
		this.rightNode = null;
		this.data = data;
		this.height = 0;
	}

	public Integer getData() {
		return data;
	}

	public void setData(Integer data) {
		this.data = data;
	}

	public AvlNode getParent() {
		return parent;
	}

	public void setParent(AvlNode parent) {
		this.parent = parent;
	}

	public AvlNode getLeftNode() {
		return leftNode;
	}

	public void setLeftNode(AvlNode leftNode) {
		this.leftNode = leftNode;
	}

	public AvlNode getRightNode() {
		return rightNode;
	}

	public void setRightNode(AvlNode rightNode) {
		this.rightNode = rightNode;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

}
