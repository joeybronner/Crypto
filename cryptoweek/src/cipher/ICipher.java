package cipher;

public interface ICipher {
    String encode(String message, String key);
    String decode(String crypted, String key);
}
