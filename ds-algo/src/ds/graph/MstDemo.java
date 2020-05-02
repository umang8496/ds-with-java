package ds.graph;

public class MstDemo {
	public static MinimumSpanningTree mst_undirected = null;
	public static MinimumSpanningTree mst_directed = null;
	
	public static MinimumSpanningTree primsMst = null; 
	public static MinimumSpanningTree kruskalsMst = null;
	
	public static void main(String[] args) {
		mst_undirected = new MinimumSpanningTree(false, true);	// undirected and weighted graph
		
		GraphNode a = mst_undirected.addNode(1, "A");
		GraphNode b = mst_undirected.addNode(2, "B");
		GraphNode c = mst_undirected.addNode(3, "C");
		GraphNode d = mst_undirected.addNode(4, "D");
		GraphNode e = mst_undirected.addNode(5, "E");
		GraphNode f = mst_undirected.addNode(6, "F");
		GraphNode g = mst_undirected.addNode(7, "G");
		GraphNode h = mst_undirected.addNode(8, "H");
		GraphNode i = mst_undirected.addNode(9, "I");

		mst_undirected.connectNodes(a, b, 4);
		mst_undirected.connectNodes(a, h, 8);
		mst_undirected.connectNodes(b, c, 8);
		mst_undirected.connectNodes(b, h, 11);
		mst_undirected.connectNodes(c, i, 2);
		mst_undirected.connectNodes(c, d, 7);
		mst_undirected.connectNodes(d, e, 9);
		mst_undirected.connectNodes(d, f, 14);
		mst_undirected.connectNodes(e, f, 10);
		mst_undirected.connectNodes(c, f, 4);
		mst_undirected.connectNodes(f, g, 2);
		mst_undirected.connectNodes(g, i, 6);
		mst_undirected.connectNodes(i, h, 7);
		mst_undirected.connectNodes(g, h, 1);

		mst_undirected.displayAdjacencyListForWeightedNode();
		System.out.println();
		
		primsMst =  mst_undirected.getPrimsMinimumSpanningTree();
		
		primsMst.displayAdjacencyListForWeightedNode();
		System.out.println("Mst cost for the graph: " + primsMst.getMstCost());
		System.out.println();
		
//		kruskalsMst =  mst_undirected.getKruskalsMinimumSpanningTree();
//		kruskalsMst.displayAdjacencyListForWeightedNode();
//		System.out.println();
		
		mst_undirected = new MinimumSpanningTree(false, true);
		
		GraphNode n1 = mst_undirected.addNode(1, "n1");
		GraphNode n2 = mst_undirected.addNode(2, "n2");
		GraphNode n3 = mst_undirected.addNode(3, "n3");
		GraphNode n4 = mst_undirected.addNode(4, "n4");
		GraphNode n5 = mst_undirected.addNode(5, "n5");
		GraphNode n6 = mst_undirected.addNode(6, "n6");
		
		mst_undirected.connectNodes(n1, n2, 4);
		mst_undirected.connectNodes(n1, n3, 2);
		mst_undirected.connectNodes(n2, n3, 4);
		mst_undirected.connectNodes(n1, n4, 3);
		mst_undirected.connectNodes(n1, n5, 4);
		mst_undirected.connectNodes(n1, n6, 2);
		mst_undirected.connectNodes(n4, n5, 3);
		mst_undirected.connectNodes(n5, n6, 3);
		
		mst_undirected.displayAdjacencyListForWeightedNode();
		System.out.println();
		
		primsMst =  mst_undirected.getPrimsMinimumSpanningTree();
		
		primsMst.displayAdjacencyListForWeightedNode();
		System.out.println("Mst cost for the graph: " + primsMst.getMstCost());
		System.out.println();
		
//		kruskalsMst =  mst_undirected.getKruskalsMinimumSpanningTree();
//		kruskalsMst.displayAdjacencyListForWeightedNode();
//		System.out.println();
		
	}
}
