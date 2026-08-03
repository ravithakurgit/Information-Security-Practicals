import java.util.HashMap;
import java.util.Scanner;

public class Practical3 {
    static char[][] matrix = new char[5][5];
    static HashMap<Character, int[]> position = new HashMap<>();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Keyword : ");
        String key = sc.nextLine().toUpperCase();

        generateMatrix(key);
        System.out.println("\nPlayfair Matrix:");

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

        System.out.print("\nEnter Plain Text: ");
        String plain = sc.nextLine();

        plain = prepareText(plain);

        System.out.println("\nPrepared Plain Text : " + plain);

        String cipher = encrypt(plain);

        System.out.println("\nCipher Text : " + cipher);

        System.out.println("Plain Text : " + plain);
        System.out.println("Key : " + key);
        sc.close();
    }

    static void generateMatrix(String key) {
        boolean[] used = new boolean[26];

        key = key.replace("J", "I");

        StringBuilder sb = new StringBuilder();

        for (char c : key.toCharArray()) {
            if (c < 'A' || c > 'Z')
                continue;

            if (!used[c - 'A']) {
                used[c - 'A'] = true;
                sb.append(c);
            }
        }

        for (char c = 'A'; c <= 'Z'; c++) {
            if (c == 'J')
                continue;

            if (!used[c - 'A']) {
                used[c - 'A'] = true;
                sb.append(c);
            }
        }

        int index = 0;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                matrix[i][j] = sb.charAt(index);

                position.put(matrix[i][j], new int[] { i, j });

                index++;
            }
        }
    }

    static String prepareText(String text) {
        // Convert to uppercase
        text = text.toUpperCase();

        // Remove spaces
        text = text.replaceAll("\\s+", "");

        // Replace J with I
        text = text.replace('J', 'I');

        StringBuilder result = new StringBuilder();

        int i = 0;

        while (i < text.length()) {
            char first = text.charAt(i);

            if (i + 1 == text.length()) {
                result.append(first);
                result.append('X');
                break;
            }

            char second = text.charAt(i + 1);

            if (first == second) {
                result.append(first);
                result.append('X');
                i++;
            } else {
                result.append(first);
                result.append(second);
                i += 2;
            }
        }

        return result.toString();
    }

    static String encrypt(String text) {
        StringBuilder cipher = new StringBuilder();

        for (int i = 0; i < text.length(); i += 2) {
            char first = text.charAt(i);
            char second = text.charAt(i + 1);

            int[] pos1 = position.get(first);
            int[] pos2 = position.get(second);

            int row1 = pos1[0];
            int col1 = pos1[1];

            int row2 = pos2[0];
            int col2 = pos2[1];

            // Rule 1: Same Row
            if (row1 == row2) {
                cipher.append(matrix[row1][(col1 + 1) % 5]);
                cipher.append(matrix[row2][(col2 + 1) % 5]);
            }

            // Rule 2: Same Column
            else if (col1 == col2) {
                cipher.append(matrix[(row1 + 1) % 5][col1]);
                cipher.append(matrix[(row2 + 1) % 5][col2]);
            }

            // Rule 3: Rectangle
            else {
                cipher.append(matrix[row1][col2]);
                cipher.append(matrix[row2][col1]);
            }
        }

        return cipher.toString();
    }
}