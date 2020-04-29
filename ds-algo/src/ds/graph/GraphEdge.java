package ds.graph;

public class GraphEdge {

	private GraphNode source;
	private GraphNode target;
	private Integer weight;

	private GraphEdge() {
		super();
	}
	
	public GraphEdge(GraphNode source, GraphNode target, Integer weight) {
		this();
		this.source = source;
		this.target = target;
		this.weight = weight;
	}
	
	public GraphNode getSource() {
		return source;
	}

	public void setSource(GraphNode source) {
		this.source = source;
	}

	public GraphNode getTarget() {
		return target;
	}

	public void setTarget(GraphNode target) {
		this.target = target;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public boolean hasEdgeBetween(GraphNode source, GraphNode target) {
		if (source != null && target != null) {
			return this._hasEdgeBetween(source, target);
		} else {
			return false;
		}
	}

	private boolean _hasEdgeBetween(GraphNode source, GraphNode target) {
		if (this.getSource() == source && this.getTarget() == target) {
			return true;
		} else {
			return false;
		}
	}
}
