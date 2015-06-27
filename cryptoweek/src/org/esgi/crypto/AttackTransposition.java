package org.esgi.crypto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.TreeSet;

public class AttackTransposition implements IExecute {
	static int[] key = {2, 4, 0, 1, 3};


	public static String encode(String message) {
		String encoded = "";
		int i = 0;
		int limit = key.length - ((int) message.length() % key.length);
		for (int k = 0; k < limit; k++)
			message += " ";
		int length = message.length();
		while (i < length) {
			for (int j = 0; j < key.length; j++) {
				encoded += message.charAt(key[j]);
			}
			message = message.substring(key.length);
			i += key.length;
		}
		return encoded;
	}

	public static String decode(String encoded, int[] key) {
		char[] decoded = new char[encoded.length()];
		int i = 0;
		int k = 0;
		int length = encoded.length();
		while (i < length) {
			for (int j = 0; j < key.length; j++) {
				int index = key[j] + k;
				decoded[index] = encoded.charAt(i);
				i++;
			}
			k += key.length;
			encoded.substring(key.length);
		}
		String decodedString = "";
		for (Character c : decoded) {
			decodedString += "" + c;
		}
		return decodedString;
	}

	public static void cipher(String encoded, File filekey, File filedecoded) {
		boolean continues = true;
		TreeSet<Integer> sizes = factors(encoded.length());
		try {
			// Flush content filekey
			PrintWriter writer = new PrintWriter(filekey);
			writer.print("");
			writer.close();

			// Flush content filekey
			PrintWriter writer2;
			writer2 = new PrintWriter(filedecoded);
			writer2.print("");
			writer2.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		for (int size : sizes) {
			if (size <= 10) {
				int[] r = range(size);
				permute(r, 0);
			}
		} 
		for (int[] keys: list) {
			String decoded = decode(encoded, keys);
			try {
				boolean found = findCorrespondenceDictionary(decoded);
				// Write in file
				try
				{
					FileWriter fk = new FileWriter(filekey,true);
					FileWriter fd = new FileWriter(filedecoded,true);
					for (int k : keys) {
						fk.write(k);
					}
					fd.write(decoded);
					fk.close();
					fd.close();
				}
				catch(IOException ioe)
				{
					System.err.println("IOException: " + ioe.getMessage());
				}

				/*if (found) {
					System.out.println(decoded);

					break;
				}*/
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	static ArrayList<int[]> list = new ArrayList<int[]>();
	static void permute(int[] a, int k)
	{
		if (k == a.length)
		{
			list.add(a.clone());
		}
		else
		{
			for (int i = k; i < a.length; i++)
			{
				int temp = a[k];
				a[k] = a[i];
				a[i] = temp;
				permute(a, k + 1);
				temp = a[k];
				a[k] = a[i];
				a[i] = temp;
			}
		}
	}

	public static int[] range(int stop)
	{
		int[] result = new int[stop];

		for(int i=0;i<stop;i++)
			result[i] = i;

		return result;
	}

	public static TreeSet<Integer> factors(int n)
	{
		TreeSet<Integer> factors = new TreeSet<Integer>();
		factors.add(n);
		for(long test = n - 1; test >= Math.sqrt(n); test--)
			if(n % test == 0)
			{
				factors.add((int) test);
				factors.add((int) (n / test));
			}
		return factors;
	}

	public static boolean findCorrespondenceDictionary(String messageDecode) throws IOException {
		boolean possibility = false;

		String ligne;
		String[] mots = messageDecode.split(" ");
		String[] motsDico;
		
		
		BufferedReader r = new BufferedReader(new InputStreamReader(in));

		while ((ligne = r.readLine()) != null) {

			motsDico = ligne.split("#");
			for (String mot: mots) {
				if (mot.equals(motsDico[0])) {
					possibility = true;
				}
			}
		}

		return possibility;
	}

	@Override
	public void execute(File encoded, File key, File decoded) {
		in = getClass().getResourceAsStream("/files/listemot.txt");
		String msg;
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(encoded));
			try {
				StringBuilder sb = new StringBuilder();
				String line = br.readLine();
				while (line != null) {
					sb.append(line);
					sb.append(System.lineSeparator());
					line = br.readLine();
				}
				msg = sb.toString();
			} finally {
				br.close();
			}

			AttackTransposition.cipher(AttackTransposition.encode(msg), key, decoded);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	static InputStream in;
	
	public static void main(String[] args) {
		new AttackTransposition().execute(new File(args[0]), new File(args[0]), new File(args[0]));

	}
	
}
