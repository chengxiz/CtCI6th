package Q_5_8_DrawLine;

public class Question {
	public static void drawLine(byte[] screen, int width, int x1, int x2, int y) {
		// let's take an example
		// width = 32
		// x1 = 1
		// x2 = 30
		// y = 1
		int start_offset = x1 % 8;			// start_offset = 1
		// first_full_byte is the first number of full byte with in the line suppose to be 1 and don't need masking
		int first_full_byte = x1 / 8;		// first_full_byte = 0, 
		if (start_offset != 0) {
			first_full_byte++;				//first_full_byte = 1
		}
		// the mask supposes to be like: 011111111 11111111 11111111 11111110
		// then first_full_byte here should be 1,  the second byte in the mask 		

		int end_offset = x2 % 8;			// end_offset = 6
		// the last_full_byte is the last number of full byte with in the line suppose to be 1 and don't need masking
		int last_full_byte = x2 / 8;		// last_full_byte = 3
		if (end_offset != 7) {
			last_full_byte--;				// last_full_byte = 2
		}
		// the mask supposes to be like: 011111111 11111111 11111111 11111110
		// then last_full_byte here should be 2,  the third byte in the mask

		// Set full bytes
		// the screen is changed like : 00000000 11111111 11111111 00000000
		for (int b = first_full_byte; b <= last_full_byte; b++) {
			// 0xFF = 255 = 11111111 (32bits), (byte) 0xFF = -1 =11111111 (8bits)
			screen[(width / 8) * y + b] = (byte) 0xFF;	
		}
		System.out.println(0xFF);
		System.out.println((byte) 0xFF);
		byte start_mask = (byte) (0xFF >> start_offset);		// start_mask = 0111111
		byte end_mask = (byte) ~(0xFF >> (end_offset + 1));		// end_mask = ~01111111 = 11111110
		
		// Set start and end of line
		if ((x1 / 8) == (x2 / 8)) { // If x1 and x2 are in the same byte
			byte mask = (byte) (start_mask & end_mask);
			screen[(width / 8) * y + (x1 / 8)] |= mask;
		} else {
			if (start_offset != 0) {
				int byte_number = (width / 8) * y + first_full_byte - 1;
				screen[byte_number] |= start_mask;
			}
			if (end_offset != 7) {
				int byte_number = (width / 8) * y + last_full_byte + 1;
				screen[byte_number] |= end_mask;
			} 
		}
	}

	public static void printScreen(byte[] screen, int width) {
		int height = screen.length * 8 / width;
		for (int r = 0; r < height; r++) {
			for (int c = 0; c < width; c+=8) {
				byte b = screen[computeByteNum(width, c, r)];
				printByte(b);
			}
			System.out.println("");
		}
	}
	public static int computeByteNum(int width, int x, int y) {
		return (width * y + x) / 8;
	}
	
	public static void printByte(byte b) {
		for (int i = 7; i >= 0; i--) {
			char c = ((b >> i) & 1) == 1 ? '1' : '_';
			System.out.print(c);
		}
	}
	
	
	public static void main(String[] args) {
		int height = 3;
		int width = 8 * 4 ;		
		int r = 1;
		byte[] screen1 = new byte[width * height / 8];
		int c1;
		int c2;
		c1 = 2;
		c2 = 11;
		System.out.println("row: " + r + ": " + c1 + " -> " + c2);
		drawLine(screen1, width, c1, c2, r);		
		printScreen(screen1, width);
		System.out.println("\n\n");

		byte[] screen2 = new byte[width * height / 8];
		c1 = 1;
		c2 = 30;
		System.out.println("row: " + r + ": " + c1 + " -> " + c2);
		drawLine(screen2, width, c1, c2, r);		
		printScreen(screen2, width);
		System.out.println("\n\n");
	}

}
