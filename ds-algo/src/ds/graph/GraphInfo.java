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
		
		System.out.println("Out degree for " + a + " vertex : " + Graph.getVertexOutDegree(g1, a));
		System.out.println("Out degree for " + b + " vertex : " + Graph.getVertexOutDegree(g1, b));
		System.out.println("Out degree for " + c + " vertex : " + Graph.getVertexOutDegree(g1, c));
		System.out.println("Out degree for " + d + " vertex : " + Graph.getVertexOutDegree(g1, d));
		System.out.println("Out degree for " + e + " vertex : " + Graph.getVertexOutDegree(g1, e));
		System.out.println("Out degree for " + f + " vertex : " + Graph.getVertexOutDegree(g1, f));
		System.out.println();
		
		System.out.println("In degree for " + a + " vertex : " + Graph.getVertexInDegree(g1, a));
		System.out.println("In degree for " + b + " vertex : " + Graph.getVertexInDegree(g1, b));
		System.out.println("In degree for " + c + " vertex : " + Graph.getVertexInDegree(g1, c));
		System.out.println("In degree for " + d + " vertex : " + Graph.getVertexInDegree(g1, d));
		System.out.println("In degree for " + e + " vertex : " + Graph.getVertexInDegree(g1, e));
		System.out.println("In degree for " + f + " vertex : " + Graph.getVertexInDegree(g1, f));
		System.out.println();
		
		System.out.println("Total degree for " + a + " vertex : " + Graph.getVertexDegree(g1, a));
		System.out.println("Total degree for " + b + " vertex : " + Graph.getVertexDegree(g1, b));
		System.out.println("Total degree for " + c + " vertex : " + Graph.getVertexDegree(g1, c));
		System.out.println("Total degree for " + d + " vertex : " + Graph.getVertexDegree(g1, d));
		System.out.println("Total degree for " + e + " vertex : " + Graph.getVertexDegree(g1, e));
		System.out.println("Total degree for " + f + " vertex : " + Graph.getVertexDegree(g1, f));
		System.out.println();
		
		System.out.println("Out degree for " + p + " vertex : " + Graph.getVertexOutDegree(g2, p));
		System.out.println("Out degree for " + q + " vertex : " + Graph.getVertexOutDegree(g2, q));
		System.out.println("Out degree for " + r + " vertex : " + Graph.getVertexOutDegree(g2, r));
		System.out.println("Out degree for " + s + " vertex : " + Graph.getVertexOutDegree(g2, s));
		System.out.println();
		
		System.out.println("In degree for " + p + " vertex : " + Graph.getVertexInDegree(g2, p));
		System.out.println("In degree for " + q + " vertex : " + Graph.getVertexInDegree(g2, q));
		System.out.println("In degree for " + r + " vertex : " + Graph.getVertexInDegree(g2, r));
		System.out.println("In degree for " + s + " vertex : " + Graph.getVertexInDegree(g2, s));
		System.out.println();
		
		System.out.println("Total degree for " + p + " vertex : " + Graph.getVertexDegree(g2, p));
		System.out.println("Total degree for " + q + " vertex : " + Graph.getVertexDegree(g2, q));
		System.out.println("Total degree for " + r + " vertex : " + Graph.getVertexDegree(g2, r));
		System.out.println("Total degree for " + s + " vertex : " + Graph.getVertexDegree(g2, s));
		System.out.println();
		
		
		Graph g3 = new Graph(true, true); 	// for directed graph
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
		
		System.out.println("Out degree for " + w + " vertex : " + Graph.getVertexOutDegree(g3, w));
		System.out.println("Out degree for " + x + " vertex : " + Graph.getVertexOutDegree(g3, x));
		System.out.println("Out degree for " + y + " vertex : " + Graph.getVertexOutDegree(g3, y));
		System.out.println("Out degree for " + z + " vertex : " + Graph.getVertexOutDegree(g3, z));
		System.out.println();
		
		System.out.println("In degree for " + w + " vertex : " + Graph.getVertexInDegree(g3, w));
		System.out.println("In degree for " + x + " vertex : " + Graph.getVertexInDegree(g3, x));
		System.out.println("In degree for " + y + " vertex : " + Graph.getVertexInDegree(g3, y));
		System.out.println("In degree for " + z + " vertex : " + Graph.getVertexInDegree(g3, z));
		System.out.println();
		
		System.out.println("Total degree for " + w + " vertex : " + Graph.getVertexDegree(g3, w));
		System.out.println("Total degree for " + x + " vertex : " + Graph.getVertexDegree(g3, x));
		System.out.println("Total degree for " + y + " vertex : " + Graph.getVertexDegree(g3, y));
		System.out.println("Total degree for " + z + " vertex : " + Graph.getVertexDegree(g3, z));
		System.out.println();
	}
}
