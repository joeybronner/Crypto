package cipher;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FrequencyAnalysis 
{
	private static Comparator<Letter> c = new Comparator<Letter>() {
		@Override
		public int compare(Letter a, Letter b) 
		{
			double aF = a.getFrequency();
			double bF = b.getFrequency();

			if (aF < bF)
				return -1;
			else if (aF == bF)
				return 0;
			else
				return 1;
		}

	};

	public static void main(String[] args) throws IOException
	{	
		String ciphertext = "";

		BufferedReader r = new BufferedReader(new FileReader(new File("encrypted-en")));
		while (r.ready())
			ciphertext += r.readLine();
		r.close();

		System.out.println("\n" + decrypt(ciphertext));
	}

	public static String decrypt(String message)
	{
		message = message.toUpperCase();
		char[] charArray = message.toCharArray();

		// Ground truth letters and their frequencies
		List<Letter> letters = initLetters();

		// Figure out frequencies of characters in ciphertext
		List<Letter> cipherLetters = getCipherFrequencies(charArray);

		// The ciphertext --> plaintext alphabet. Keys are cipherletters, values plaintext
		Map<Character, Character> cipherToPlain = new HashMap<Character, Character>();
		for (int i = 0; i < letters.size(); i++)
			cipherToPlain.put(cipherLetters.get(i).getLetter(), letters.get(i).getLetter());

		String plaintext = "";
		for (char ch : charArray)
		{
			if (ch >= 'A' && ch <= 'Z')
				plaintext += cipherToPlain.get(ch);
			else
				plaintext += ch;
		}

		System.out.println("Ciphertext: A B C D E F G H I J K L M N O P Q R S T U V W X Y Z");
		System.out.print("Plaintext : ");
		for (Character entry : cipherToPlain.values())
			System.out.print(entry + " ");
		System.out.println();

		return plaintext;
	}

	private static List<Letter> getCipherFrequencies(char[] charArray)
	{
		// table with i = char's position and frequencies[i] = frequency of char i
		List<Letter> frequencies = new ArrayList<Letter>(Arrays.asList(
				new Letter("A", 0.0), new Letter("B", 0.0),
				new Letter("C", 0.0), new Letter("D", 0.0),
				new Letter("E", 0.0), new Letter("F", 0.0),
				new Letter("G", 0.0), new Letter("H", 0.0),
				new Letter("I", 0.0), new Letter("J", 0.0),
				new Letter("K", 0.0), new Letter("L", 0.0),
				new Letter("M", 0.0), new Letter("N", 0.0),
				new Letter("O", 0.0), new Letter("P", 0.0),
				new Letter("Q", 0.0), new Letter("R", 0.0),
				new Letter("S", 0.0), new Letter("T", 0.0),
				new Letter("U", 0.0), new Letter("V", 0.0),
				new Letter("W", 0.0), new Letter("X", 0.0),
				new Letter("Y", 0.0), new Letter("Z", 0.0)));

		for (char c : charArray)
			if (c >= 'A' && c <= 'Z')
				frequencies.get(c - 'A').incrementFrequency();

		Collections.sort(frequencies, c);

		return frequencies;
	}

	private static List<Letter> initLetters()
	{
		/*
		 * English dictionary.
		 * 
		 * List<Letter> letters = new ArrayList<Letter>(Arrays.asList(
						new Letter("A", 8.167), new Letter("B", 1.492),
						new Letter("C", 2.782), new Letter("D", 4.253),
						new Letter("E", 12.702), new Letter("F", 2.228),
						new Letter("G", 2.015), new Letter("H", 6.094),
						new Letter("I", 6.966), new Letter("J", 0.153),
						new Letter("K", 0.772), new Letter("L", 4.025),
						new Letter("M", 2.406), new Letter("N", 6.749),
						new Letter("O", 7.507), new Letter("P", 1.929),
						new Letter("Q", 0.095), new Letter("R", 5.987),
						new Letter("S", 6.327), new Letter("T", 9.056),
						new Letter("U", 2.758), new Letter("V", 0.978),
						new Letter("W", 2.361), new Letter("X", 0.150),
						new Letter("Y", 1.974), new Letter("Z", 0.074)));*/

		/*
		 * French dictionary.
		 */
		List<Letter> letters = new ArrayList<Letter>(Arrays.asList(
				new Letter("A", 7.265), new Letter("B", 0.893),
				new Letter("C", 3.029), new Letter("D", 3.538),
				new Letter("E", 13.754), new Letter("F", 1.018),
				new Letter("G", 0.972), new Letter("H", 0.810),
				new Letter("I", 7.027), new Letter("J", 0.480),
				new Letter("K", 0.046), new Letter("L", 5.438),
				new Letter("M", 2.743), new Letter("N", 6.671),
				new Letter("O", 5.095), new Letter("P", 2.704),
				new Letter("Q", 1.045), new Letter("R", 6.216),
				new Letter("S", 7.800), new Letter("T", 6.699),
				new Letter("U", 5.697), new Letter("V", 1.398),
				new Letter("W", 0.025), new Letter("X", 0.411),
				new Letter("Y", 0.269), new Letter("Z", 0.116)));

		Collections.sort(letters, c);	

		return letters;
	}

	private static class Letter
	{
		private char letter;
		private double frequency;

		Letter(String l, double f) { 
			letter = l.charAt(0);
			frequency = f;
		}

		public char getLetter() { return letter; }
		public double getFrequency() { return frequency; }
		public void incrementFrequency() { frequency += 1.0; }
	}

}
