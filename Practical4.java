
import java.util.Scanner;

public class Practical4 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter plaintext: ");
        String plaintext = sc.nextLine();

        System.out.print("Enter number of rails: ");
        int rails = sc.nextInt();

        String ciphertext = encrypt(plaintext, rails);

        System.out.println("Plaintext: " + plaintext);
        System.out.println("Rails: " + rails);
        System.out.println("Encrypted Text: " + ciphertext);

        String decryptedText = decrypt(ciphertext, rails);

        System.out.println("Decrypted Text: " + decryptedText);

        sc.close();
    }

    static String encrypt(String text, int rails) {

        if (rails == 1) {
            return text;
        }
        StringBuilder[] rail = new StringBuilder[rails];
        for (int i = 0; i < rails; i++) {
            rail[i] = new StringBuilder();
        }

        int row = 0;
        boolean down = true;
        for (int i = 0; i < text.length(); i++) {

            rail[row].append(text.charAt(i));

            if (row == rails - 1) {
                down = false;
            } else if (row == 0) {
                down = true;
            }

            if (down) {
                row++;
            } else {
                row--;
            }
        }

        StringBuilder cipherText = new StringBuilder();

        for (int i = 0; i < rails; i++) {
            cipherText.append(rail[i]);
        }

        return cipherText.toString();
    }

    // Decryption method
    static String decrypt(String cipherText, int rails) {

        // If there is only one rail,
        // no transposition takes place.
        if (rails == 1) {
            return cipherText;
        }
        int length = cipherText.length();

        // Create a 2D array:
        // rows = number of rails
        // columns = length of ciphertext
        char[][] rail = new char[rails][length];
        int row = 0;
        boolean down = true;

        // ==========================================
        // STEP 1: Mark the positions of zig-zag
        // ==========================================

        for (int col = 0; col < length; col++) {

            // Mark the position
            rail[row][col] = '*';

            // If we reach the bottom rail,
            // change direction to upward.
            if (row == rails - 1) {
                down = false;
            }

            // If we reach the top rail,
            // change direction to downward.
            else if (row == 0) {
                down = true;
            }

            // Move to the next rail.
            if (down) {
                row++;
            } else {
                row--;
            }
        }

        // ==========================================
        // STEP 2: Fill the marked positions
        // with ciphertext characters
        // ==========================================

        int index = 0;

        for (int i = 0; i < rails; i++) {

            for (int j = 0; j < length; j++) {

                if (rail[i][j] == '*') {

                    rail[i][j] = cipherText.charAt(index);

                    index++;
                }
            }
        }

        // ==========================================
        // STEP 3: Read the matrix in zig-zag order
        // ==========================================

        StringBuilder plainText = new StringBuilder();

        row = 0;
        down = true;

        for (int col = 0; col < length; col++) {

            // Take the character from current position
            plainText.append(rail[row][col]);

            // Change direction at bottom
            if (row == rails - 1) {
                down = false;
            }

            // Change direction at top
            else if (row == 0) {
                down = true;
            }

            // Move to next rail
            if (down) {
                row++;
            } else {
                row--;
            }
        }

        return plainText.toString();
    }
}