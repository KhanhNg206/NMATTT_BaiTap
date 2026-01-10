package D_Hellman_RSA;

import java.security.*;
import javax.crypto.Cipher;
import java.util.Base64;

public class RSAExample {
    public static void main(String[] args) throws Exception {
        // Bước 1: Tạo cặp khóa RSA
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048); // độ dài khóa
        KeyPair pair = keyGen.generateKeyPair();
        PublicKey publicKey = pair.getPublic();
        PrivateKey privateKey = pair.getPrivate();

        System.out.println("Khóa công khai (Public Key): " + Base64.getEncoder().encodeToString(publicKey.getEncoded()));
        System.out.println("Khóa bí mật (Private Key): " + Base64.getEncoder().encodeToString(privateKey.getEncoded()));

        // Bước 2: Mã hóa dữ liệu
        String plaintext = "Hello, this is RSA encryption demo!";
        Cipher encryptCipher = Cipher.getInstance("RSA");
        encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedBytes = encryptCipher.doFinal(plaintext.getBytes());
        String encryptedText = Base64.getEncoder().encodeToString(encryptedBytes);
        System.out.println("\nBản mã: " + encryptedText);

        // Bước 3: Giải mã dữ liệu
        Cipher decryptCipher = Cipher.getInstance("RSA");
        decryptCipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedBytes = decryptCipher.doFinal(Base64.getDecoder().decode(encryptedText));
        String decryptedText = new String(decryptedBytes);
        System.out.println("Giải mã: " + decryptedText);
    }
}
