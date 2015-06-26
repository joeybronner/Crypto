package transposition;

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
}
