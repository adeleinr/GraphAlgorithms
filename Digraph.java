package GraphProblems;

import java.util.ArrayList;

/**
 *  The Digraph class represents a directed graph of vertices where each vertex
 *  is identified by it index in the adjacency list. This is a simple
 *  implementation and assumes that the vertices are integers.
 *  The adjacency list works as follows: at index 0 we find the list of vertices
 *  adjacent to vertex 0, in the example below 0 has a one directional
 *  connection to 2, 3 and 4.
 *  [0] ==> [2,3,4]
 *  [1] ==> [5,2]
 *
 *  @author Adelein Rodriguez
 */
class Digraph {
	// List of lists containing each node and its list of neighbors
	protected ArrayList<Integer> [] adj;
	// We keep track of the number of vertices because we build
	// them while processing the vertices and there is no other way to deduce it.
	protected int numEdges;

    /**
	 * Created an empty adjacency array for storing the neighbors
	 * of each vertex
	 * 
	 * @return the number of vertices in this digraph
	 */
	public Digraph(int numVertices){
		adj = new ArrayList[numVertices];
		for(int i = 0; i < numVertices; i++){
			adj[i] = new ArrayList<Integer>();
		}
	}
	
    /**
     * Adds the directed edge v->w to this digraph.
     *
     * @param  v1 source vertex
     * @param  v2 target vertex
     */
	public void addEdge(int v1, int v2){
		adj[v1].add(v2);
		numEdges++;
	}

    /**
	 * Create a string representation of the graph
	 * 
	 * @return a string representing the graph
	 */
	public String toString(){
		StringBuffer buff = new StringBuffer();
		for(int i = 0; i < adj.length; i++){
			buff.append(i + " => "+adj[i]);
		}
		return buff.toString();
	}
}