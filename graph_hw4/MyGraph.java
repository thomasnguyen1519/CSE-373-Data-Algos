// Tom Nguyen
// CSE 373
// TA: Raquel Van Hofwegen
// 2/16/17
// Homework #4


/**
 * A representation of a graph.
 * Assumes that we do not have negative cost edges in the graph.
 */


import java.util.*;


public class MyGraph implements Graph {

    private Map<Vertex, List<Edge>> adjacencyList;

    /**
     * Creates a MyGraph object with the given collection of vertices
     * and the given collection of edges.
     * @param v a collection of the vertices in this graph
     * @param e a collection of the edges in this graph
     * @throws IllegalArgumentException if an edge has a negative cost,
     * or if either of the vertices of the edges are not present in the graph,
     * or if a duplicate directed edge does not have the same weight as the 
     * edge that is presently in the graph.
     */
    public MyGraph(Collection<Vertex> v, Collection<Edge> e) {
    	this.adjacencyList = new HashMap<Vertex, List<Edge>>();
    	for (Vertex curr : v) {
    		if (!adjacencyList.containsKey(v)) {
    			this.adjacencyList.put(new Vertex(curr.getLabel()), new ArrayList<Edge>());
    		}
    	}
    	for (Edge curr : e) {
    		Vertex start = curr.getSource();
    		Vertex destination = curr.getDestination();
    		if (curr.getWeight() < 0 ||
             (this.edgeCost(start,destination) != curr.getWeight() &&
              this.edgeCost(start,destination) != -1)) { 
    			throw new IllegalArgumentException();
    		}
    		this.adjacencyList.get(start).add(new Edge(curr.getSource(), 
    										  curr.getDestination(), curr.getWeight()));
    	}
    }

    /*
     *  Returns a deep copy, Set<Vertex>, of the Vertex objects in the Collection<Vertex>
     *  param.
     */
    private Set<Vertex> deepCopyVertices(Collection<Vertex> v) {
    	Set<Vertex> vertices = new HashSet<Vertex>();
    	for (Vertex curr : v) {
    		Vertex vertCopy = new Vertex(curr.getLabel());
    		vertices.add(vertCopy);
    	}
    	return vertices;
    }
    
    /*
     *  Returns a deep copy, Set<Edge>, of the Edge objects in the Collection<Edge>
     *  param.
     */
    private Set<Edge> deepCopyEdges(Collection<Edge> e) {
    	Set<Edge> edges = new HashSet<Edge>();
    	for (Edge curr : e) {
    		Edge edgeCopy = new Edge(curr.getSource(), curr.getDestination(), 
    								 curr.getWeight());
    		edges.add(edgeCopy);
    	}
    	return edges;
    }
    
    /** 
     * Return the collection of vertices of this graph
     * @return the vertices as a collection (which is anything iterable)
     */
    public Collection<Vertex> vertices() {
    	Collection<Vertex> newVerticesCopy = new HashSet<Vertex>();
    	newVerticesCopy = deepCopyVertices(this.adjacencyList.keySet());
    	return newVerticesCopy;
    }

    /** 
     * Return the collection of edges of this graph
     * @return the edges as a collection (which is anything iterable)
     */
    public Collection<Edge> edges() {
    	Collection<List<Edge>> allListsOfEdges = this.adjacencyList.values();
    	Collection<Edge> edgesCopy = new HashSet<Edge>();
    	for (List<Edge> currList : allListsOfEdges) {
    		Set<Edge> newList = this.deepCopyEdges(currList);
    		for (Edge copy : newList) {
    			edgesCopy.add(copy);
    		}
    	}
    	return edgesCopy;
    }

    /**
     * Return a collection of vertices adjacent to a given vertex v.
     *   i.e., the set of all vertices w where edges v -> w exist in the graph.
     * Return an empty collection if there are no adjacent vertices.
     * @param v one of the vertices in the graph
     * @return an iterable collection of vertices adjacent to v in the graph
     * @throws IllegalArgumentException if v does not exist.
     */
    public Collection<Vertex> adjacentVertices(Vertex v) {
    	if (!this.adjacencyList.containsKey(v)) {
    		throw new IllegalArgumentException();
    	}
    	List<Edge> listOfEdges = this.adjacencyList.get(v);
    	Set<Vertex> listOfAdjVertices = new HashSet<Vertex>();
    	for (Edge curr : listOfEdges) {
    		Vertex oppositeVertex = curr.getDestination();
    		listOfAdjVertices.add(new Vertex(oppositeVertex.getLabel()));
    	}
    	return listOfAdjVertices;
    }

    /**
     * Test whether vertex b is adjacent to vertex a (i.e. a -> b) in a directed graph.
     * Assumes that we do not have negative cost edges in the graph.
     * @param a one vertex
     * @param b another vertex
     * @return cost of edge if there is a directed edge from a to b in the graph, 
     * return -1 otherwise.
     * @throws IllegalArgumentException if a or b do not exist.
     */
    public int edgeCost(Vertex a, Vertex b) {
    	if (!this.adjacencyList.containsKey(a) || !this.adjacencyList.containsKey(b)) {
    		throw new IllegalArgumentException();
    	}
    	List<Edge> edges = this.adjacencyList.get(a);
    	for (Edge curr : edges) {
    		if (curr.getDestination().equals(b)) {
    			return curr.getWeight();
    		}
    	}
    	return -1;
    }
    
}
