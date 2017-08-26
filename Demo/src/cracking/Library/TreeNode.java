package cracking.Library;

public class TreeNode {
	public int data;
	public TreeNode left, right, parent;
	private int size = 0;

	public TreeNode(int d) {
		data = d;
		size = 1;
	}
	public void insertInOrder(int d) {
		if (d <= data) {
			if (left == null){
				setLeftChild(new TreeNode(d));
			} else {
				left.insertInOrder(d);
			}
		} else {
			if (right == null){
				setRightChild(new TreeNode(d));
			} else {
				right.insertInOrder(d);
			}
		}
		size++;
	}
	public int size() {
		return size;
	}
	public TreeNode find (int d){
		if (d == data) {
			return this;
		} else if (d < data) {
			return this.left ==null? null : left.find(d);
		} else if (d > data) {
			return this.right ==null? null : right.find(d);
		}
		return null;
	}
	public void setLeftChild(TreeNode left) {
		this.left = left;
		if (left != null) {
			left.parent = this;
		}
	}

	public void setRightChild(TreeNode right) {
		this.right = right;
		if(right != null) {
			right.parent = this;
		}
	}
}
