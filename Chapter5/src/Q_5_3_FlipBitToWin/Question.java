package Q_5_3_FlipBitToWin;

public class Question {
	public static int flipBit(int a){
		/* If all 1s, this is already the longest sequence. */
		if (~a == 0) return Integer.BYTES * 8;

		int currentLength = 0;
		int previousLength = 0;
		int maxLength = 1;	// we can always have a sequence of at least one 1
		while (a != 0){
			if((a&1)==1) {	// Current bit is a '1'
				currentLength++;
			} else if ((a & 1) == 0) {
				/* Update previousLength to :
				 * a: 0, if next bit is 0, which means there are two continuous zeros, 
				 * then apply 0 to previous length
				 * b: 1, if next bit is 1, which means this zeros could be fliped and cause a different max length,
				 * then apply current length to previous length
				 */
				previousLength = (a & 2) == 0 ? 0 : currentLength;
				currentLength = 0;
			}
			maxLength = Math.max(previousLength + currentLength + 1, maxLength);
			a >>>= 1;
		}
		return maxLength;
	}
}
