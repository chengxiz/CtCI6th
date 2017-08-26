package cracking.ch1;

public class RotateMatrix1_7 {
	private static int[][] Rotate(int[][] mat, int n) {
		int num = n/2 ;
		for (int layer = 0; layer < num; layer++) {
			int first = layer;			// first index of matrix 
			int last = n -1 - layer;	// last index of matrix
			for (int i = first; i < last; i++) {
				int offset = i - first;	// absolute offset from first until at most last
				int temp = mat[first][i];
				mat[first][i] = mat[last - offset][first];			// left to top
				mat[last - offset][first] = mat[last][last-offset];	// bottom to left
				mat[last][last-offset] = mat[first+offset][last];	// right to bottom
				mat[first+offset][last] = temp;						// temp to right
			}
		}
		return mat;

	}
	private static void PrintMatrix(int[][] matrix){
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();		
		}
	}
	public static void main(String[] args) {
		int[][] mat1 = new int[][]{
									{ 20, 18, 22, 20, 16 },
                                 	{ 18, 20, 18, 21, 20 },
                                 	{ 16, 18, 16, 20, 24 },
                                 	{ 25, 24, 22, 24, 25 },
                                 	{ 27, 16, 26, 22, 29 }
                                 };
                                 System.out.println(mat1.length);
         PrintMatrix(mat1);
         System.out.println("Rotation");
         System.out.println((int)5/2);
         PrintMatrix(Rotate(mat1, 5));
         
	}
}
