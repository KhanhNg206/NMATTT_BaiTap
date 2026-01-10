package Des_Aes;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;

public class AESExample {
    public static void main(String[] args) throws Exception {
        // Tạo khóa
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128); // Có thể chọn 128, 192 hoặc 256 bit
        SecretKey secretKey = keyGen.generateKey();

        String plainText = "Xin chao Viet Nam!";
        System.out.println("Văn bản gốc: " + plainText);

        // Mã hóa
        String encryptedText = encrypt(plainText, secretKey);
        System.out.println("Đã mã hóa (AES): " + encryptedText);

        // Giải mã
        String decryptedText = decrypt(encryptedText, secretKey);
        System.out.println("Đã giải mã (AES): " + decryptedText);
    }

    public static String encrypt(String data, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedBytes = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decrypt(String encryptedData, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
        return new String(decryptedBytes);
    }
}
