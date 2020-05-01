package ds.graph;

public class GraphDemo {
	public static void main(String[] args) {
		Graph g1 = new Graph(false);		// for undirected graph
		Graph g2 = new Graph(true);			// for directed graph

		GraphNode a = g1.addNode(1, "A");
		GraphNode b = g1.addNode(2, "B");
		GraphNode c = g1.addNode(3, "C");
		GraphNode d = g1.addNode(4, "D");
		GraphNode e = g1.addNode(5, "E");
		GraphNode f = g1.addNode(6, "F");
		
		GraphNode p = g2.addNode("P");
		GraphNode q = g2.addNode("Q");
		GraphNode r = g2.addNode("R");
		GraphNode s = g2.addNode("S");

		// g1.connectNodes(a, b);
		// g1.connectNodes(b, c);
		// g1.connectNodes(c, d);
		// g1.connectNodes(d, e);
		// g1.connectNodes(e, f);
		// g1.connectNodes(f, a);

		g1.connectNodes(a, a);
		g1.connectNodes(a, b);
		g1.connectNodes(a, c);
		g1.connectNodes(b, d);
		g1.connectNodes(b, e);
		g1.connectNodes(d, f);
		
		g2.connectNodes(p, q);
		g2.connectNodes(p, r);
		g2.connectNodes(r, s);
		g2.connectNodes(s, q);

		System.out.println("Number of nodes in the " + g1 + " : " + g1.getNumberOfNodes());
		System.out.println("Number of nodes in the " + g2 + " : " + g2.getNumberOfNodes());
		System.out.println();
		
		System.out.println("Is the graph g1 directed : " + g1.isTheGraphDirected());
		System.out.println("Is the graph g2 directed : " + g2.isTheGraphDirected());
		System.out.println();
				
		g1.displayAdjacencyList();
		g2.displayAdjacencyList();
		System.out.println();
		
		g1.breadthFirstSearch();
		g2.breadthFirstSearch();
		System.out.println();
		
		g1.unvisitEveryNode();
		g2.unvisitEveryNode();

		g1.depthFirstSearch();
		g2.depthFirstSearch();
		System.out.println();
		
		System.out.println("Is there an edge in " + g1 + " from a to b " + g1.hasEdge(a, b));
		System.out.println("Is there an edge in " + g1 + " from b to a " + g1.hasEdge(b, a));
		System.out.println("Is there an edge in " + g1 + " from c to e " + g1.hasEdge(c, e));
		System.out.println("Is there an edge in " + g1 + " from e to c " + g1.hasEdge(e, c));
		System.out.println();
		
		g1.removeGraphNode(e);
		g1.displayAdjacencyList();
		System.out.println();
		
		System.out.println("Is there an edge in " + g1 + " from c to e " + g1.hasEdge(c, e));
		System.out.println("Is there an edge in " + g1 + " from e to c " + g1.hasEdge(e, c));
		System.out.println();
		
		System.out.println("Is graph g1 " + g1 +" cyclic : " + g1.isGraphCyclic());
		System.out.println("Is graph g2 " + g2 +" cyclic : " + g2.isGraphCyclic());
		System.out.println();
		
		// GraphNode alpha = GraphNode.createIndependentNodes(10, "alpha");
		// GraphNode beta  = GraphNode.createIndependentNodes(20, "beta" );
		// GraphNode gamma = GraphNode.createIndependentNodes(30, "gamma");
		
		/** Test code for weighted graph **/
		Graph g3 = new Graph(true, true);			// for directed graph
		GraphNode w = g3.addNode("W");
		GraphNode x = g3.addNode("X");
		GraphNode y = g3.addNode("Y");
		GraphNode z = g3.addNode("Z");
		
		g3.connectNodes(w, x, 4);
		g3.connectNodes(w, y, 7);
		g3.connectNodes(y, z, 3);
		g3.connectNodes(z, x, 6);
		g3.displayAdjacencyListForWeightedNode();
		System.out.println();
		
		System.out.println("Graph Size for " + g1 + " : " + g1.getGraphSize());
		System.out.println("Graph Size for " + g2 + " : " + g2.getGraphSize());
		System.out.println("Graph Size for " + g3 + " : " + g3.getGraphSize());
		System.out.println();
	}
	
}
