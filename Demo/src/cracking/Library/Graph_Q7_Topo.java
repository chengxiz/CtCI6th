package cracking.Library;
import java.util.ArrayList;
import java.util.HashMap;
public class Graph_Q7_Topo {
	private ArrayList<ProjectTopo> projects = new ArrayList<ProjectTopo>();
	private HashMap<String,ProjectTopo> map = new HashMap<String, ProjectTopo>();

	public ProjectTopo getOrCreateProject(String name){
		if(!map.containsKey(name)){
			ProjectTopo p = new ProjectTopo(name);
			map.put(name, p);
			projects.add(p);
		}
		return map.get(name);
	}

	public void addEdge(String startname, String endname){
		ProjectTopo start = getOrCreateProject(startname);
		ProjectTopo end = getOrCreateProject(endname);
		start.addNeighbor(end);
	}
	public ArrayList<ProjectTopo> getProjects(){
		return projects;
	}
}
