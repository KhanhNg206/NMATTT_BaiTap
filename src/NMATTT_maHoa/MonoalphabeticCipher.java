package NMATTT;
import java.util.Scanner;

public class MonoalphabeticCipher {

    // Bảng chữ cái gốc
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    // Bảng thay thế (key)
    private static final String KEY = "QWERTYUIOPASDFGHJKLZXCVBNM";

    // Hàm mã hóa
    public static String encrypt(String text) {
        text = text.toUpperCase();
        StringBuilder result = new StringBuilder();

        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                int index = ALPHABET.indexOf(c);
                result.append(KEY.charAt(index));
            } else {
                result.append(c); 
            }
        }
        return result.toString();
    }

    // Hàm giải mã
    public static String decrypt(String cipher) {
        cipher = cipher.toUpperCase();
        StringBuilder result = new StringBuilder();

        for (char c : cipher.toCharArray()) {
            if (Character.isLetter(c)) {
                int index = KEY.indexOf(c);
                result.append(ALPHABET.charAt(index));
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhập văn bản: ");
        String plainText = sc.nextLine();

        String encrypted = encrypt(plainText);
        String decrypted = decrypt(encrypted);

        System.out.println("\nVăn bản gốc  : " + plainText);
        System.out.println("Mã hóa       : " + encrypted);
        System.out.println("Giải mã      : " + decrypted);

        sc.close();
    }
}
