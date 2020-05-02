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
import ds.exp.IncompatibleGraphTypeException;
import ds.exp.NoSuchNodeException;
import ds.exp.NodeCreationException;
import ds.exp.NullNodeException;

public class Graph {
	private int numberOfNodes;
	private Map<GraphNode, List<GraphNode>> adjacencyMap;
	private Set<GraphNode> nodeKeySet;
	private boolean isCyclic;
	private boolean isDirected;
	private boolean isWeighted;
	private Map<GraphNode, List<GraphEdge>> adjacencyMapForWeightedNode;

	// creates undirected graph always
	public Graph() {
		this.isDirected = false;
		this.numberOfNodes = 0;
		this.adjacencyMap = new HashMap<>();
		this.nodeKeySet = new LinkedHashSet<>();
		this.isCyclic = false;
		this.isWeighted = false;
	}

	// creates directed/undirected graph based on the boolean value
	// for directed graph:		true
	// for undirected graph:	false
	public Graph(boolean isDirected) {
		super();
		this.isDirected = isDirected;
		this.numberOfNodes = 0;
		this.adjacencyMap = new HashMap<>();
		this.nodeKeySet = new LinkedHashSet<>();
		this.isCyclic = false;
		this.isWeighted = false;
	}
	
	// creates the weighted graph if isWeighted is true
	public Graph(boolean isDirected, boolean isWeighted) {
		super();
		this.isDirected = isDirected;
		this.numberOfNodes = 0;		
		this.nodeKeySet = new LinkedHashSet<>();
		this.isCyclic = false;
		this.isWeighted = isWeighted;
		if(isWeighted == false) {
			this.adjacencyMap = new HashMap<>();
		} else {
			this.adjacencyMapForWeightedNode = new HashMap<>();
		}
	}

	public GraphNode addNode(int index, String label) throws NodeCreationException {
		GraphNode node = this._createNode(index, label);
		if (node == null) {
			throw new NodeCreationException("unable to create a graph node");
		} else {
			this._updateNodeCount();
			this._updateNodeKeySet(node);
			return node;
		}
	}

	public GraphNode addNode(String label) throws NodeCreationException {
		GraphNode node = this._createNode(label);
		if (node == null) {
			throw new NodeCreationException("unable to create a graph node");
		} else {
			this._updateNodeCount();
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

	private void _updateNodeCount() {
		this.numberOfNodes += 1;
	}

	private void _updateNodeKeySet(GraphNode node) {
		this.nodeKeySet.add(node);
	}

	public void connectNodes(GraphNode source, GraphNode target) throws NoSuchNodeException {
		if (source != null && target != null) {
			if (source == target) {
				// then this graph is cyclic
				this.isCyclic = true;
			}

			if (this._isPresentInGraph(source) && this._isPresentInGraph(target)) {
				this._createEdge(source, target);
			} else {
				if (!this._isPresentInGraph(source)) {
					throw new NoSuchNodeException(source + " is not found in the graph instance.");
				} else {
					throw new NoSuchNodeException(target + " is not found in the graph instance.");
				}
			}

			if (this.isTheGraphUndirected()) {
				this._createEdge(target, source);
			}
		} else {
			throw new NullNodeException("node cannot be connected");
		}
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
	
	private void _createEdge(GraphNode source, GraphNode target) {
		List<GraphNode> tmpList = this.adjacencyMap.get(source);

		if (tmpList != null) {
			tmpList.remove(target);
		} else {
			tmpList = new LinkedList<>();
		}

		tmpList.add(target);
		this.adjacencyMap.put(source, tmpList);
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

	public void displayAdjacencyList() {
		System.out.println("Adjacency List for " + this);
		this._displayAdjacencyList();
		System.out.println();
	}

	private void _displayAdjacencyList() {
		Iterator<GraphNode> itr = this._getNodeKeySet().iterator();
		while (itr.hasNext()) {
			GraphNode node = itr.next();
			List<GraphNode> tmp = this.adjacencyMap.get(node);

			if (tmp != null) {
				System.out.print("The Node " + node.getLabel() + " has a edges towards: [ ");
				for (GraphNode n : tmp) {
					System.out.print(n.getLabel() + " ");
				}
				System.out.println("]");
			} else {
				System.out.println("The Node " + node.getLabel() + " has a edges towards: [ ]");
			}
		}
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

	public int getNumberOfNodes() {
		return numberOfNodes;
	}

	public void unvisitEveryNode() {
		Iterator<GraphNode> itr = this._getNodeKeySet().iterator();
		while (itr.hasNext()) {
			itr.next().unvisit();
		}
	}

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

		List<GraphNode> nodeList = this._getAssociatedNodeList(node);

		if (nodeList != null) {
			for (GraphNode associatedNode : nodeList) {
				if (associatedNode != null && !associatedNode.isVisited()) {
					this._dfsTraversal(associatedNode);
				}
			}
		} else {
			return;
		}
	}

	public void breadthFirstSearch() {
		GraphNode node = this._getFirstKeyFromSet();
		if (node == null) {
			return;
		} else {
			System.out.println("BFS Traversal for " + this);
			this._bfsTraversal(node);
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

			List<GraphNode> nodeList = this._getAssociatedNodeList(tmpNode);
			if (nodeList != null) {
				for (GraphNode associatedNode : nodeList) {
					if (!associatedNode.isVisited()) {
						queue.add(associatedNode);
					}
				}
			}
		}
	}

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
			List<GraphNode> nodeList = this._getAssociatedNodeList(source);
			return nodeList != null ? nodeList.contains(target) : false;
		} else {
			return false;
		}
	}

	private List<GraphNode> _getAssociatedNodeList(GraphNode currentNode) {
		return this.adjacencyMap.get(currentNode);
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

	public void performTopologicalSort() {
		if(this._isGraphWeighted()) {
			if (this.isGraphCyclic()) {
				throw new CyclicGraphException("Topological sort cannot be performed.");
			} else {
				this._performTopologicalSortForWeightedGraph(this._getNodeKeySet());
			}
		} else {
			if (this.isGraphCyclic()) {
				throw new CyclicGraphException("Topological sort cannot be performed.");
			} else {
				this._performTopologicalSort(this._getNodeKeySet());
			}
		}
	}

	private void _performTopologicalSort(Set<GraphNode> keySet) {
		Stack<GraphNode> stackOfNodes = new Stack<>();
		for (GraphNode node : keySet) {
			if (node.unVisited()) {
				this._topologicalVisit(node, stackOfNodes);
			}
		}
		this._printTopologicalOrder(stackOfNodes);
	}
	
	private void _performTopologicalSortForWeightedGraph(Set<GraphNode> keySet) {
		Stack<GraphNode> stackOfNodes = new Stack<>();
		for (GraphNode node : keySet) {
			if (node.unVisited()) {
				this._topologicalVisitForWeightedGraph(node, stackOfNodes);
			}
		}
		this._printTopologicalOrder(stackOfNodes);
	}

	private void _topologicalVisit(GraphNode currentNode, Stack<GraphNode> stackOfNodes) {
		List<GraphNode> associatedNodeList = this._getAssociatedNodeList(currentNode);

		if (associatedNodeList != null) {
			for (GraphNode associatedNode : associatedNodeList) {
				if (associatedNode.unVisited()) {
					this._topologicalVisit(associatedNode, stackOfNodes);
				}
			}
		}
		currentNode.visit();
		stackOfNodes.push(currentNode);
	}

	private void _topologicalVisitForWeightedGraph(GraphNode currentNode, Stack<GraphNode> stackOfNodes) {
		List<GraphEdge> associatedEdgeList = this._getAssociatedWeightedNodeList(currentNode);
		List<GraphNode> associatedNodeList = this._convertEdgeListIntoNodeList(associatedEdgeList);

		if (associatedNodeList != null) {
			for (GraphNode associatedNode : associatedNodeList) {
				if (associatedNode.unVisited()) {
					this._topologicalVisitForWeightedGraph(associatedNode, stackOfNodes);
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

	public void removeGraphNode(GraphNode node) {
		if (node != null) {
			if (this._isPresentInGraph(node)) {
				System.out.println("Removing node " + node + " from graph " + this);
				this._removeVertex(node);
			}
		}
	}

	private void _removeVertex(GraphNode node) {
		GraphNode node_to_be_removed = node;

		if (this._getNodeKeySet().contains(node)) {
			this._getNodeKeySet().remove(node);
		}

		Set<GraphNode> setOfKeys = this._getNodeKeySet();
		Iterator<GraphNode> itr = setOfKeys.iterator();
		while (itr.hasNext()) {
			GraphNode key = (GraphNode) itr.next();
			List<GraphNode> list = this._getAssociatedNodeList(key);
			if (list.contains(node_to_be_removed))
				list.remove(node_to_be_removed);
		}
	}
	
	private List<GraphNode> _convertEdgeListIntoNodeList(List<GraphEdge> edgeList) {
		if (edgeList != null) {
			Iterator<GraphEdge> iterator_for_edges = edgeList.iterator();
			List<GraphNode> nodeList = new ArrayList<GraphNode>();
			
			while(iterator_for_edges.hasNext()){
				nodeList.add(iterator_for_edges.next().getTarget());
			}
			return nodeList;
		} else {
			return null;
		}
	}
	
	public static int getVertexOutDegree(Graph graph, GraphNode node) {
		List<GraphEdge> edgeList = null;
		List<GraphNode> nodeList = null;

		if (graph._isGraphWeighted()) {
			edgeList = graph._getAssociatedWeightedNodeList(node);
			nodeList = graph._convertEdgeListIntoNodeList(edgeList);
		} else {
			nodeList = graph._getAssociatedNodeList(node);
		}

		if (nodeList != null) {
			return nodeList.size();
		} else {
			return 0;
		}
	}

	public static int getVertexInDegree(Graph graph, GraphNode node) {
		int inVertex = 0;
		
		if (graph._isGraphWeighted()) {
			Set<GraphNode> keySet = graph._getNodeKeySet();
			Iterator<GraphNode> itr = keySet.iterator();
			while (itr.hasNext()) {
				List<GraphEdge> edgeList = graph._getAssociatedWeightedNodeList(itr.next());
				if (edgeList != null) {
					for (GraphEdge edge : edgeList) {
						if(edge != null) {
							if (edge.getTarget() == node) {
								inVertex += 1;
							}
						}
					}
				}
			}
		} else {
			List<GraphNode> nodeList = null;
			Set<GraphNode> keySet = graph._getNodeKeySet();
			Iterator<GraphNode> itr = keySet.iterator();
			while (itr.hasNext()) {
				nodeList = graph._getAssociatedNodeList(itr.next());
				if (nodeList != null) {
					if (nodeList.contains(node)) {
						inVertex += 1;
					}
				}
			}
		}

		return inVertex;
	}
	
	public static int getVertexDegree(Graph graph, GraphNode node) {
		return (getVertexInDegree(graph, node) + getVertexOutDegree(graph, node));
	}
	
	public static int getGraphDegree(Graph graph) {
		int graphDegree = 0;
		Set<GraphNode> keySet = graph._getNodeKeySet();
		Iterator<GraphNode> itr = keySet.iterator();
		while (itr.hasNext()) {
			int vertexDegree = getVertexDegree(graph, itr.next());
			if(vertexDegree > graphDegree) {
				graphDegree = vertexDegree;
			}
		}
		return graphDegree;
	}
	
	public static void connectIndependentNodes(GraphNode source, GraphNode target) {
		
	}
	
	private boolean _isGraphWeighted() {
		return this.isWeighted;
	}

	public int getGraphSize() {
		return this._getNodeKeySet().size();
	}
}
