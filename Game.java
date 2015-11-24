package GraphProblems;
import java.util.*;


/**  You’re given a board game which is a row of squares, each labeled with an
 *  integer. This can be represented by a list, e.g. [1, 3, 2, 0, 5, 2, 8, 4, 1]
 *  Given a start position on the board, you “win” by landing on a zero, where
 *  you move by jumping from square to square either left or right the number
 *  of spaces specified on the square you’re currently on. Your task is to
 *  implement the function: def can_win(board, pos): returns True if you can
 *  win the board from that starting pos, False otherwise.
 *  
 *  @Author Adelein Rodriguez
 */  
public class Game{
	
	public static void main(String[] args){
		int[] board = {1, 3, 2, 0, 5, 2, 8, 4, 1};
		System.out.println(Game.canWin(board,0)); // False
		System.out.println(Game.canWin(board,1)); // False
		System.out.println(Game.canWin(board,2)); // False
		System.out.println(Game.canWin(board,3)); // True if src == target vertex
		System.out.println(Game.canWin(board,4)); // False
		System.out.println(Game.canWin(board,5)); // True
		System.out.println(Game.canWin(board,6)); // False
		System.out.println(Game.canWin(board,7)); // True
		System.out.println(Game.canWin(board,8)); // True
	}
	
    /** 
	  * Determines whether it is possible to win the game starting at start pos
	  * 
	  * @param  board array of board moves
      * @param  start start index in the array 0 - board.length
      * @return whether or not you can win the game starting at the given position
	*/  
	public static boolean canWin(int[] board, int start){
		
		Digraph graph = new Digraph(board.length);
		int zeroPos = -1;
		//  [1, 3, 2, 0, 5, 2, 8, 4, 1] 
		for(int i = 0; i < board.length; i++){
			// Find the position of zero in the list
			if(board[i] == 0){
				zeroPos = i;
				continue;
			}
			int v1 = board[i];
			// right child
			int indexOfV2Right = i+board[i];
			// left child
			int indexOfV2Left = i-board[i];
			
			if(indexOfV2Right < board.length){
				graph.addEdge(i, indexOfV2Right);
			}
			if(indexOfV2Left >= 0){
				graph.addEdge(i, indexOfV2Left);
			}	
		}

		PathFinder pathFinder = new PathFinder(graph);
		int target = zeroPos;

		return pathFinder.find(start,target);		
	}		
}


/**
 *  Uses depth first search to find if there is a path from vertex to vertex  
 *
 */
class PathFinder{
	
	// List keeping track of visited nodes
	private boolean[] marked;
	// List maintaining the path to this node
	private int[] parent;
	// Graph to find path in
	private Digraph graph;
		
    /**
     * Initializes arrays to be used for finding a path.
     * 
     * @param  graph Already built graph where we want to find a path
     * @param  srcV target vertex
     * @param  targetV target vertex
     */
	public PathFinder(Digraph graph){
		this.graph = graph;
		int numVertices = graph.adj.length; 		
	}
	
    /**
     * Finds if there is a path from srcV to targetV.
     * Probes the parent array to find if there is a path or not.
     *
     * @param  srcV source vertex
     * @param  targetV target vertex
     * @return whether there is path or not
     */
	public boolean find(int srcV, int targetV){
		if(srcV == targetV){
			return true;
		}
		int numVertices = graph.adj.length;
		marked = new boolean[numVertices];
		parent = new int[numVertices];
		
		// Reset the parents list to -1 since 0 is a valid node number
		for(int i = 0; i < parent.length; i++){
			parent[i] = -1;
		}
	
		// Find a path if there is one
		buildPath(srcV, targetV, marked, parent);
		// If the parent has been initialized it means that there is a path
		// to this node
		return parent[targetV] != -1;
	}
	
    /**
     * Does a depth first search and builds a path from srcV to targetV
     * It fills the parent array with the vertex that serve as a path to targetV
     *
     * @param  srcV source vertex
     * @param  targetV target vertex
     * @param  marked array of visited vertices 
     * @param  parent array of vertices in this path
     * @return whether there is path or not
     */
	private void buildPath(int srcV, int targetV,
			boolean[] marked, int[] parent){
		
		marked[srcV] = true;
		// Iterate through the neighbors
		for (int adjV : graph.adj[srcV]){
			if(!marked[adjV]){
				parent[adjV] = srcV;
				buildPath(adjV,targetV,marked, parent);
			}
			// If we find the target node we are done.
			if(adjV  == targetV) return;
		}
	}	
}




