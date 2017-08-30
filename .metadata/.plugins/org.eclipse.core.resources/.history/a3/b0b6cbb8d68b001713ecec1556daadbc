package cracking.ch4;

import java.util.HashMap;

import cracking.Library.TreeNode;

public class Q_4_12_PathsWithSum {
	public static int countPathsWithSum(TreeNode root, int targetSum) {
		return countPathsWithSum(root, targetSum, 0, new HashMap<Integer, Integer>());
	}
	public static int countPathsWithSum(TreeNode node, int targetSum, 
										int runningSum, 
										HashMap<Integer, Integer> pathCount) {
		if (node == null) return 0;

		/* Count paths with sum ending at the current node. */
		runningSum += node.data;
		int sum = runningSum - targetSum;
		/* if the HashTable pathCount has the value of sum, it means that
		 * there is existing running sum y referring 'runningSum' minus
		 * running sum x here refers to 'sum' 
		 * equals to targetSum
		 * then it means in this path there is a path which doesn't start from root has the targetSum
		 */
		int totalPaths = pathCount.getOrDefault(sum,0);

		/* if runningSum equals targetSum, then one additional path starts at root.
		 * Add in this path. */
		if (runningSum == targetSum){
			totalPaths++;
		}

		/* Increment pathCount, recurse, then decrement pathCount. */
		incrementHashTable(pathCount, runningSum, 1);	// Increment pathCount
		totalPaths += countPathsWithSum(node.left, targetSum, runningSum, pathCount);
		totalPaths += countPathsWithSum(node.right, targetSum, runningSum, pathCount);
		incrementHashTable(pathCount, runningSum, -1);	// Decrement pathCount

		return totalPaths;	

	}
	public static void incrementHashTable(HashMap<Integer, Integer> hashTable, int key, int delta){
		int newCount = hashTable.getOrDefault(key,0) + delta;
		if (newCount == 0) { // Remove when zero to reduce space usage
			hashTable.remove(key);			
		} else {
			hashTable.put(key, newCount);
		}
	}
	public static void main(String[] args) {
		int[] a = {3,3,4,5,6,7,8,9,11};
		TreeNode mid = Q_4_2_MiniTree.createMinimalBST(a);		
		System.out.println(countPathsWithSum(mid,12));
	}
}
