package ds.graph;

import ds.exp.NullNodeException;

public class GraphNode {
	private String label;
	private int index;
	private boolean visited;
	private GraphNode parent;

	private GraphNode() {
		super();
	}

	public GraphNode(String label) {
		this();
		if (label == null) {
			throw new NullNodeException("node label cannot be null");
		} else {
			this.index = -1;
			this.label = label;
			this.visited = false;
		}
	}
	
	public GraphNode(int index, String label) {
		this();
		if (label == null) {
			throw new NullNodeException("node label cannot be null");
		} else {
			this.index = index;
			this.label = label;
			this.visited = false;
		}
	}

	public String getLabel() {
		return this.label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public int getIndex() {
		return this.index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public void visit() {
		this.visited = true;
	}

	public void unvisit() {
		this.visited = false;
	}

	public boolean isVisited() {
		return this.visited;
	}

	public boolean unVisited() {
		return !this.visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public GraphNode getParentNode() {
		return this.parent;
	}

	public void setParentNode(GraphNode node) {
		this.parent = node;
	}
	
	// for creating independent nodes and later connecting them across different graphs
	public static GraphNode createIndependentNodes(int index, String label) {
		return new GraphNode(index, label);
	}
}
