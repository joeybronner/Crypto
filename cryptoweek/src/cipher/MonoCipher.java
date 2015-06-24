package cipher;

import java.util.HashMap;

public class MonoCipher implements ICipher {

	public static final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" /*+ " .,;:\"'"*/;

	public String generateKey(){

		char[] charTable = alphabet.toCharArray();
		int currentIndex = charTable.length, randomIndex;
		char temporaryValue;

		// While there remain elements to shuffle...
		while (0 != currentIndex) {

			// Pick a remaining element...
			randomIndex = (int) Math.floor(Math.random() * currentIndex);
			currentIndex -= 1;

			// And swap it with the current element.
			temporaryValue = charTable[currentIndex];
			charTable[currentIndex] = charTable[randomIndex];
			charTable[randomIndex] = temporaryValue;
		}

		return new String(charTable);
	}

	private HashMap<Character, Character> buildConversionTable(String key, boolean reverse){
		HashMap<Character,Character> table = new HashMap<Character, Character>();
		for(int i=0 ; i < alphabet.length() ; i++){
			if(reverse){
				table.put(key.charAt(i), alphabet.charAt(i));
			}else{
				table.put(alphabet.charAt(i), key.charAt(i));
			}
		}
		return table;
	}

	@Override
	public String encode(String message, String key) {
		HashMap<Character,Character> table = buildConversionTable(key, false);
		StringBuilder stringBuilder = new StringBuilder();

		for (int i = 0 ; i < message.length(); i++){
			stringBuilder.append(table.get(message.charAt(i)));
		}

		return stringBuilder.toString();
	}

	@Override
	public String decode(String crypted, String key) {
		HashMap<Character,Character> table = buildConversionTable(key, true);
		StringBuilder stringBuilder = new StringBuilder();

		for (int i = 0 ; i < crypted.length(); i++){
			stringBuilder.append(table.get(crypted.charAt(i)));
		}

		return stringBuilder.toString();
	}

}
