import java.util.Scanner;
//import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Practical5 {

    // DES Encryption
    static byte[] encrypt(byte[] plaintext, byte[] key) throws Exception {

        SecretKeySpec secretKey = new SecretKeySpec(key, "DES");

        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");

        cipher.init(
                Cipher.ENCRYPT_MODE,
                secretKey);

        return cipher.doFinal(plaintext);
    }

    // DES Decryption
    static byte[] decrypt(byte[] ciphertext, byte[] key) throws Exception {

        SecretKeySpec secretKey = new SecretKeySpec(key, "DES");

        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");

        cipher.init(
                Cipher.DECRYPT_MODE,
                secretKey);

        return cipher.doFinal(ciphertext);
    }

    // Change exactly ONE bit
    static byte[] changeOneBit(byte[] data) {

        // Make a copy so original data is not changed
        byte[] modifiedData = data.clone();

        // Change the least significant bit of first byte
        modifiedData[0] = (byte) (modifiedData[0] ^ 1);

        return modifiedData;
    }

    // Count number of changed bits
    static int countDifferentBits(byte[] a, byte[] b) {

        int count = 0;

        for (int i = 0; i < a.length; i++) {

            // XOR finds the different bits
            int xor = (a[i] ^ b[i]) & 0xFF;

            // Count number of 1s
            count += Integer.bitCount(xor);
        }

        return count;
    }

    // Convert bytes to hexadecimal
    static String bytesToHex(byte[] bytes) {

        StringBuilder hex = new StringBuilder();

        for (byte b : bytes) {

            hex.append(
                    String.format("%02X", b));
        }

        return hex.toString();
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {

            // ==========================================
            // STEP 1: Accept Plaintext
            // ==========================================

            System.out.print("Enter plaintext: ");
            String plaintext = sc.nextLine();

            // ==========================================
            // STEP 2: Accept Secret Key
            // ==========================================

            System.out.print(
                    "Enter secret key (exactly 8 characters): ");

            String key = sc.nextLine();

            // ==========================================
            // STEP 3: Validate Key
            // ==========================================

            if (key.length() != 8) {

                System.out.println(
                        "Error: DES key must be exactly 8 characters.");

                return;
            }

            // ==========================================
            // STEP 4: Convert String to Bytes
            // ==========================================

            byte[] plaintextBytes = plaintext.getBytes();

            byte[] keyBytes = key.getBytes();

            // ==========================================
            // STEP 5: DES Encryption
            // ==========================================

            byte[] ciphertext = encrypt(
                    plaintextBytes,
                    keyBytes);

            System.out.println(
                    "\n========== DES ENCRYPTION ==========");

            System.out.println(
                    "Plaintext  : " + plaintext);

            System.out.println(
                    "Secret Key : " + key);

            System.out.println(
                    "Ciphertext : "
                            + bytesToHex(ciphertext));

            // ==========================================
            // STEP 6: DES Decryption
            // ==========================================

            byte[] decryptedBytes = decrypt(
                    ciphertext,
                    keyBytes);

            String decryptedText = new String(decryptedBytes);

            System.out.println(
                    "\n========== DES DECRYPTION ==========");

            System.out.println(
                    "Ciphertext : "
                            + bytesToHex(ciphertext));

            System.out.println(
                    "Decrypted  : "
                            + decryptedText);

            // ==========================================
            // STEP 7: Create Modified Plaintext
            // ==========================================

            byte[] modifiedPlaintext = changeOneBit(plaintextBytes);

            // ==========================================
            // STEP 8: Encrypt Modified Plaintext
            // ==========================================

            byte[] modifiedCiphertext = encrypt(
                    modifiedPlaintext,
                    keyBytes);

            System.out.println(
                    "\n========== AVALANCHE EFFECT ==========");

            System.out.println(
                    "Original Ciphertext : "
                            + bytesToHex(ciphertext));

            System.out.println(
                    "Modified Ciphertext : "
                            + bytesToHex(modifiedCiphertext));

            // ==========================================
            // STEP 9: Count Changed Bits
            // ==========================================

            int differentBits = countDifferentBits(
                    ciphertext,
                    modifiedCiphertext);

            // ==========================================
            // STEP 10: Calculate Total Bits
            // ==========================================

            int totalBits = ciphertext.length * 8;

            // ==========================================
            // STEP 11: Calculate Avalanche Percentage
            // ==========================================

            double avalanchePercentage = ((double) differentBits / totalBits)
                    * 100;

            // ==========================================
            // STEP 12: Display Result
            // ==========================================

            System.out.println(
                    "\nChanged bits : "
                            + differentBits);

            System.out.println(
                    "Total bits   : "
                            + totalBits);

            System.out.printf(
                    "Avalanche Effect : %.2f%%%n",
                    avalanchePercentage);

            System.out.println(
                    "\nOne bit of plaintext was changed.");

            System.out.println(
                    "The ciphertext changed significantly.");

        } catch (Exception e) {

            System.out.println(
                    "Error: " + e.getMessage());

        } finally {

            sc.close();
        }
    }
}