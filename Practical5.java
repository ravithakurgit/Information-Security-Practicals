import java.util.Scanner;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Practical5 {

    public static void main(String[] args) {

        try {

            Scanner sc = new Scanner(System.in);

            // Accept plaintext
            System.out.print("Enter plaintext: ");
            String plaintext = sc.nextLine();

            // Accept secret key
            System.out.print("Enter secret key (8 characters): ");
            String key = sc.nextLine();

            // Validate key
            if (key.length() != 8) {
                System.out.println(
                        "Error: DES key must be exactly 8 characters.");

                sc.close();
                return;
            }

            // Convert String to bytes
            byte[] plaintextBytes = plaintext.getBytes();
            byte[] keyBytes = key.getBytes();

            // Create DES key
            SecretKeySpec secretKey = new SecretKeySpec(keyBytes, "DES");

            // Create DES cipher
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");

            // Initialize encryption
            cipher.init(
                    Cipher.ENCRYPT_MODE,
                    secretKey);

            // Perform encryption
            byte[] ciphertext = cipher.doFinal(plaintextBytes);

            // Display ciphertext
            System.out.println("\n========== ENCRYPTION ==========");

            System.out.println("Plaintext  : " + plaintext);
            System.out.println("Key        : " + key);
            System.out.println(
                    "Ciphertext : "
                            + Arrays.toString(ciphertext));

            sc.close();

        } catch (Exception e) {

            System.out.println("Error: " + e.getMessage());
        }
    }
}