package ds.graph;

import ds.exp.CyclicGraphException;

/**
 * Topological sorting for Directed Acyclic Graph (DAG) is a linear ordering of vertices such that
 * for every directed edge uv, vertex u comes before v in the ordering.
 * Topological Sorting for a graph is not possible if the graph is not a DAG.
 * Any linear ordering in which all the arrows go to the right is a valid solution.
 * 
 */
public class TopologicalSortDemo {

	public static void main(String[] args) {
		Graph g1 = new Graph(true);
		Graph g2 = new Graph(true);
		
		GraphNode a = g1.addNode(1, "A");
		GraphNode b = g1.addNode(2, "B");
		GraphNode c = g1.addNode(3, "C");
		GraphNode d = g1.addNode(4, "D");
		GraphNode e = g1.addNode(5, "E");
		GraphNode f = g1.addNode(6, "F");
		GraphNode g = g1.addNode(7, "G");
		GraphNode h = g1.addNode(8, "H");
		
		GraphNode p = g2.addNode("P");
		GraphNode q = g2.addNode("Q");
		GraphNode r = g2.addNode("R");
		GraphNode s = g2.addNode("S");
		
		g1.connectNodes(a, c);
		g1.connectNodes(b, c);
		g1.connectNodes(b, d);
		g1.connectNodes(d, f);
		g1.connectNodes(c, e);
		g1.connectNodes(e, h);
		g1.connectNodes(e, f);
		g1.connectNodes(f, g);
		
		// graph.connectNodes(a, a);
		// graph.connectNodes(a, b);
		// graph.connectNodes(b, c);
		// graph.connectNodes(b, d);
		// graph.connectNodes(c, e);
		
		g2.connectNodes(p, q, 4);
		g2.connectNodes(p, r, 7);
		g2.connectNodes(r, s, 3);
		g2.connectNodes(s, q, 6);
		
		System.out.println("Number of nodes in the " + g1 + " : " + g1.getNumberOfNodes());
		System.out.println("Number of nodes in the " + g2 + " : " + g2.getNumberOfNodes());
		System.out.println();
		
		g1.displayAdjacencyList();
		g2.displayAdjacencyListForWeightedNode();
		System.out.println();
		
		try {
			System.out.println("Topological Sort for " + g1 + " graph is shown below:");
			g1.performTopologicalSort();
			System.out.println();
			System.out.println("Topological Sort for " + g2 + " graph is shown below:");
			g2.performTopologicalSort();
			System.out.println();
		} catch (CyclicGraphException cge) {
			cge.printStackTrace();		
		}
	}
	
}
