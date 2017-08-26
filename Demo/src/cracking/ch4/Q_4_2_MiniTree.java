package cracking.ch4;
import cracking.Library.*;

public class Q_4_2_MiniTree {
	static TreeNode createMinimalBST(int array[]) {
		return createMinimalBST(array, 0, array.length - 1);
	}

	static TreeNode createMinimalBST(int arr[], int start, int end) {
		if (end < start) {
			return null;
		}
		int mid = (start + end)/2;
		TreeNode n = new TreeNode(arr[mid]);
		n.left = createMinimalBST(arr, start, mid - 1);
		n.right = createMinimalBST(arr, mid + 1, end);
		return n;
	}

	public static void main(String[] args) {
		int[] a = {3,3,4,5,6,7,8,9,11};
		TreeNode mid = createMinimalBST(a);
		System.out.println(mid.data);
	}
}