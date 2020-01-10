package graph;

import java.util.*;

//implement
public class BreadthFirstSearch {
	protected HashMap<Vertex, Vertex> visitedVertices = new HashMap<>();
	Graph graph;
	Queue<Vertex> queue;
	List<Vertex> vertices = null;
	Iterator<Vertex> iterator = null;
	List<Edge> edges = null;
	protected int numVertices;
	protected int numEdges;
	boolean cycle = false;
	boolean oddCycle = false;
	int oddCycleLength;
	protected List<Vertex> bfsList = new ArrayList<Vertex>();
	
	public BreadthFirstSearch(Graph g) {
		queue = new LinkedList<Vertex>();
		this.graph = g;
		this.vertices = g.vertices;
		this.iterator = this.vertices.iterator();
		this.numVertices = this.vertices.size();
		this.edges = g.edges;
		this.numEdges = this.edges.size();
	}
	
	protected void resetVisitedVertices() {
		visitedVertices.clear();
	}

	protected void resetVertexIterator() {
		iterator = vertices.iterator();
	}
	
	protected boolean checVerticesUnvisited() {
		return visitedVertices.size() < numVertices;
	}
	
	public void start() {
		bfs();
	}

	protected void bfs() {
		Vertex s = nextUnvisited();
		int cnt = 0;
		if (null != s) {
			setVisited(s);
			queue.add(s);
			while (!queue.isEmpty()) {
				Vertex v = queue.poll();
				bfsList.add(v);
				List<Vertex> listAdj = graph.getListOfAdjacentVerts(v);
				Iterator<Vertex> it = listAdj.iterator();
				while (it.hasNext()) {	
					Vertex u = it.next();
					if (!checkVisited(u)) {
						setVisited(u);
						queue.add(u);
						List<Vertex> subList = graph.getListOfAdjacentVerts(u);
						int counter = 0;
						cnt++;
						for (Vertex sv : subList) {
							if (checkVisited(sv))
								counter++;
							if (counter % 2 == 0) {
								cycle = true;
								if (cnt % 2 != 0) {
									oddCycle = true;
									oddCycleLength = cnt;
								}
								cnt = 0;
								break;
							}
						}
					}
				}
			}
		}
	}

	protected void processVertex(Vertex v) {
		if (null != v)
			System.out.println("Process vertex: " + v.toString());
		else
			System.out.println("Null vertex to process");
	}

	protected void processEdge(Edge edge) {
		
	}
	
	public void setVisited(Vertex v) {
		System.out.println("Visited: " + v.toString());
		visitedVertices.put(v, v);
	}
	
	public boolean checkVisited(Vertex v) {
		return visitedVertices.containsKey(v);
	}

	public Vertex nextUnvisited() {
		while (iterator.hasNext()) {
			Vertex next = iterator.next();
			if (!visitedVertices.containsKey(next)) 
				return next;
		}
		return null;
	}
}
