package graph;

import java.util.*;

//implement
public class ConnectedComponentSearch extends DepthFirstSearch {
	private ArrayList<Edge> tree = new ArrayList<Edge>();
	
	public ConnectedComponentSearch(Graph graph) {
		super(graph);
	}
	
	protected void processEdge(Edge e) {
		tree.add(e);
	}

	public List<Graph> computeConnectedComponent() {
		start();
		return listGraph;
	}
}
