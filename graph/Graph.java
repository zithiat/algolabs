package graph;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

public class Graph {
	Graph spanningTree;
	List<Graph> connectedComponents;
	boolean isBipartite;
	LinkedList<Vertex> vertices = new LinkedList<Vertex>();
	LinkedList<Edge> edges = new LinkedList<Edge>();
	HashMap<Vertex, LinkedList<Vertex>> adjList = new HashMap<Vertex, LinkedList<Vertex>>();
	int oddCycleLength = 0;
	List<Vertex> path;
	/* new public methods */

	public Graph(Object[] inputEdges) {
		HashMap<Vertex, Vertex> dupverts = new HashMap<Vertex, Vertex>();

		for (Object ob : inputEdges) {
			if (ob.getClass() != Edge.class)
				continue;
			Edge e = (Edge) ob;
			// assume no dup edges
			edges.add(e);
			Vertex v1 = e.u;
			Vertex v2 = e.v;
			if (!dupverts.containsKey(v1)) {
				dupverts.put(v1, v1);
				vertices.add(v1);
			}
			if (!dupverts.containsKey(v2)) {
				dupverts.put(v2, v2);
				vertices.add(v2);
			}
			LinkedList<Vertex> l = adjList.get(v1);
			if (l == null) {
				l = new LinkedList<Vertex>();
			}
			l.add(v2);
			adjList.put(v1, l);

			LinkedList<Vertex> l2 = adjList.get(v2);
			if (l2 == null) {
				l2 = new LinkedList<Vertex>();
			}
			l2.add(v1);
			adjList.put(v2, l2);
		}
	}

	public Graph(List<Pair> pairs) {
		HashMap<Vertex, Vertex> dupverts = new HashMap<Vertex, Vertex>();
		HashMap<Edge, Edge> dupedges = new HashMap<Edge, Edge>();
		for (Pair e : pairs) {
			// handle the vertices and edges simultaneously
			Vertex v1 = new Vertex(e.ob1);
			Vertex v2 = new Vertex(e.ob2);
			Edge edge = new Edge(v1, v2);
			if (!dupverts.containsKey(v1)) {
				dupverts.put(v1, v1);
				vertices.add(v1);
			}
			if (!dupverts.containsKey(v2)) {
				dupverts.put(v2, v2);
				vertices.add(v2);
			}
			if (!dupedges.containsKey(edge)) {
				dupedges.put(edge, edge);
				edges.add(edge);
			}

			LinkedList<Vertex> l = adjList.get(v1);
			if (l == null) {
				l = new LinkedList<Vertex>();
			}
			l.add(v2);
			adjList.put(v1, l);

			LinkedList<Vertex> l2 = adjList.get(v2);
			if (l2 == null) {
				l2 = new LinkedList<Vertex>();
			}
			l2.add(v1);
			adjList.put(v2, l2);
		}
	}

	private void loadMajorStructures() {
		FindSpanningTree fst = new FindSpanningTree(this);
		spanningTree = fst.computeSpanningTree();
	}

	public Graph getSpanningTree() {
		if (spanningTree == null) {
			loadMajorStructures();
		}
		return spanningTree;
	}

	public List<Graph> getConnectedComponents() {
		// if null, first compute
		// then return connectedComponents
		if (connectedComponents == null) {
			ConnectedComponentSearch ccs = new ConnectedComponentSearch(this);
			connectedComponents = ccs.computeConnectedComponent();
		}
		return connectedComponents;
	}

	public boolean isConnected() {
		if (connectedComponents != null) 
			if (connectedComponents.size() == 1)
				return true;
		return false;
	}

	public boolean existsPathBetween(Vertex u, Vertex v) {
		FindPath fp = new FindPath(this);
		fp.findPathBetween(u, v);
		this.path = fp.path;
		return fp.path.size() > 0;
	}

	public boolean containsCycle() {
		OddCycle oc = new OddCycle(this);
		return oc.checkOddCycle();
	}

	public List<Vertex> getListOfAdjacentVerts(Vertex v) {
		List<Vertex> theList = adjList.get(v);
		if (theList == null)
			return new LinkedList<Vertex>();
		else
			return theList;
	}

	public boolean areAdjacent(Vertex v, Vertex w) {
		LinkedList<Vertex> l = adjList.get(v);
		if (l == null)
			return false;
		return l.contains(w);
	}

	public String toString() {
		StringBuilder sb = new StringBuilder("Vertices: \n" + " ");
		for (Vertex v : vertices) {
			sb.append(v + " ");
		}
		sb.append("\nEdges:\n" + " ");
		HashMap<String, String> dups = new HashMap<String, String>();
		for (Vertex v : vertices) {
			LinkedList<Vertex> l = adjList.get(v);

			for (Vertex w : l) {
				String edge = v.toString() + "-" + w.toString();
				String edgeRev = reverse(edge);
				if (!dups.containsKey(edge) && !dups.containsKey(edgeRev)) {
					sb.append(edge + " ");
					dups.put(edge, edge);
				}
			}
		}
		return sb.toString();
	}

	private String reverse(String edge) {
		String[] vals = edge.split("-");
		return vals[1] + "-" + vals[0];
	}
	
	public void printAdjList() {
		Iterator<Entry<Vertex, LinkedList<Vertex>>> it = adjList.entrySet().iterator();
		while (it.hasNext()) {
			Entry<Vertex, LinkedList<Vertex>> elem = (Entry<Vertex, LinkedList<Vertex>>) it.next();
			Vertex v = (Vertex) elem.getKey();
			LinkedList<Vertex> ll = (LinkedList<Vertex>) elem.getValue();
			System.out.println(v.toString() + " -> " + ll.toString());
		}
	}
}
