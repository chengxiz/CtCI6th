package cracking.Library;
import java.util.ArrayList;
import java.util.HashMap;
public class Graph_Q7_DFS {
	private ArrayList<ProjectDFS> projects = new ArrayList<ProjectDFS>();
	private HashMap<String,ProjectDFS> map = new HashMap<String, ProjectDFS>();

	public ProjectDFS getOrCreateProject(String name){
		if(!map.containsKey(name)){
			ProjectDFS p = new ProjectDFS(name);
			map.put(name, p);
			projects.add(p);
		}
		return map.get(name);
	}

	public void addEdge(String startname, String endname){
		ProjectDFS start = getOrCreateProject(startname);
		ProjectDFS end = getOrCreateProject(endname);
		start.addNeighbor(end);
	}
	public ArrayList<ProjectDFS> getProjects(){
		return projects;
	}
}
