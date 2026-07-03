import java.util.Scanner;

public class Practical1 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int choice;

        do {

            System.out.println("\n===== MENU =====");
            System.out.println("1. Additive Cipher");
            System.out.println("2. Multiplicative Cipher");
            System.out.println("3. Affine Cipher");
            System.out.println("4. Exit");

            System.out.print("Enter Choice : ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:

                    sc.nextLine();

                    System.out.print("Enter Plain Text : ");
                    String text = sc.nextLine();

                    System.out.print("Enter Key : ");
                    int key = sc.nextInt();

                    String encrypted = additiveEncrypt(text, key);

                    System.out.println("Encrypted = " + encrypted);

                    String decrypted = additiveDecrypt(encrypted, key);

                    System.out.println("Decrypted = " + decrypted);

                    break;
                case 2:

                    sc.nextLine();

                    System.out.print("Enter Plain Text : ");
                    String text2 = sc.nextLine();

                    System.out.print("Enter Key : ");
                    int key2 = sc.nextInt();

                    if (gcd(key2, 26) != 1) {

                        System.out.println("Invalid Key! Key must be coprime with 26.");
                        break;
                    }

                    String encrypted2 = multiplicativeEncrypt(text2, key2);

                    System.out.println("Encrypted = " + encrypted2);

                    String decrypted2 = multiplicativeDecrypt(encrypted2, key2);

                    System.out.println("Decrypted = " + decrypted2);

                    break;
                case 3:

                    sc.nextLine();

                    System.out.print("Enter Plain Text : ");
                    String text3 = sc.nextLine();

                    System.out.print("Enter Multiplication Key (a): ");
                    int a = sc.nextInt();

                    if (gcd(a, 26) != 1) {

                        System.out.println("Invalid Multiplication Key");

                        break;

                    }

                    System.out.print("Enter Addition Key (b): ");

                    int b = sc.nextInt();

                    String encrypted3 = affineEncrypt(text3, a, b);

                    System.out.println("Encrypted = " + encrypted3);

                    String decrypted3 = affineDecrypt(encrypted3, a, b);

                    System.out.println("Decrypted = " + decrypted3);

                    break;
                case 4:
                    System.out.println("Program Ended");
                    break;

                default:
                    System.out.println("Invalid Choice");
            }

        } while (choice != 4);

        sc.close();
    }

    public static String additiveEncrypt(String text, int key) {

        String result = "";

        text = text.toUpperCase();

        for (int i = 0; i < text.length(); i++) {

            char ch = text.charAt(i);

            if (ch >= 'A' && ch <= 'Z') {

                char encrypt = (char) ((ch - 'A' + key) % 26 + 'A');

                result += encrypt;
            } else {

                result += ch;
            }
        }

        return result;
    }

    public static String additiveDecrypt(String text, int key) {

        String result = "";

        text = text.toUpperCase();

        for (int i = 0; i < text.length(); i++) {

            char ch = text.charAt(i);

            if (ch >= 'A' && ch <= 'Z') {

                char decrypt = (char) ((ch - 'A' - key + 26) % 26 + 'A');

                result += decrypt;
            } else {

                result += ch;
            }
        }

        return result;
    }

    public static String multiplicativeEncrypt(String text, int key) {

        String result = "";

        text = text.toUpperCase();

        for (int i = 0; i < text.length(); i++) {

            char ch = text.charAt(i);

            if (ch >= 'A' && ch <= 'Z') {

                char encrypt = (char) (((ch - 'A') * key) % 26 + 'A');

                result += encrypt;

            } else {

                result += ch;

            }

        }

        return result;

    }

    public static String multiplicativeDecrypt(String text, int key) {

        String result = "";

        int inverse = multiplicativeInverse(key);

        if (inverse == -1) {

            return "Invalid Key";

        }

        text = text.toUpperCase();

        for (int i = 0; i < text.length(); i++) {

            char ch = text.charAt(i);

            if (ch >= 'A' && ch <= 'Z') {

                char decrypt = (char) (((ch - 'A') * inverse) % 26 + 'A');

                result += decrypt;

            } else {

                result += ch;

            }

        }

        return result;

    }

    public static int gcd(int a, int b) {

        while (b != 0) {

            int temp = b;
            b = a % b;
            a = temp;

        }

        return a;
    }

    public static int multiplicativeInverse(int key) {

        for (int i = 1; i < 26; i++) {

            if ((key * i) % 26 == 1) {

                return i;

            }

        }

        return -1;
    }

    public static String affineEncrypt(String text, int a, int b) {

        String result = "";

        text = text.toUpperCase();

        for (int i = 0; i < text.length(); i++) {

            char ch = text.charAt(i);

            if (ch >= 'A' && ch <= 'Z') {

                char encrypt = (char) ((((ch - 'A') * a + b) % 26) + 'A');

                result += encrypt;

            }

            else {

                result += ch;

            }

        }

        return result;

    }

    public static String affineDecrypt(String text, int a, int b) {

        String result = "";

        int inverse = multiplicativeInverse(a);

        if (inverse == -1) {

            return "Invalid Key";

        }

        text = text.toUpperCase();

        for (int i = 0; i < text.length(); i++) {

            char ch = text.charAt(i);

            if (ch >= 'A' && ch <= 'Z') {

                int value = (inverse * ((ch - 'A' - b + 26)) % 26);

                char decrypt = (char) (value + 'A');

                result += decrypt;

            }

            else {

                result += ch;

            }

        }

        return result;

    }
}