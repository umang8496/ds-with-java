package ds.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Stream;

import ds.exp.CyclicGraphException;
import ds.exp.GraphNotFoundException;
import ds.exp.IncompatibleGraphTypeException;
import ds.exp.NoSuchNodeException;
import ds.exp.NodeCreationException;
import ds.exp.NullNodeException;

public class Graph {
	private Set<GraphNode> nodeKeySet = null;
	private boolean isCyclic;
	private boolean isDirected;
	private boolean isWeighted;
	private Map<GraphNode, List<GraphEdge>> adjacencyMapForGraph = null;

	/**
	 * Creates an undirected Graph object.
	 */
	public Graph() {
		this.isDirected = false;
		this.nodeKeySet = new LinkedHashSet<>();
		this.isCyclic = false;
		this.isWeighted = false;
	}

	/**
	 * Creates a Graph object with specified type of graph.
	 * 
	 * @param isDirected,
	 *            if true then graph is directed otherwise undirected
	 */
	public Graph(boolean isDirected) {
		super();
		this.isDirected = isDirected;
		this.nodeKeySet = new LinkedHashSet<>();
		this.isCyclic = false;
		this.isWeighted = false;
		this.adjacencyMapForGraph = new HashMap<>();
	}

	/**
	 * Creates a Graph object with specified type of graph.
	 * 
	 * @param isDirected,
	 *            if true then graph is directed otherwise undirected
	 * @param isWeighted,
	 *            if true then graph is weighted otherwise unweighted
	 */
	public Graph(boolean isDirected, boolean isWeighted) {
		super();
		this.isDirected = isDirected;
		this.nodeKeySet = new LinkedHashSet<>();
		this.isCyclic = false;
		this.isWeighted = isWeighted;
		this.adjacencyMapForGraph = new HashMap<>();
	}

	/**
	 * Creates a GraphNode and puts it into the key-set.
	 * 
	 * @param index
	 * @param label
	 * @return GraphNode
	 * @throws NodeCreationException
	 */
	public GraphNode addNode(int index, String label) throws NodeCreationException {
		GraphNode node = this._createNode(index, label);
		if (node == null) {
			throw new NodeCreationException("unable to create a graph node");
		} else {
			this._updateNodeKeySet(node);
			return node;
		}
	}

	/**
	 * Creates a GraphNode and puts it into the key-set.
	 * 
	 * @param label
	 * @return GraphNode
	 * @throws NodeCreationException
	 */
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

	/**
	 * Connects nodes of an unweighted graph
	 * 
	 * @param source
	 * @param target
	 * @throws NoSuchNodeException
	 */
	public void connectNodes(GraphNode source, GraphNode target) throws NoSuchNodeException {
		if (source != null && target != null) {
			if (source == target) {
				// then this graph is cyclic
				this.isCyclic = true;
			}

			if (this._isPresentInGraph(source) && this._isPresentInGraph(target)) {
				GraphEdge edge = this._getNewEdge(source, target, null);
				if (edge != null) {
					this._createEdge(edge);
				}
			} else {
				if (!this._isPresentInGraph(source)) {
					throw new NoSuchNodeException(source + " is not found in the graph instance.");
				} else {
					throw new NoSuchNodeException(target + " is not found in the graph instance.");
				}
			}

			if (this.isTheGraphUndirected()) {
				GraphEdge complementary_edge = this._getNewEdge(target, source, null);
				this._createEdge(complementary_edge);
			}
		} else {
			throw new NullNodeException("node cannot be connected");
		}
	}

	/**
	 * Connects nodes of a weighted graph
	 * 
	 * @param source
	 * @param target
	 * @param weight
	 * @throws NoSuchNodeException
	 */
	public void connectNodes(GraphNode source, GraphNode target, Integer weight) throws NoSuchNodeException {
		if (this._isGraphWeighted()) {
			if (source != null && target != null && weight != null) {
				if (source == target) {
					// then this graph is cyclic
					this.isCyclic = true;
				}

				if (this._isPresentInGraph(source) && this._isPresentInGraph(target)) {
					GraphEdge edge = this._getNewEdge(source, target, weight);
					if (edge != null) {
						this._createEdge(edge);
					}
				} else {
					if (!this._isPresentInGraph(source)) {
						throw new NoSuchNodeException(source + " is not found in the graph instance.");
					} else {
						throw new NoSuchNodeException(target + " is not found in the graph instance.");
					}
				}

				if (this.isTheGraphUndirected()) {
					GraphEdge complementary_edge = this._getNewEdge(target, source, weight);
					this._createEdge(complementary_edge);
				}
			} else {
				throw new NullNodeException("node cannot be connected");
			}
		} else {
			throw new IncompatibleGraphTypeException("Unweighted graph cannot have weights.");
		}
	}

	private GraphEdge _getNewEdge(GraphNode source, GraphNode target, Integer weight) {
		return new GraphEdge(source, target, weight);
	}

	private void _createEdge(GraphEdge edge) {
		List<GraphEdge> tmpEdgeList = this.adjacencyMapForGraph.get(edge.getSource());

		if (tmpEdgeList != null) {
			tmpEdgeList.remove(edge);
		} else {
			tmpEdgeList = new LinkedList<>();
		}

		tmpEdgeList.add(edge);
		this.adjacencyMapForGraph.put(edge.getSource(), tmpEdgeList);
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

	/**
	 * Prints/Displays the entire adjacency-list representation of a graph.
	 * 
	 * @throws GraphNotFoundException
	 */
	@SuppressWarnings("unused")
	public void displayAdjacencyList() {
		if (this != null) {
			System.out.println("Adjacency List for " + this);
			this._displayAdjacencyList();
			System.out.println();
		} else {
			throw new GraphNotFoundException("Graph is null or empty.");
		}
	}

	private void _displayAdjacencyList() {
		Iterator<GraphNode> itr = this._getNodeKeySet().iterator();
		while (itr.hasNext()) {
			GraphNode node = itr.next();
			List<GraphEdge> tmp = this.adjacencyMapForGraph.get(node);

			if (tmp != null) {
				System.out.print("The Node " + node.getLabel() + " has a edges towards: [ ");
				for (GraphEdge edge : tmp) {
					if (edge.getWeight() != null) {
						System.out.print(edge.getTarget().getLabel() + "(" + edge.getWeight() + ")" + " ");
					} else {
						System.out.print(edge.getTarget().getLabel() + " ");
					}
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

	/**
	 * Un-visit every node in the graph for revisiting them.
	 */
	public void unvisitEveryNode() {
		Iterator<GraphNode> itr = this._getNodeKeySet().iterator();
		while (itr.hasNext()) {
			itr.next().unvisit();
		}
	}

	/**
	 * Prints/Displays the DFS representation of a graph.
	 */
	public void depthFirstSearch() {
		GraphNode node = this._getFirstKeyFromSet();
		if (node == null) {
			return;
		} else {
			System.out.println("DFS Traversal for " + this);
			this._dfsTraversal(node);
		}
	}

	private void _dfsTraversal(GraphNode node) {
		if (!node.isVisited()) {
			System.out.println("Visiting : [ " + node.getLabel() + " ]");
			node.visit();
		}
		List<GraphEdge> edgeList = this._getAssociatedNodeList(node);
		if (edgeList != null) {
			for (GraphEdge edge : edgeList) {
				if (edge != null && !edge.getSource().isVisited()) {
					this._dfsTraversal(edge.getSource());
				}
			}
		} else {
			return;
		}
	}

	/**
	 * Prints/Displays the BFS representation of a graph.
	 */
	public void breadthFirstSearch() {
		GraphNode node = this._getFirstKeyFromSet();
		if (node == null) {
			return;
		} else {
			System.out.println("BFS Traversal for " + this);
			this._bfsTraversal(node);
		}
	}

	private void _bfsTraversal(GraphNode node) {
		Queue<GraphNode> queue = new LinkedList<>();
		queue.add(node);

		while (!queue.isEmpty()) {
			GraphNode tmpNode = queue.remove();

			if (!tmpNode.isVisited()) {
				System.out.println("Visiting : [ " + tmpNode.getLabel() + " ]");
				tmpNode.visit();
			} else {
				continue;
			}

			List<GraphEdge> edgeList = this._getAssociatedNodeList(tmpNode);
			if (edgeList != null) {
				for (GraphEdge edge : edgeList) {
					if (!edge.getSource().isVisited()) {
						queue.add(edge.getSource());
					}
				}
			}
		}
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
	 * Check if two GraphNode objects are connected through a GraphEdge.
	 * 
	 * @param source
	 * @param target
	 * @return boolean
	 */
	public boolean hasEdge(GraphNode source, GraphNode target) {
		if (source != null && target != null) {
			return this._hasEdge(source, target);
		} else {
			return false;
		}
	}

	private boolean _hasEdge(GraphNode source, GraphNode target) {
		boolean isSourceNodeAvaiable = this._isPresentInGraph(source);
		if (isSourceNodeAvaiable) {
			List<GraphEdge> edgeList = this._getAssociatedNodeList(source);
			if (edgeList != null) {
				for (GraphEdge edge : edgeList) {
					if (edge.getSource() == source && edge.getTarget() == target) {
						return true;
					}
				}
			}
		}
		return false;
	}

	private List<GraphEdge> _getAssociatedNodeList(GraphNode currentNode) {
		return this.adjacencyMapForGraph.get(currentNode);
	}

	public boolean isGraphCyclic() {
		return this.isCyclic;
	}

	public boolean isGraphAcyclic() {
		return !this.isCyclic;
	}

	/**
	 * Performs the topological sorting of the nodes of a graph.
	 */
	public void performTopologicalSort() {
		if (this.isGraphCyclic()) {
			throw new CyclicGraphException("Topological sort cannot be performed.");
		} else {
			this._performTopologicalSort(this._getNodeKeySet());
		}
	}

	private void _performTopologicalSort(Set<GraphNode> keySet) {
		Stack<GraphNode> stackOfNodes = new Stack<>();
		for (GraphNode node : keySet) {
			if (node.unVisited()) {
				this._topologicalVisitForNode(node, stackOfNodes);
			}
		}
		this._printTopologicalOrder(stackOfNodes);
	}

	private void _topologicalVisitForNode(GraphNode currentNode, Stack<GraphNode> stackOfNodes) {
		List<GraphEdge> associatedEdgeList = this._getAssociatedNodeList(currentNode);
		List<GraphNode> associatedNodeList = this._convertEdgeListIntoNodeList(associatedEdgeList);

		if (associatedNodeList != null) {
			for (GraphNode associatedNode : associatedNodeList) {
				if (associatedNode.unVisited()) {
					this._topologicalVisitForNode(associatedNode, stackOfNodes);
				}
			}
		}
		currentNode.visit();
		stackOfNodes.push(currentNode);
	}

	private void _printTopologicalOrder(Stack<GraphNode> stack) {
		while (!stack.isEmpty()) {
			System.out.print(stack.pop().getLabel() + " ");
		}
	}

	/**
	 * Removes a GraphNode object from the Graph
	 * 
	 * @param node
	 */
	public void removeGraphNode(GraphNode node) {
		if (node != null) {
			if (this._isPresentInGraph(node)) {
				System.out.println("Removing node " + node + " from graph " + this);
				this._removeGraphNode(node);
			}
		}
	}

	private void _removeGraphNode(GraphNode node) {
		if (this._getNodeKeySet().contains(node)) {
			this._getNodeKeySet().remove(node);
		}

		Set<GraphNode> setOfKeys = this._getNodeKeySet();
		Iterator<GraphNode> itr = setOfKeys.iterator();

		while (itr.hasNext()) {
			GraphNode key = (GraphNode) itr.next();
			List<GraphEdge> edgeList = this._getAssociatedNodeList(key);
			Iterator<GraphEdge> itr_edge = edgeList.iterator();

			while (itr_edge.hasNext()) {
				GraphEdge edge = itr_edge.next();
				if (edge.getTarget() == node) {
					edgeList.remove(edge);
				}
			}
		}
	}

	private List<GraphNode> _convertEdgeListIntoNodeList(List<GraphEdge> edgeList) {
		if (edgeList != null) {
			Iterator<GraphEdge> iterator_for_edges = edgeList.iterator();
			List<GraphNode> nodeList = new ArrayList<GraphNode>();

			while (iterator_for_edges.hasNext()) {
				nodeList.add(iterator_for_edges.next().getTarget());
			}
			return nodeList;
		} else {
			return null;
		}
	}

	/**
	 * Calculates and returns the out-degree of a vertex.
	 * 
	 * @param graph
	 * @param node
	 * @return int
	 */
	public static int getVertexOutDegree(Graph graph, GraphNode node) {
		List<GraphEdge> edgeList = null;
		List<GraphNode> nodeList = null;

		edgeList = graph._getAssociatedNodeList(node);
		nodeList = graph._convertEdgeListIntoNodeList(edgeList);

		if (nodeList != null) {
			return nodeList.size();
		} else {
			return 0;
		}
	}

	/**
	 * Calculates and returns the in-degree of a vertex.
	 * 
	 * @param graph
	 * @param node
	 * @return int
	 */
	public static int getVertexInDegree(Graph graph, GraphNode node) {
		int inVertex = 0;
		Set<GraphNode> keySet = graph._getNodeKeySet();
		Iterator<GraphNode> itr = keySet.iterator();
		while (itr.hasNext()) {
			List<GraphEdge> edgeList = graph._getAssociatedNodeList(itr.next());
			if (edgeList != null) {
				for (GraphEdge edge : edgeList) {
					if (edge != null) {
						if (edge.getTarget() == node) {
							inVertex += 1;
						}
					}
				}
			}
		}
		return inVertex;
	}

	/**
	 * Calculates and returns the total degree (in + out) of a vertex.
	 * 
	 * @param graph
	 * @param node
	 * @return int
	 */
	public static int getVertexDegree(Graph graph, GraphNode node) {
		return (getVertexInDegree(graph, node) + getVertexOutDegree(graph, node));
	}

	/**
	 * Calculates and returns the degree of a this Graph.
	 * 
	 * @param graph
	 * @param node
	 * @return int
	 */
	public static int getGraphDegree(Graph graph) {
		int graphDegree = 0;
		Set<GraphNode> keySet = graph._getNodeKeySet();
		Iterator<GraphNode> itr = keySet.iterator();
		while (itr.hasNext()) {
			int vertexDegree = getVertexDegree(graph, itr.next());
			if (vertexDegree > graphDegree) {
				graphDegree = vertexDegree;
			}
		}
		return graphDegree;
	}

	private boolean _isGraphWeighted() {
		return this.isWeighted;
	}

	/**
	 * Gives the size of the graph i.e. # of GraphNode objects in Graph.
	 * 
	 * @return int
	 */
	public int getGraphSize() {
		return this._getNodeKeySet().size();
	}
}
