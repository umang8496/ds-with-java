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

	
	
	/**
	 * Prim's algorithm is an MST algorithm that takes a graph as input and finds the subset of the edges of that graph which
	 * form a tree that includes every vertex
	 * has the minimum sum of weights among all the trees that can be formed from the graph
	 * 
	 * It falls under greedy algorithms which finds the local optimum in the hopes of finding a global optimum subsequently.
	 * We start from one vertex and keep adding edges with the lowest weight until we we reach our goal.
	 * 
	 * The steps for implementing Prim's algorithm are as follows:
	 * --> Initialize the minimum spanning tree with a vertex chosen at random.
	 * --> Find all the edges that connect the tree to new vertices, find the minimum and add it to the tree
	 * --> Keep repeating step 2 until we get a minimum spanning tree
	 * 
	 **/
	@SuppressWarnings("unchecked")
	public MinimumSpanningTree getPrimsMinimumSpanningTree() {
		MinimumSpanningTree prims = new MinimumSpanningTree(false, true);
		
		Set<GraphNode> nodeSet = new HashSet<GraphNode>();
		Set<GraphEdge> edgeSet = new HashSet<GraphEdge>();
		int nodeCount = 0;
		
		// select a random node from the given graph
		GraphNode firstVertex = this._getFirstKeyFromSet();

		// put that node into the list of visited nodes
		nodeSet.add(firstVertex);
		firstVertex.visit();
		nodeCount++;
		
		// select the minimum weighted edge from that node and put it into the list of edges
		while (nodeCount != this.getGraphSize()) {
			Object[] ob = _selectMinWeightEdgeFromNodeSet(nodeSet, edgeSet);
			nodeSet = (Set<GraphNode>) ob[0];
			edgeSet = (Set<GraphEdge>) ob[1];
			nodeCount = nodeSet.size();
		}
		
		return this._createGraphFromEdges(prims, edgeSet);
	}

	private Object[] _selectMinWeightEdgeFromNodeSet(Set<GraphNode> nodeSet, Set<GraphEdge> edgeSet) {
		int minWeight = Integer.MAX_VALUE;
		GraphEdge edge = null;

		for (GraphNode node : nodeSet) {
			GraphEdge edgeWithMinWeight = this._getTheNodeWithMinWeight(node);
			if (edgeWithMinWeight == null) {
				continue;
			} else {
				if (edgeWithMinWeight.getWeight() < minWeight) {
					minWeight = edgeWithMinWeight.getWeight();
					edge = edgeWithMinWeight;
				}
			}
		}		
		
		if(edge != null) {
			GraphNode nextNode = edge.getTarget();
			if (!nodeSet.contains(nextNode)) {
				nodeSet.add(nextNode);
				nextNode.visit();
				edgeSet.add(edge);
			}
		}
		return new Object[] { nodeSet, edgeSet };
	}

	private GraphEdge _getTheNodeWithMinWeight(GraphNode node) {
		int minWeight = Integer.MAX_VALUE;
		GraphEdge ret_edge = null;
		List<GraphEdge> edgeList = this._getAssociatedWeightedNodeList(node);
		for (GraphEdge edge : edgeList) {
			if(edge.getTarget().isVisited()) {
				continue;
			} else {
				if (edge.getWeight() < minWeight) {
					minWeight = edge.getWeight();
					ret_edge = edge;
				}
			}
		}
		return ret_edge;
	}

	private MinimumSpanningTree _createGraphFromEdges(MinimumSpanningTree prims, Set<GraphEdge> edgeSet) {
		for (GraphEdge edge : edgeSet) {
			prims.mstCost += edge.getWeight(); 
			prims = this._connectWeightedNodes(prims, edge);
		}
		return prims;
	}
	
	private MinimumSpanningTree _connectWeightedNodes(MinimumSpanningTree prims, GraphEdge edge) {
		if (prims != null && edge != null) {
			prims.nodeKeySet.add(edge.getSource());
			List<GraphEdge> edgeList = null;
			List<GraphEdge> reverseEdgeList = null;
			
			if(prims.adjacencyMapForWeightedNode.containsKey(edge.getSource())) {
				edgeList = prims.adjacencyMapForWeightedNode.get(edge.getSource());				
			} else {
				edgeList = new ArrayList<GraphEdge>();
			}
			edgeList.add(edge);
			prims.adjacencyMapForWeightedNode.put(edge.getSource(), edgeList);
			
			if(prims.adjacencyMapForWeightedNode.containsKey(edge.getTarget())) {
				reverseEdgeList = prims.adjacencyMapForWeightedNode.get(edge.getTarget());				
			} else {
				reverseEdgeList = new ArrayList<GraphEdge>();
			}
			
			GraphEdge reverse_edge = this._getEdgeWithWeigth(edge.getTarget(), edge.getSource(), edge.getWeight());
			reverseEdgeList.add(reverse_edge);
			prims.adjacencyMapForWeightedNode.put(edge.getTarget(), reverseEdgeList);			
		}
		return prims;
	}
	
	public int getMstCost() {
		return this.mstCost;
	}

}
