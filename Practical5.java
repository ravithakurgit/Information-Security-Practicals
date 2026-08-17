import java.util.Scanner;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Practical5 {

    public static void main(String[] args) {

        try {

            Scanner sc = new Scanner(System.in);

            // --------------------------------
            // 1. Accept plaintext
            // --------------------------------

            System.out.print("Enter plaintext: ");
            String plaintext = sc.nextLine();

            // --------------------------------
            // 2. Accept secret key
            // --------------------------------

            System.out.print("Enter secret key (8 characters): ");
            String key = sc.nextLine();

            // --------------------------------
            // 3. Validate key
            // --------------------------------

            if (key.length() != 8) {

                System.out.println(
                        "Error: DES key must be exactly 8 characters.");

                sc.close();
                return;
            }

            // --------------------------------
            // 4. Convert String to bytes
            // --------------------------------

            byte[] plaintextBytes = plaintext.getBytes();

            byte[] keyBytes = key.getBytes();

            // --------------------------------
            // 5. Create DES key
            // --------------------------------

            SecretKeySpec secretKey = new SecretKeySpec(keyBytes, "DES");

            // --------------------------------
            // 6. Create DES cipher
            // --------------------------------

            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");

            // --------------------------------
            // 7. Encryption
            // --------------------------------

            cipher.init(
                    Cipher.ENCRYPT_MODE,
                    secretKey);

            byte[] ciphertext = cipher.doFinal(plaintextBytes);

            System.out.println(
                    "\n========== ENCRYPTION ==========");

            System.out.println(
                    "Plaintext  : " + plaintext);

            System.out.println(
                    "Key        : " + key);

            System.out.println(
                    "Ciphertext : "
                            + Arrays.toString(ciphertext));

            // --------------------------------
            // 8. Decryption
            // --------------------------------

            cipher.init(
                    Cipher.DECRYPT_MODE,
                    secretKey);

            byte[] decryptedBytes = cipher.doFinal(ciphertext);

            String decryptedText = new String(decryptedBytes);

            System.out.println(
                    "\n========== DECRYPTION ==========");

            System.out.println(
                    "Ciphertext : "
                            + Arrays.toString(ciphertext));

            System.out.println(
                    "Decrypted  : "
                            + decryptedText);

            sc.close();

        } catch (Exception e) {

            System.out.println(
                    "Error: " + e.getMessage());
        }
    }
}