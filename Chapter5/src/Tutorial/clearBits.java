package Tutorial;

public class clearBits {
	public static int clearBitsIThrough0(int num, int i) {
		int mask = (-1 << (i+1));
		return num & mask;
	}
	public static void main(String[] args) {
		System.out.println(clearBitsIThrough0(92,2));
	}
}
