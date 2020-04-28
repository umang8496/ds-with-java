package ds.graph;

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
import ds.exp.NoSuchNodeException;
import ds.exp.NodeCreationException;
import ds.exp.NullNodeException;

public class Graph {
	private int numberOfNodes;
	private Map<GraphNode, List<GraphNode>> adjacencyMap;
	private Set<GraphNode> nodeKeySet;
	private boolean isCyclic;
	private boolean isDirected;

	// creates undirected graph always
	public Graph() {
		this.isDirected = false;
		this.numberOfNodes = 0;
		this.adjacencyMap = new HashMap<>();
		this.nodeKeySet = new LinkedHashSet<>();
		this.isCyclic = false;
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

	private GraphNode _createNode(int index, String label) {
		return new GraphNode(index, label);
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

			if (!this.isTheGraphDirected()) {
				this._createEdge(target, source);
			}
		} else {
			throw new NullNodeException("node cannot be connected");
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
		GraphNode node = this._getFirstKeyFromMap();
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
		GraphNode node = this._getFirstKeyFromMap();
		if (node == null) {
			return;
		} else {
			System.out.println("BFS Traversal for " + this);
			this._bfsTraversal(node);
		}
	}

	private GraphNode _getFirstKeyFromMap() {
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

	public boolean isGraphCyclic() {
		return this.isCyclic;
	}

	public boolean isGraphAcyclic() {
		return !this.isCyclic;
	}

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
				this.topologicalVisit(node, stackOfNodes);
			}
		}
		this._printTopologicalOrder(stackOfNodes);
	}

	private void topologicalVisit(GraphNode currentNode, Stack<GraphNode> stackOfNodes) {
		List<GraphNode> associatedNodeList = this._getAssociatedNodeList(currentNode);

		if (associatedNodeList != null) {
			for (GraphNode associatedNode : associatedNodeList) {
				if (associatedNode.unVisited()) {
					this.topologicalVisit(associatedNode, stackOfNodes);
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

	public int getVertexDegree(GraphNode node) {
		return 0;
	}
	
	public int getGraphDegree() {
		return 0;
	}
	
	public int getInDegreeOfVertex(GraphNode node) {
		return 0;
	}
	
	public int getOutDegreeOfVertex(GraphNode node) {
		return 0;
	}
	
	public boolean isGraphConnected() {
		return false;
	}
	
	public int getNumberOfMutualNodesBetween(GraphNode A, GraphNode B) {
		return 0;
	}
	
	public List<GraphNode> getListOfMutualNodesBetween(GraphNode A, GraphNode B) {
		return null;
	}
	
	public static void connectIndependentNodes(GraphNode source, GraphNode target) {
		
	}
	
}
