import java.util.HashMap;
import java.util.Scanner;

public class Practical3 {
    static char[][] matrix = new char[5][5];
    static HashMap<Character, int[]> position = new HashMap<>();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Keyword : ");
        String key = sc.nextLine().toUpperCase();

        System.out.print("Enter Plain Text : ");
        String plain = sc.nextLine().toUpperCase();
        System.out.println("Plain Text : " + plain);
        System.out.println("Key : " + key);
        sc.close();
    }
}