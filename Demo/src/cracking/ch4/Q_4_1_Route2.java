package cracking.ch4;
import java.util.LinkedList;
import cracking.Library.*;
import cracking.Library.Vertex.State;


public class Q_4_1_Route2 {
	public enum Technique {BFS, DFS};
	public static boolean search(Graph_Q1 g, Vertex start, Vertex end, Technique search) throws Exception {
		if (start == end) return true;
		LinkedList<Vertex> queue = new LinkedList<Vertex>();

		for (Vertex u: g.getVertices()) {
			u.state = State.Unvisited;
		}
		start.state = State.Visiting;
		queue.addLast(start);



		Vertex u;
		while (!queue.isEmpty()) {			
			u = queue.removeFirst();
			if ( u != null) {
				for (Vertex v: u.getAdjacent()) {
					if (v.state == State.Unvisited) {
						if (v == end) {
							return true;
						} else {
							v.state = State.Visiting;
							if (search == Technique.BFS){
								/* to make sure it is Breadth-First */
								queue.addLast(v);
							} else if (search == Technique.DFS){
								/* to make sure it is Depth-First */
								queue.addFirst(v);
							} else {
								throw new Exception(" Please select the search techinque.");
							}
						}
					}
				}
				u.state = State.Visited;
			}
		}
		return false;
	}
	public static boolean search_DFS_Recursion(Graph_Q1 g, Vertex start, Vertex end) {
		if (start == end) return true;

		for (Vertex u: g.getVertices()) {
			u.state = State.Unvisited;
		}
		return search_DFS_Recursion(start,end);
	}
	public static boolean search_DFS_Recursion(Vertex start, Vertex end) {
		boolean found = false;
		start.state =State.Visiting;
		//queue.add(start);
		for (Vertex v : start.getAdjacent()) {
			if (v.state == State.Unvisited)
				/* If there is one matching, then return true */
				found = (found || search_DFS_Recursion(v,end));
		}
		start.state = State.Visited;
		if (start == end) return true;
		return found;
	}

	public static Graph_Q1 createNewGraph() {
		Graph_Q1 g = new Graph_Q1(9);        
		Vertex[] temp = new Vertex[9];

		temp[0] = new Vertex("a", 3);
		temp[1] = new Vertex("b", 2);
		temp[2] = new Vertex("c", 0);
		temp[3] = new Vertex("d", 1);
		temp[4] = new Vertex("e", 3);
		temp[5] = new Vertex("f", 0);
		temp[6] = new Vertex("g", 0);
		temp[7] = new Vertex("h", 0);
		temp[8] = new Vertex("i", 1);
		/* Vertex 0 and Vertex 1 are directed with each other */
		temp[0].addAdjacent(temp[1]);
		temp[1].addAdjacent(temp[0]);
		temp[0].addAdjacent(temp[2]);
		temp[0].addAdjacent(temp[3]);
		temp[3].addAdjacent(temp[4]);
		temp[4].addAdjacent(temp[5]);
		temp[4].addAdjacent(temp[6]);
		temp[4].addAdjacent(temp[7]);
		/* Vertex 1 and Vertex 8 are directed with each other */
		temp[1].addAdjacent(temp[8]);
		temp[8].addAdjacent(temp[1]);
		for (int i = 0; i < 9; i++) {
			g.addVertices((temp[i]));
		}
		return g;
	}
	public static void main(String[] args) throws Exception {
		Graph_Q1 g = createNewGraph();
		Vertex[] n = g.getVertices();
		Vertex start1 = n[1];
		Vertex end1 = n[5];
		System.out.println(search(g, start1, end1, Technique.BFS));
		System.out.println(search(g, start1, end1, Technique.DFS));
		System.out.println(search_DFS_Recursion(g, start1, end1));
		Vertex start2 = n[2];
		Vertex end2 = n[5];
		System.out.println(search(g, start2, end2, Technique.BFS));
		System.out.println(search(g, start2, end2, Technique.DFS));
		System.out.println(search_DFS_Recursion(g, start2, end2));
		Vertex start3 = n[8];
		Vertex end3 = n[6];
		System.out.println(search(g, start3, end3, Technique.BFS));
		System.out.println(search(g, start3, end3, Technique.DFS));
		System.out.println(search_DFS_Recursion(g, start3, end3));
	}
	
}
