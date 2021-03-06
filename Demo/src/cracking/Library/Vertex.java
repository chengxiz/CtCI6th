package cracking.Library;

public class Vertex {
	public enum State { Unvisited, Visited, Visiting; }
    private Vertex[] adjacent;
    public int adjacentCount;
    private String vertex;
    public State state;
    public Vertex(String vertex, int adjacentLength) {
        this.vertex = vertex;                
        adjacentCount = 0;        
        adjacent = new Vertex[adjacentLength];
    }
    
    public void addAdjacent(Vertex x) {
        if (adjacentCount < adjacent.length) {
            this.adjacent[adjacentCount] = x;
            adjacentCount++;
        } else {
            System.out.print("No more adjacent can be added");
        }
    }
    public Vertex[] getAdjacent() {
        return adjacent;
    }
    public String getVertex() {
        return vertex;
    }
}
