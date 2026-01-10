package NMATTT;
import java.util.Base64;
import java.util.Scanner;
import java.security.SecureRandom;
import java.nio.charset.StandardCharsets;

public class OneTimePadFixed {


    public static byte[] generateKey(int length) {
        SecureRandom sr = new SecureRandom();
        byte[] key = new byte[length];
        sr.nextBytes(key);
        return key;
    }

    public static byte[] xorBytes(byte[] a, byte[] b) {
        if (a.length != b.length) {
            throw new IllegalArgumentException("Length mismatch between arrays");
        }
        byte[] out = new byte[a.length];
        for (int i = 0; i < a.length; i++) {
            out[i] = (byte) (a[i] ^ b[i]);
        }
        return out;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in, StandardCharsets.UTF_8.name());

        System.out.print("Nhập văn bản: ");
        String plain = sc.nextLine();

        byte[] plainBytes = plain.getBytes(StandardCharsets.UTF_8);

        if (plainBytes.length == 0) {
            System.out.println("Văn bản rỗng, thoát.");
            sc.close();
            return;
        }

        byte[] key = generateKey(plainBytes.length);

        byte[] cipherBytes = xorBytes(plainBytes, key);

        String keyBase64 = Base64.getEncoder().encodeToString(key);
        String cipherBase64 = Base64.getEncoder().encodeToString(cipherBytes);

        System.out.println("\n--- Kết quả ---");
        System.out.println("Plaintext      : " + plain);
        System.out.println("Key (Base64)   : " + keyBase64);
        System.out.println("Cipher (Base64): " + cipherBase64);

        // --- Giải mã (demo) ---
        byte[] decodedCipher = Base64.getDecoder().decode(cipherBase64);
        byte[] decodedKey = Base64.getDecoder().decode(keyBase64);
        byte[] decryptedBytes = xorBytes(decodedCipher, decodedKey);
        String decrypted = new String(decryptedBytes, StandardCharsets.UTF_8);

        System.out.println("\nGiải mã lại    : " + decrypted);

        sc.close();
    }
}
