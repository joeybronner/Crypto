package vigenere;

public class Vigenere {

    public static void main(String[] args) {

        String input = "NGTUBIF";
        String key = "BC";
        String mode = "decode";

        String output = "";

        //create alphabet table
        char matrix[][] = new char[58][58];

        for (int x = 0; x < 58; x++) {
            for (int y = 0; y < 58; y++) {
                matrix[x][y] = (char) (((x + y) % 58) + 32);
            }
        }

        if ("decode".equals(mode)) {
            for (int i = 0; i < input.length(); i++) {
                for (int x = 0; x < 58; x++) {
                    if (matrix[x][key.charAt(i % key.length()) - 32] == input.charAt(i)) {
                        output += (char) (x + 32);
                        break;
                    }
                }
            }
        } else if ("encode".equals(mode)) {
            for (int i = 0; i < input.length(); i++) {
                output += matrix[input.charAt(i) - 32][key.charAt(i % key.length()) - 32];
            }
        }
        System.out.println("MODE: " + mode);
        System.out.println("KEY: " + key);
        System.out.println("INPUT: " + input);
        System.out.println();
        System.out.print("OUTPUT: " + output);
        System.exit(0);
    }


}
