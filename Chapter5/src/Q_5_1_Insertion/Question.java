package Q_5_1_Insertion;

public class Question {
	/* Actually this question aims to update one number by the other rather than insert one to another. */
	public static int updateBit(int n, int m, int i, int j){
		/* take an example: let's use 8 bits for simplicity, 
		 * i = 2, j = 4, the mask will be 11100011

		/* a sequence of all 1s 
		 * bit form: 1111111111111, and the first digit is the sign digit
		 * integer: -1*/
		int allOnes = ~0;

		/* 1s before position j, then 0s. left = 11100000 */
		int left = allOnes << (j + 1);

		/* 1s after position i, right - 00000011 */
		int right = (1 << i) - 1;

		int mask = left | right;

		int shifted_m = m << i;
		int masked_n = n & mask;
		return (shifted_m | masked_n);
	}
	public static void main(String[] args) {
		// n: 92 = 1011100
		// m: 7 = 111
		// i: 1
		// j: 3
		// expected output: 94: 1011110
		System.out.println(updateBit(92,7,1,3));
	}
}
