package cracking.Library;

public class Graph_Q1 {
	public static int MAX_VERTICES ;
	private Vertex[] vertices;
	public int count;
	public Graph_Q1(int MAX_VERTICES) {
		vertices = new Vertex[MAX_VERTICES];
		count = 0;
    }
	
    public void addVertices(Vertex x) {
		if (count < vertices.length) {
			vertices[count] = x;
			count++;
		} else {
			System.out.print("Graph full");
		}
    }
    
    public Vertex[] getVertices() {
        return vertices;
    }
}
