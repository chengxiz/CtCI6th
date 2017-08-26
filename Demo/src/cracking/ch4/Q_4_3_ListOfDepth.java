package cracking.ch4;

import java.util.ArrayList;
import java.util.LinkedList;

import cracking.Library.TreeNode;

public class Q_4_3_ListOfDepth {
	public static ArrayList<LinkedList<TreeNode>> createLevelLinkedList_DFS(TreeNode root){
		ArrayList<LinkedList<TreeNode>> lists = new ArrayList<LinkedList<TreeNode>>();
		createLevelLinkedList_DFS(root, lists,0);
		return lists;
	}
	public static void createLevelLinkedList_DFS(TreeNode node, ArrayList<LinkedList<TreeNode>> lists, int level){
		if (node == null) return;
		LinkedList<TreeNode> list;
		if (lists.size() == level){
			list = new LinkedList<TreeNode>();
			lists.add(list);
		} else {
			list = lists.get(level);
		}
		list.add(node);
		createLevelLinkedList_DFS(node.left, lists, level + 1);
		createLevelLinkedList_DFS(node.right, lists, level + 1);
	}
	public static ArrayList<LinkedList<TreeNode>> createLevelLinkedList_BFS(TreeNode root) {
		ArrayList<LinkedList<TreeNode>> result = new ArrayList<LinkedList<TreeNode>>();
		/* Visit the root */
		LinkedList<TreeNode> current = new LinkedList<TreeNode>();
		if (root != null) {
			current.add(root);
		}
		while (current.size() > 0){
			result.add(current);
			LinkedList<TreeNode> parents = current; // go to next level
			current = new LinkedList<TreeNode>();
			for (TreeNode parent: parents){
				if (parent.left != null){
					current.add(parent.left);
				}
				if (parent.left != null){
					current.add(parent.right);
				}
			}
		}
		return result;
	}

	public static void main(String[] args) {
		int[] a = {3,3,4,5,6,7,8,9,11};
		TreeNode root = Q_4_2_MiniTree.createMinimalBST(a);
		ArrayList<LinkedList<TreeNode>> lists = createLevelLinkedList_BFS(root);
		System.out.println(lists);
	}
}
