package dijkstra;

import java.util.*;

public class DijkstraPriorityQueue {

	private int distance[];
	private Set<Integer> settled;
	private PriorityQueue<Vertex> pq;
	private int numVertices;
	List<List<Vertex>> adj;

	public DijkstraPriorityQueue(int size) {
		this.numVertices = size;
		this.distance = new int[numVertices];
		this.settled = new HashSet<Integer>();
		pq = new PriorityQueue<Vertex>(numVertices, new Vertex());
	}
	
	public void dijkstra(List<List<Vertex>> adj, int src) {
		this.adj = adj;
		for (int i = 0; i < numVertices; i++) {
			distance[i] = Integer.MAX_VALUE;
		}

		// Add source vertex to the priority queue
		pq.add(new Vertex(src, 0));

		// Initial distance to the source = 0
		distance[src] = 0;
		while (settled.size() != numVertices) {

			// remove the minimum distance node
			// from the priority queue
			int u = pq.remove().vertex;

			// adding the vertex whose distance is finalized
			settled.add(u);

			neighbours(u);
		}
	}

	private void neighbours(int u) {
		int edgeDistance = -1;
		int newDistance = -1;

		// All the neighbors of v
		for (int i = 0; i < adj.get(u).size(); i++) {
			Vertex v = adj.get(u).get(i);

			// If current node hasn't already been processed
			if (!settled.contains(v.vertex)) {
				edgeDistance = v.cost;
				newDistance = distance[u] + edgeDistance;

				// If new distance is cheaper in cost
				if (newDistance < distance[v.vertex])
					distance[v.vertex] = newDistance;

				// Add the current node to the queue
				pq.add(new Vertex(v.vertex, distance[v.vertex]));
			}
		}
	}

	public static void main(String[] args) {
		int s = 5;
		int src = 0;
		// Adjacency list representation of the connected edges 
        List<List<Vertex> > adj = new ArrayList<List<Vertex>>();
        for (int i = 0; i < s; i++) {
        	List<Vertex> item = new ArrayList<Vertex>();
        	adj.add(item);
        }
        adj.get(0).add(new Vertex(1, 1));
        adj.get(0).add(new Vertex(2, 3));
        adj.get(0).add(new Vertex(3, 2));
        
        adj.get(1).add(new Vertex(2, 4));
        adj.get(1).add(new Vertex(3, 3));
        adj.get(1).add(new Vertex(4, 2));
        
        DijkstraPriorityQueue dpq = new DijkstraPriorityQueue(s);
		dpq.dijkstra(adj, src);
		// Print the shortest path to all the vertices from the source node
		System.out.println("The shorted path from vertex :");
		for (int i = 0; i < dpq.distance.length; i++)
			System.out.println(src + " to " + i + " is " + dpq.distance[i]);

	}
}

class Vertex implements Comparator<Vertex> {
	public int vertex;
	public int cost;

	public Vertex() {
	}

	public Vertex(int v, int cost) {
		this.vertex = v;
		this.cost = cost;
	}

	@Override
	public int compare(Vertex v1, Vertex v2) {
		if (v1.cost < v2.cost)
			return -1;
		if (v1.cost > v2.cost)
			return 1;
		return 0;
	}
}