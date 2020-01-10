package graph;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		List<Pair> l = new ArrayList<Pair>();
		l.add(new Pair("A", "B"));
		l.add(new Pair("B", "C"));
		l.add(new Pair("B", "D"));
		l.add(new Pair("B", "E"));
		l.add(new Pair("C", "D")); // C, D in the same level
		l.add(new Pair("D", "F"));
		l.add(new Pair("E", "G"));
		l.add(new Pair("G", "K"));
		l.add(new Pair("G", "H"));

		// Example for connectedComponent
//		l.add(new Pair("I", "L"));
		
		Graph g = new Graph(l);
		g.printAdjList();
		System.out.println(g.toString());
//		System.out.println("Are D and G adjacent? " + g.areAdjacent(new Vertex("D"), new Vertex("G")));
//		System.out.println("Are A and F adjacent? " + g.areAdjacent(new Vertex("A"), new Vertex("F")));
//		System.out.println("Are G and F adjacent? " + g.areAdjacent(new Vertex("G"), new Vertex("F")));
//		System.out.println("Spanning Tree:");
//		Graph t = g.getSpanningTree();
//		System.out.println(t);
//		System.out.println("Connected Components:");
//		System.out.println(g.getConnectedComponents().toString());
		System.out.println("Graph contains odd cycle? " + g.containsCycle());
		System.out.println("Exist path between C and G ? " + g.existsPathBetween(new Vertex("C"), new Vertex("G")) + " => " + g.path.toString());
	}
}
