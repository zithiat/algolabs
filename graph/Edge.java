package graph;

public class Edge {
	Vertex u;
	Vertex v;

	public Edge(Vertex u, Vertex v) {
		this.u = u;
		this.v = v;
	}

	public boolean equals(Object ob) {
		if (ob == null)
			return false;
		if (ob.getClass() != getClass())
			return false;
		Edge other = (Edge) ob;
		return (u == other.u && v == other.v) || (u == other.v && v == other.u);
	}

	@Override
	public String toString() {
//		return "Edge [" + u + "-" + v + "]";
		return "[" + u + "-" + v + "]";
	}
	
	public boolean belongTo(Vertex o) {
		if (u.equals(o) || v.equals(o))
			return true;
		return false;
	}
}
