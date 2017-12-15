// Tom Nguyen
// CSE 373
// TA: Raquel Van Hofwegen
// 2/28/17
// Homework #5


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
     * edge that is already present in the graph, or if either params are null.
     */
    public MyGraph(Collection<Vertex> v, Collection<Edge> e) {
    	if (v == null || e == null || e.contains(null) || v.contains(null)) {
    		throw new IllegalArgumentException();
    	}
    	this.adjacencyList = new HashMap<Vertex, List<Edge>>();
    	for (Vertex curr : v) {
    		this.adjacencyList.put(new Vertex(curr.getLabel()), new ArrayList<Edge>());
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
    
    /**
     * Returns the shortest path from a to b in the graph, or null if there is
     * no such path.  Assumes all edge weights are nonnegative.
     * Uses Dijkstra's algorithm.
     * @param a the starting vertex
     * @param b the destination vertex
     * @return a Path where the vertices indicate the path from a to b in order
     *   and contains a (first) and b (last) and the cost is the cost of 
     *   the path. Returns null if b is not reachable from a.
     * @throws IllegalArgumentException if a or b does not exist.
     */
    public Path shortestPath(Vertex a, Vertex b) {
    	if (!this.adjacencyList.containsKey(a) || !this.adjacencyList.containsKey(b)) {
    		throw new IllegalArgumentException();
    	}
    	Map<Vertex, Integer> runningTotalCost = new HashMap<Vertex, Integer>();
    	Map<Vertex, Boolean> knownVertices = new HashMap<Vertex, Boolean>();
    	Map<Vertex, Vertex> prevVertices = new HashMap<Vertex, Vertex>();
    	for (Vertex v : this.adjacencyList.keySet()) {
    		runningTotalCost.put(v, Integer.MAX_VALUE);
    		knownVertices.put(v, false);
    		prevVertices.put(v, null);
    	}
    	runningTotalCost.put(a, 0);
    	knownVertices.put(a, true);
    	Vertex known = a;
    	while (knownVertices.values().contains(false) && known != null) {
    		knownVertices.put(known, true);
    		edgeCostProcessing(known, runningTotalCost, prevVertices);
    		known = findNextVertex(runningTotalCost, knownVertices);
    	}
    	Vertex curr = prevVertices.get(b);
    	List<Vertex> path = new ArrayList<Vertex>();
    	path.add(new Vertex(b.getLabel()));
    	if (a.equals(b)) {
    		return new Path(path, 0);
    	} else if (curr == null) {
    		return null;
    	}
    	while (!curr.equals(a)) {
    		path.add(0, new Vertex(curr.getLabel()));
    		curr = prevVertices.get(curr);
    	}
    	path.add(0, new Vertex(a.getLabel()));
    	return new Path(path, runningTotalCost.get(b));
    }
    
    /*
     *  Three params of types Vertex, Map<Vertex, Integer>, Map<Vertex, Vertex>.
     *  Updates the running total of each edge cost of each Vertex having a directed edge
     *  from the Vertex param. If the known edge cost is less than the "new" edge cost,
     *  then the running edge cost and optimal path will not be updated.
     */
    private void edgeCostProcessing(Vertex known, Map<Vertex, Integer> totalCost,
    							   Map<Vertex, Vertex> paths) {
    	List<Edge> edges = this.adjacencyList.get(known);
    	for (Edge e : edges) {
    		Vertex curr = e.getDestination();
    		if (totalCost.get(known) + e.getWeight() < totalCost.get(curr)) {
    			totalCost.put(curr, totalCost.get(known) + e.getWeight());
    			paths.put(curr, known);
    		}
    	}
    }
    
    /*
     *  Returns the "unknown" Vertex that currently has the lowest cost to get to.
     *  If the rest of the "unknown" Vertex's are unreachable then returns null.
     *  The two params are a Map<Vertex, Integer> and a Map<Vertex, Boolean>.
     */
    private Vertex findNextVertex(Map<Vertex, Integer> totalCost, 
    							  Map<Vertex, Boolean> knownVertices) {
    	Vertex min = null;
    	Set<Vertex> vertices = new HashSet<Vertex>();
    	for (Vertex v : knownVertices.keySet()) {
    		if (knownVertices.get(v) == false) {
    			vertices.add(v);
    		}
    	}
    	int cost = Integer.MAX_VALUE;
    	for (Vertex v : vertices) {
    		if (cost > totalCost.get(v)) {
    			cost = totalCost.get(v);
    			min = v;
    		}
    	}
    	return min;
    }
}
