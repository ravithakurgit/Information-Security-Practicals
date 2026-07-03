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

                System.out.println("2 x 2 Hill Cipher Selected");

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