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

		BufferedReader r = new BufferedReader(new FileReader(new File("files/encrypted-fr")));
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
			plaintext += cipherToPlain.get(ch);
		}

		System.out.println("Ciphertxt :   : . ; , ' \" A B C D E F G H I J K L M N O P Q R S T U V W X Y Z");
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
				new Letter(" ", 0.000), new Letter(":", 0.000),
				new Letter(".", 0.000), new Letter(";", 0.000),
				new Letter(",", 0.000), new Letter("'", 0.000), 
				new Letter("\"", 0.000),new Letter("A", 0.000), new Letter("B", 0.000),
				new Letter("C", 0.000), new Letter("D", 0.000),
				new Letter("E", 0.000), new Letter("F", 0.000),
				new Letter("G", 0.000), new Letter("H", 0.000),
				new Letter("I", 0.000), new Letter("J", 0.000),
				new Letter("K", 0.000), new Letter("L", 0.000),
				new Letter("M", 0.000), new Letter("N", 0.000),
				new Letter("O", 0.000), new Letter("P", 0.000),
				new Letter("Q", 0.000), new Letter("R", 0.000),
				new Letter("S", 0.000), new Letter("T", 0.000),
				new Letter("U", 0.000), new Letter("V", 0.000),
				new Letter("W", 0.000), new Letter("X", 0.000),
				new Letter("Y", 0.000), new Letter("Z", 0.000)));

		for (char c : charArray) {
			for (int i=0;i < frequencies.size();i++)
			{
				if (frequencies.get(i).getLetter() == c) {
					frequencies.get(i).incrementFrequency();
				}
			}
		}

		Collections.sort(frequencies, c);

		return frequencies;
	}

	private static List<Letter> initLetters()
	{
		/*
		 * French dictionary.
		 */
		List<Letter> letters = new ArrayList<Letter>(Arrays.asList(
				new Letter(" ", 1.560), new Letter(":", 0.330),
				new Letter(".", 0.830), new Letter(";", 0.040),
				new Letter(",", 1.020), new Letter("'", 0.760), new Letter("\"", 0.080),
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
