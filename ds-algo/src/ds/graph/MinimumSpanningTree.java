package ds.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import ds.exp.IncompatibleGraphTypeException;
import ds.exp.NoSuchNodeException;
import ds.exp.NodeCreationException;
import ds.exp.NullNodeException;

/**
 * A spanning tree is a sub-graph of an undirected and a connected graph,
 * which includes all the vertices of the graph having a minimum possible number of edges.
 * If a vertex is missed, then it is not a spanning tree.
 * The edges may or may not have weights assigned to them.
 * 
 * A minimum spanning tree is a spanning tree in which the sum of the weight of the edges is as minimum as possible.
 * The minimum spanning tree from a graph is found using: Prim's Algorithm and Kruskal's Algorithm
 * 
 */
public class MinimumSpanningTree {
	private Set<GraphNode> nodeKeySet;
	private boolean isCyclic;
	private boolean isDirected;
	private boolean isWeighted;
	private Map<GraphNode, List<GraphEdge>> adjacencyMapForWeightedNode;
	private int mstCost = 0;
	
	// creates the weighted graph if isWeighted is true
	public MinimumSpanningTree(boolean isDirected, boolean isWeighted) {
		super();
		this.isDirected = isDirected;
		this.nodeKeySet = new LinkedHashSet<>();
		this.isCyclic = false;
		this.isWeighted = isWeighted;
		this.adjacencyMapForWeightedNode = new HashMap<>();
	}
	
	public GraphNode addNode(int index, String label) throws NodeCreationException {
		GraphNode node = this._createNode(index, label);
		if (node == null) {
			throw new NodeCreationException("unable to create a graph node");
		} else {
			this._updateNodeKeySet(node);
			return node;
		}
	}

	public GraphNode addNode(String label) throws NodeCreationException {
		GraphNode node = this._createNode(label);
		if (node == null) {
			throw new NodeCreationException("unable to create a graph node");
		} else {
			this._updateNodeKeySet(node);
			return node;
		}
	}
	
	private GraphNode _createNode(int index, String label) {
		return new GraphNode(index, label);
	}

	private GraphNode _createNode(String label) {
		return new GraphNode(label);
	}

	private void _updateNodeKeySet(GraphNode node) {
		this.nodeKeySet.add(node);
	}
	
	public void connectNodes(GraphNode source, GraphNode target, Integer weight) throws NoSuchNodeException {
		if (this._isGraphWeighted()) {
			if (source != null && target != null && weight != null) {
				if (source == target) {
					// then this graph is cyclic
					this.isCyclic = true;
				}
				
				if (this._isPresentInGraph(source) && this._isPresentInGraph(target)) {
					GraphEdge edge = this._getEdgeWithWeigth(source, target, weight);
					if (edge != null) {
						this._createEdgeWithWeigth(edge);
					}
				} else {
					if (!this._isPresentInGraph(source)) {
						throw new NoSuchNodeException(source + " is not found in the graph instance.");
					} else {
						throw new NoSuchNodeException(target + " is not found in the graph instance.");
					}
				}
	
				if (this.isTheGraphUndirected()) {
					GraphEdge complementary_edge = this._getEdgeWithWeigth(target, source, weight);
					this._createEdgeWithWeigth(complementary_edge);
				}
			} else {
				throw new NullNodeException("node cannot be connected");
			}
		} else {
			throw new IncompatibleGraphTypeException("Unweighted graph cannot have weights.");
		}
	}
	
	private GraphEdge _getEdgeWithWeigth(GraphNode source, GraphNode target, Integer weight) {
		return new GraphEdge(source, target, weight);
	}
	
	private void _createEdgeWithWeigth(GraphEdge edge) {
		List<GraphEdge> tmpEdgeList = this.adjacencyMapForWeightedNode.get(edge.getSource());

		if (tmpEdgeList != null) {
			tmpEdgeList.remove(edge);
		} else {
			tmpEdgeList = new LinkedList<>();
		}

		tmpEdgeList.add(edge);
		this.adjacencyMapForWeightedNode.put(edge.getSource(), tmpEdgeList);
	}
	
	private boolean _isPresentInGraph(GraphNode node) {
		return this.nodeKeySet.contains(node);
	}
	
	public boolean isTheGraphDirected() {
		return this.isDirected;
	}

	public boolean isTheGraphUndirected() {
		return !this.isDirected;
	}
	
	public void displayAdjacencyListForWeightedNode() {
		System.out.println("Adjacency List for Weighted Node " + this);
		this._displayAdjacencyListForWeightedNode();
		System.out.println();
	}
	
	private void _displayAdjacencyListForWeightedNode() {
		Iterator<GraphNode> itr = this._getNodeKeySet().iterator();
		while (itr.hasNext()) {
			GraphNode node = itr.next();
			List<GraphEdge> tmp = this.adjacencyMapForWeightedNode.get(node);

			if (tmp != null) {
				System.out.print("The Node " + node.getLabel() + " has a edges towards: [ ");
				for (GraphEdge n : tmp) {
					System.out.print(n.getTarget().getLabel() + "(" + n.getWeight() + ")" + " ");
				}
				System.out.println("]");
			} else {
				System.out.println("The Node " + node.getLabel() + " has a edges towards: [ ]");
			}
		}
	}
	
	private Set<GraphNode> _getNodeKeySet() {
		return this.nodeKeySet;
	}
	
	private List<GraphEdge> _getAssociatedWeightedNodeList(GraphNode currentNode) {
		return this.adjacencyMapForWeightedNode.get(currentNode);
	}

	public boolean isGraphCyclic() {
		return this.isCyclic;
	}

	public boolean isGraphAcyclic() {
		return !this.isCyclic;
	}
		
	private boolean _isGraphWeighted() {
		return this.isWeighted;
	}

	public int getGraphSize() {
		return this._getNodeKeySet().size();
	}
	
	private GraphNode _getFirstKeyFromSet() {
		if (this._getNodeKeySet().isEmpty()) {
			return null;
		} else {
			Stream<GraphNode> stream = this._getNodeKeySet().parallelStream();
			Optional<GraphNode> optionalGraphNode = stream.findFirst();
			if (optionalGraphNode.isPresent()) {
				GraphNode node = optionalGraphNode.get();
				stream.close();
				return node;
			} else {
				stream.close();
				return null;
			}
		}
	}
	
}
