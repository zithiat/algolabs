package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Stack;

public class DepthFirstSearch {

	protected HashMap<Vertex, Vertex> visitedVertices = new HashMap<>();
	Graph graph;
	Stack<Vertex> stack;
	List<Vertex> vertices = null;
	Iterator<Vertex> iterator = null;
	List<Edge> edges = null;
	List<Edge> edgesVisitedDuringDFS = new ArrayList<Edge>();
	protected int numVertices;
	protected int numEdges;
	List<Graph> listGraph = new ArrayList<>();
	List<Vertex> path = new ArrayList<Vertex>();

	public DepthFirstSearch(Graph graph) {
		stack = new Stack<Vertex>();
		this.graph = graph;
		vertices = graph.vertices;
		iterator = vertices.iterator();
		edges = graph.edges;
		numVertices = vertices.size();
		numEdges = edges.size();
	}

	protected void resetVisitedVertices() {
		visitedVertices.clear();
	}

	protected void resetVertexIterator() {
		iterator = vertices.iterator();
	}
	
	public void findPath(Vertex u, Vertex v) {
		stack.push(u);
		visitedVertices.put(u, u);
		while (!stack.isEmpty()) {
			Vertex sv = nextUnvisitedAdjacent(stack.peek());

			if (sv == null) {
				stack.pop();
			} else {
				setHasBeenVisited(sv);
				stack.push(sv);
				if (sv.equals(v)) {
					break;
				}
			}
		}
		path = stack.subList(0, stack.size());
	}

	public void start() {
		while (someVertexUnvisited()) {
			// process vertex and place on stack
			handleInitialVertex();

			// search for deeper nodes and backtrack, in a loop
			// starts on a new component after a component has been completely visited
			singleComponentLoop();

			// override this method if processing needs to occur here
			additionalProcessing();
		}
	}

	protected void additionalProcessing() {
		List<Edge> lstEdge = new ArrayList<Edge>();
		for (Iterator<Entry<Vertex, Vertex>> it = visitedVertices.entrySet().iterator(); it.hasNext();) {
			Map.Entry en = it.next();
			for (Edge ed : edges) {
				if (ed.belongTo((Vertex) en.getKey())) {
					lstEdge.add(ed);
				}
			}
			vertices.remove(en.getKey());
		}
		Graph gh = new Graph(lstEdge.toArray());
		listGraph.add(gh);
		numVertices = numVertices - visitedVertices.size();		
		resetVisitedVertices();
		resetVertexIterator();
	}
	/*
	 * public void cleanup() { //cleanup vertices graph.resetVertexBookkeeping(); }
	 */

	// inserts an initial vertex into the stack as preparation for
	// dfs for this component
	protected void handleInitialVertex() {
		Vertex v = null;
		try {
			v = nextUnvisited();
		} catch(NullPointerException e) {
			
		}
		if (v != null) {
			setHasBeenVisited(v);
			processVertex(v);
			stack.push(v);
		}
	}

	protected void singleComponentLoop() {
		while (!stack.isEmpty()) {
			Vertex v = nextUnvisitedAdjacent(stack.peek());

			if (v == null) {
				Vertex vm = stack.pop();
			}
			else {
				setHasBeenVisited(v);
				processEdge(new Edge(stack.peek(), v));
				processVertex(v);
				stack.push(v);
			}
		}
	}

	protected void processEdge(Edge e) {
		// override for needed functionality
	}

	public boolean getHasBeenVisited(Vertex v) {
		return visitedVertices.containsKey(v);
	}

	public void setHasBeenVisited(Vertex v) {
		visitedVertices.put(v, v);
	}

	protected boolean someVertexUnvisited() {
		return visitedVertices.size() < numVertices;
	}

	protected void processVertex(Vertex w) {
		// should be overridden; by default, do nothing
	}

	public Vertex nextUnvisited() {
		while (iterator.hasNext()) {
			Vertex next = iterator.next();
			if (!visitedVertices.containsKey(next)) {
				return next;
			}
		}
		return null;
	}

	public Vertex nextUnvisitedAdjacent(Vertex v) {
		List<Vertex> listOfAdjacent = graph.getListOfAdjacentVerts(v);
		Iterator<Vertex> it = listOfAdjacent.iterator();
		Vertex retVert = null;
		boolean found = false;
		while (it.hasNext() && !found) {
			Vertex u = it.next();
			if (!visitedVertices.containsKey(u)) {
				found = true;
				retVert = u;
			}
		}
		return retVert;
	}
}
