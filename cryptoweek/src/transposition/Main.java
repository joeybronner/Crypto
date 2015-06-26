package transposition;

public class Main {
    public static void main(String[] args) {
        System.out.println("VOICI UN MESSAGE CLAIR");
        System.out.println(TranspoCypher.encode("VOICI UN MESSAGE CLAIR"));
        System.out.println(TranspoCypher.decode(TranspoCypher.encode("VOICI UN MESSAGE CLAIR"), TranspoCypher.key));
        TranspoCypher.cipher(TranspoCypher.encode("VOICI UN MESSAGE CLAIR"));
    }
}