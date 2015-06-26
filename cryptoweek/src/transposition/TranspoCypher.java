package transposition;

import java.util.List;

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
        int keyLenght = 2;
        boolean continues = true;

        while (keyLenght != encoded.length() && continues) {
            // generatePossibleKeys
            List<Integer[]> keys = generatePossibleKeys(keyLenght);
            //for (int[] key: keys) {
                System.out.println(decode(encoded, key));
            //}
            keyLenght ++;
        }
    }

    private static List<Integer[]> generatePossibleKeys(int keyLenght) {
        //get all the possible keys permutation
        return null;
    }
}
