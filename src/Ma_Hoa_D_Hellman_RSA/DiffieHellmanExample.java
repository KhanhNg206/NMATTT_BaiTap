package D_Hellman_RSA;

import java.math.BigInteger;
import java.security.SecureRandom;

public class DiffieHellmanExample {
    public static void main(String[] args) {
        SecureRandom random = new SecureRandom();

        // Bước 1: Hai bên thống nhất công khai p và g
        BigInteger p = new BigInteger("23"); 
        BigInteger g = new BigInteger("5");  
        // Bước 2: Alice chọn khóa bí mật a
        BigInteger a = new BigInteger(1024, random);
        BigInteger A = g.modPow(a, p); // A = g^a mod p
        // Bước 3: Bob chọn khóa bí mật b
        BigInteger b = new BigInteger(1024, random);
        BigInteger B = g.modPow(b, p); // B = g^b mod p
        // Bước 4: Hai bên trao đổi A và B
        // Bước 5: Tính khóa chung
        BigInteger sharedKeyAlice = B.modPow(a, p); // K = B^a mod p
        BigInteger sharedKeyBob = A.modPow(b, p);   // K = A^b mod p

        System.out.println("p = " + p);
        System.out.println("g = " + g);
        System.out.println("Khóa công khai của Alice (A) = " + A);
        System.out.println("Khóa công khai của Bob (B) = " + B);
        System.out.println("Khóa chung phía Alice: " + sharedKeyAlice);
        System.out.println("Khóa chung phía Bob  : " + sharedKeyBob);
        System.out.println("Khóa chung giống nhau? " + sharedKeyAlice.equals(sharedKeyBob));
    }
}
