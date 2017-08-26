package cracking.ch1;

public class ZeroMatrix1_8 {
	protected static void nullifyRow (int[][]m, int nRow){
		for (int i=0; i<m[0].length; i++){
			m[nRow][i]=0;
		}
	}
	protected static void nullifyColomn (int[][]m, int nColomn){
		for (int j=0; j<m.length; j++){
			m[j][nColomn]=0;
		}
	}
	@SuppressWarnings("unused")
	private static void setZero(int[][] mat){
		boolean rowHasZero = false;
		boolean colomnHasZero = false;
		for(int i=0; i<mat[0].length; i++){
			if (mat[0][i]==0) {
				rowHasZero = true;
				break;
			}
		}
		for(int j=0; j<mat.length; j++){
			if (mat[j][0]==0) {
				colomnHasZero = true;
				break;
			}
		}
		for ( int i = 1; i<mat[0].length; i++){
			for (int j = 1; j<mat.length; j++){
				if (mat[j][i]==0) {
					mat[j][0]=0;
					mat[0][i]=0;
				}
			}
		}
		for ( int i = 1; i<mat[0].length; i++) {
			if (mat[0][i]==0) nullifyColomn(mat,i);
		}
		for (int j = 1; j<mat.length; j++){
			if (mat[j][0]==0) nullifyRow(mat,j);
		}
		if (rowHasZero) nullifyRow(mat,0);
		if (colomnHasZero) nullifyColomn(mat,0);
	}
}
