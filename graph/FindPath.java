package graph;

public class FindPath extends DepthFirstSearch {

	public FindPath(Graph graph) {
		super(graph);
	}
	
	public void findPathBetween(Vertex v, Vertex u) {
		findPath(v, u);
	}
}
