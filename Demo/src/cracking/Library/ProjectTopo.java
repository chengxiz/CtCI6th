package cracking.Library;
import java.util.ArrayList;
import java.util.HashMap;

public class ProjectTopo {
	private String name;
	private ArrayList<ProjectTopo> children = new ArrayList<ProjectTopo>();
	private int numDependencies;
	private HashMap<String,ProjectTopo> map= new HashMap<String, ProjectTopo>();
	public String getName() {
		return name;
	}
	public int getNumberDependencies() {
		return numDependencies;
	}
	public ArrayList<ProjectTopo> getChildren() {
		return children;
	}

	public ProjectTopo(String n){
		name =n;
	}
	public void addNeighbor(ProjectTopo p){
		if(!map.containsKey(p.getName())){
			map.put(p.getName(), p);
			children.add(p);
			p.increaseDependencies();
		}
	}

	public void increaseDependencies(){
		this.numDependencies++;
	}
	public void decreaseDependencies(){
		this.numDependencies--;
	}	
}
