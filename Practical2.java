import java.util.Scanner;

public class Practical2 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("===== Hill Cipher =====");

        System.out.println("1. Encrypt using 2 x 2 Matrix");
        System.out.println("2. Exit");

        System.out.print("Enter Choice : ");

        int choice = sc.nextInt();

        switch (choice) {

            case 1:

                sc.nextLine();

                System.out.print("Enter Plain Text : ");

                String text = sc.nextLine().toUpperCase();

                int key[][] = new int[2][2];

                System.out.println("Enter 2 x 2 Key Matrix");

                for (int i = 0; i < 2; i++) {

                    for (int j = 0; j < 2; j++) {

                        System.out.print("Key[" + i + "][" + j + "] : ");

                        key[i][j] = sc.nextInt();

                    }

                }

                System.out.println("\nPlain Text = " + text);

                System.out.println("\nKey Matrix");

                for (int i = 0; i < 2; i++) {

                    for (int j = 0; j < 2; j++) {

                        System.out.print(key[i][j] + " ");

                    }

                    System.out.println();

                }
                System.out.println("\nPlaintext to Number Conversion");

                for (int i = 0; i < text.length(); i++) {

                    char ch = text.charAt(i);

                    int value = ch - 'A';

                    System.out.println(ch + " = " + value);

                }
                // Padding if plaintext length is odd

                if (text.length() % 2 != 0) {

                    text = text + "X";

                    System.out.println("\nPlaintext length is odd.");
                    System.out.println("After Padding : " + text);

                }

                System.out.println("\nBlocks");

                for (int i = 0; i < text.length(); i = i + 2) {

                    System.out.println(
                            text.charAt(i) + " " + text.charAt(i + 1));

                }
                System.out.println("\nEncrypted Text");

                String cipher = "";

                for (int i = 0; i < text.length(); i += 2) {

                    int p1 = text.charAt(i) - 'A';
                    int p2 = text.charAt(i + 1) - 'A';

                    int c1 = (key[0][0] * p1 + key[0][1] * p2) % 26;
                    int c2 = (key[1][0] * p1 + key[1][1] * p2) % 26;

                    cipher = cipher + (char) (c1 + 'A');
                    cipher = cipher + (char) (c2 + 'A');
                }

                System.out.println(cipher);

                break;

            case 2:

                System.out.println("Program Ended");

                break;

            default:

                System.out.println("Invalid Choice");

        }

        sc.close();

    }

}