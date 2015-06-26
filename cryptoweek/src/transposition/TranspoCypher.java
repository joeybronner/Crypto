package transposition;

import java.util.ArrayList;
import java.util.TreeSet;

public class TranspoCypher {
    static int[] key = {2, 4, 0, 1, 3};

    public static String encode(String message) {
        String encoded = "";
        int i=0;
        int limit = key.length - ((int) message.length() % key.length);
        for (int k = 0; k < limit; k++)
            message += " ";
        int length = message.length();
        while (i < length) {
            for(int j= 0; j< key.length; j++) {
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
            for (int j = 0; j <key.length; j++) {
                int index = key[j]+k;
                decoded[index] = encoded.charAt(i);
                i ++;
            }
            k += key.length;
            encoded.substring(key.length);
        }
        String decodedString = "";
        for (Character c: decoded) {
            decodedString += "" + c;
        }
        return decodedString;
    }

    public static void cipher(String encoded) {
        boolean continues = true;
        TreeSet<Integer> sizes = factors(encoded.length());

        for(int size: sizes) {
            int[] r = range(size);
            permute(r, size, list);
            System.out.println("");
        }
    }

    static ArrayList<int[]> list = new ArrayList<int[]>();
    public static int[] range(int stop)
    {
        int[] result = new int[stop];

        for(int i=0;i<stop;i++)
            result[i] = i;

        return result;
    }

    static void permute(int[] a, int k, ArrayList<int[]> list)
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
                permute(a, k + 1, list);
                temp = a[k];
                a[k] = a[i];
                a[i] = temp;
            }
        }
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
}
