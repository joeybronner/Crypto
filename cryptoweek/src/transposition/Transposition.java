package transposition;

import java.util.ArrayList;
import java.util.List;

public class Transposition {

	static String INPUT;
	static String KEY;
	static int KEY_LENGTH;

	public static void main(String[] args) {
		INPUT = "VOICI UN MESSAGE CLAIR   ";
		KEY = "24013";	
		KEY_LENGTH = KEY.length();

		System.out.println("--- Encoding by transposition");
		String encoded = encode();

		System.out.println("\n\n--- Encoding by transposition");
		String decoded = decode(encoded);


	}

	private static String encode() {
		List<String> blocks = new ArrayList<String>();
		int index = 0;

		// Cut sentence by blocks
		while (index < INPUT.length()) {
			blocks.add(INPUT.substring(index, Math.min(index + KEY_LENGTH,INPUT.length())));
			index += KEY_LENGTH;
		}
		System.out.println("Message: [" + INPUT + "]");

		// Encode
		String encoded = "";
		for (String block : blocks) {
			int i = 0;
			for (char ch : block.toCharArray()) {
				int val = Character.getNumericValue(KEY.charAt(i));
				encoded += block.charAt(val);
				i++;
			}
		}
		System.out.println("Encoded: [" + encoded + "]");
		return encoded;
	}

	private static String decode(String msg) {
		List<String> blocks = new ArrayList<String>();
		int index = 0;

		// Cut sentence by blocks
		while (index < msg.length()) {
			blocks.add(msg.substring(index, Math.min(index + KEY_LENGTH,msg.length())));
			index += KEY_LENGTH;
		}
		System.out.println("Encrypted: [" + msg + "]");

		// Decode
		String decoded = "";
		String KEY_REVERSE = new StringBuilder(KEY).reverse().toString();
					//"24013";
		KEY_REVERSE = "23041";
		
		for (String block : blocks) {
			int i = 0;
			for (char ch : block.toCharArray()) {
				int val = Character.getNumericValue(KEY_REVERSE.charAt(i));
				decoded += block.charAt(val);
				i++;
			}
		}
		System.out.println("Decoded:   [" + decoded + "]");
		return decoded;
	}
/*
	private static String decodeMathieu(String msg) {

		String outputstring = "";
		int stepforDec=0;
		stepforDec= msg.length() / KEY_LENGTH;
		for(int j=0;j<stepforDec;j++){ 
			for(int i=j;i<msg.length();i+=stepforDec){
				outputstring += msg.charAt(i); 
			}
		}
		System.out.println("Decoded:   [" + outputstring + "]");
		return outputstring;
	}
*/

}
