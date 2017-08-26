package cracking.ch4;
import java.util.ArrayList;
import java.util.Stack;

import cracking.Library.*;

public class Q_4_7_BuildOrder {
	private static ProjectTopo[] findBuildOrderTopo(String[] projects, String[][] dependencies) {
		Graph_Q7_Topo graph = buildGraphTopo(projects, dependencies);
		return orderProjectsTopo(graph.getProjects());	
	}


	/* Return a list of the projects a correct build order. */
	public static ProjectTopo[] orderProjectsTopo(ArrayList<ProjectTopo> projects) {
		ProjectTopo[] order = new ProjectTopo[projects.size()];

		/* Add "roots" to the build order first. */
		int endOfList = addNonDependent(order, projects, 0);

		int toBeProcessed = 0;
		while (toBeProcessed < order.length) {
			ProjectTopo current = order[toBeProcessed];

			/* We have a circular dependency since there are no remaining projects with
			 * zero dependencies. */
			if(current==null){
				return null;
			}
			/* Remove myself as a dependency. */
			ArrayList<ProjectTopo> children = current.getChildren();
			for(ProjectTopo child: children) {
				child.decreaseDependencies();
			}

			/* Add children that have no one depending on them. */
			endOfList = addNonDependent(order, children, endOfList);
			toBeProcessed++;
		}
		return order;
	}

	private static Stack<ProjectDFS> findBuildOrderDFS(String[] projects, String[][] dependencies) {
		Graph_Q7_DFS graph = buildGraphDFS(projects, dependencies);
		return orderProjectsDFS(graph.getProjects());	
	}
	 /* Find a correct build order */
	public static Stack<ProjectDFS> orderProjectsDFS(ArrayList<ProjectDFS> projects) {
		Stack<ProjectDFS> stack = new Stack<ProjectDFS>();
		for (ProjectDFS project: projects) {
			if (project.getState() == ProjectDFS.State.BLANK){
				if (!doDFS(project, stack)){
					return null;
				}
			}
		}
		return stack;
	}
	private static boolean doDFS(ProjectDFS project, Stack<ProjectDFS> stack) {
		/* It means the state of the project is changed to partial first, 
		 * and before it becomes complete it is visited again, 
		 * which means it is a cycle */
		if(project.getState() == ProjectDFS.State.PARTIAL){
			return false;
		}
		if(project.getState() == ProjectDFS.State.BLANK){
			project.setState(ProjectDFS.State.PARTIAL);
			ArrayList<ProjectDFS> children = project.getChildren();
			for (ProjectDFS child : children) {
				if(!doDFS(child,stack)){
					return false;
				}
			}
			project.setState(ProjectDFS.State.COMPLETE);
			stack.push(project);
		}
		/* In case the Sate is COMPLETE */
		return true;
	}

	/* A helper function to insert projects with zero dependencies into the order 
	 * array, starting at index offset */
	public static int addNonDependent(ProjectTopo[] order, ArrayList<ProjectTopo> projects, int offset){
		for (ProjectTopo project : projects) {
			if (project.getNumberDependencies() == 0){
				order[offset] = project;
				offset++;
			}
		}
		return offset;
	}


	public static Graph_Q7_Topo buildGraphTopo(String[] projects, String[][] dependencies) {
		Graph_Q7_Topo graph = new Graph_Q7_Topo();
		for (String project: projects){
			graph.getOrCreateProject(project);
		}

		for(String[] dependency: dependencies) {
			String first = dependency[0];
			String second = dependency[1];
			graph.addEdge(first,second);
		}
		return graph;
	}
	public static Graph_Q7_DFS buildGraphDFS(String[] projects, String[][] dependencies) {
		Graph_Q7_DFS graph = new Graph_Q7_DFS();
		for (String project: projects){
			graph.getOrCreateProject(project);
		}

		for(String[] dependency: dependencies) {
			String first = dependency[0];
			String second = dependency[1];
			graph.addEdge(first,second);
		}
		return graph;
	}
	public static void main(String[] args) {
		String[] s = {"a","b","c","d","e","f","g"};
		String[][] d = {{"f","c"},{"f","b"},{"f","a"},{"c","a"},{"b","a"},{"b","e"},{"a","e"},{"d","g"}};
		ProjectTopo[] answer1 = findBuildOrderTopo(s,d);
		System.out.println("Topo");
		for(ProjectTopo i : answer1){
			System.out.println(i.getName());
		}
		Stack<ProjectDFS> answer2 = findBuildOrderDFS(s,d);
		System.out.println("DFS");
		while (!answer2 .isEmpty()){
			System.out.println(answer2.pop().getName());
		}
	}
}
