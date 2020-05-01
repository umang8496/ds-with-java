package ds.graph;

public class GraphInfo {
	public static void main(String[] args) {
		Graph g1 = new Graph(false); 		// for undirected graph
		Graph g2 = new Graph(true); 		// for directed graph

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

		g1.displayAdjacencyList();
		g2.displayAdjacencyList();
		System.out.println();
		
		System.out.println("Vertex degree for " + a + " : " + Graph.getVertexDegree(g1, a));
		System.out.println("Vertex degree for " + b + " : " + Graph.getVertexDegree(g1, b));
		System.out.println("Vertex degree for " + c + " : " + Graph.getVertexDegree(g1, c));
		System.out.println("Vertex degree for " + d + " : " + Graph.getVertexDegree(g1, d));
		System.out.println("Vertex degree for " + e + " : " + Graph.getVertexDegree(g1, e));
		System.out.println("Vertex degree for " + f + " : " + Graph.getVertexDegree(g1, f));
		System.out.println();
		
		System.out.println("Vertex degree for " + p + " : " + Graph.getVertexDegree(g2, p));
		System.out.println("Vertex degree for " + q + " : " + Graph.getVertexDegree(g2, q));
		System.out.println("Vertex degree for " + r + " : " + Graph.getVertexDegree(g2, r));
		System.out.println("Vertex degree for " + s + " : " + Graph.getVertexDegree(g2, s));
		System.out.println();
		
//		Graph g3 = new Graph(true, true); 	// for directed graph
//		GraphNode w = g3.addNode("W");
//		GraphNode x = g3.addNode("X");
//		GraphNode y = g3.addNode("Y");
//		GraphNode z = g3.addNode("Z");
//
//		g3.connectNodes(w, x, 4);
//		g3.connectNodes(w, y, 7);
//		g3.connectNodes(y, z, 3);
//		g3.connectNodes(z, x, 6);
//		g3.displayAdjacencyListForWeightedNode();
	}
}
