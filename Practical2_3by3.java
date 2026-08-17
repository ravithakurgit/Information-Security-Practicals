import java.util.Scanner;

public class Practical2_3by3 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("3*3 Hill Cipher");
        System.out.println("Enter the Plain Text");
        String text = sc.nextLine().toUpperCase().replace(" ", "");

        System.out.println("Text:" + text);
        String s = padding(text);
        int key[][] = readMatrix();
        printMatrix(key);
        int det = determinant(key);

        System.out.println("Determinant : " + det);

        int inverse = modInverse(det);

        if (inverse == -1) {

            System.out.println("Key Matrix is NOT invertible.");

            return;
        }

        System.out.println("Modular Inverse : " + inverse);
        System.out.println("Padded string is:" + s);
        String cipher = encrypt(s, key);

        System.out.println("Encrypted Text : " + cipher);
        sc.close();

    }

    public static String padding(String text) {

        while (text.length() % 3 != 0) {

            text += "X";

        }

        return text;

    }

    public static int[][] readMatrix() {

        int key[][] = new int[3][3];

        System.out.println("\nEnter 3 x 3 Key Matrix");

        for (int i = 0; i < 3; i++) {

            for (int j = 0; j < 3; j++) {

                System.out.print("Key[" + i + "][" + j + "] : ");

                key[i][j] = sc.nextInt();

            }

        }

        return key;

    }

    public static void printMatrix(int matrix[][]) {

        System.out.println();

        for (int i = 0; i < 3; i++) {

            for (int j = 0; j < 3; j++) {

                System.out.print(matrix[i][j] + " ");

            }

            System.out.println();

        }

    }

    public static int[] convertToVector(String block) {

        int vector[] = new int[3];

        for (int i = 0; i < 3; i++) {

            vector[i] = block.charAt(i) - 'A';

        }

        return vector;

    }

    public static int[] multiplyMatrix(int key[][], int vector[]) {

        int result[] = new int[3];

        for (int i = 0; i < 3; i++) {

            result[i] = 0;

            for (int j = 0; j < 3; j++) {

                result[i] += key[i][j] * vector[j];

            }

            result[i] = result[i] % 26;

        }

        return result;

    }

    public static String encrypt(String text, int key[][]) {

        String cipher = "";

        for (int i = 0; i < text.length(); i += 3) {

            String block = text.substring(i, i + 3);

            int vector[] = convertToVector(block);

            int result[] = multiplyMatrix(key, vector);

            cipher += vectorToText(result);

        }

        return cipher;

    }

    public static String vectorToText(int vector[]) {

        String text = "";

        for (int i = 0; i < 3; i++) {

            text += (char) (vector[i] + 'A');

        }

        return text;

    }

    public static int modInverse(int det) {

        det = det % 26;

        if (det < 0)
            det += 26;

        for (int i = 1; i < 26; i++) {

            if ((det * i) % 26 == 1)
                return i;

        }

        return -1;
    }

    public static int determinant(int key[][]) {

        int det = key[0][0] * (key[1][1] * key[2][2] - key[1][2] * key[2][1])
                - key[0][1] * (key[1][0] * key[2][2] - key[1][2] * key[2][0])
                + key[0][2] * (key[1][0] * key[2][1] - key[1][1] * key[2][0]);

        det = det % 26;

        if (det < 0)
            det += 26;

        return det;
    }
}
