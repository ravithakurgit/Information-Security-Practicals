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