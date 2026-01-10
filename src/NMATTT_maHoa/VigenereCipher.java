package NMATTT;

import java.util.Scanner;

public class VigenereCipher {

    // Hàm mã hóa
    public static String encrypt(String text, String key) {
        StringBuilder result = new StringBuilder();
        text = text.toUpperCase();
        key = key.toUpperCase();

        int keyIndex = 0;
        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                int shift = key.charAt(keyIndex) - 'A';
                char ch = (char) ((c - 'A' + shift) % 26 + 'A');
                result.append(ch);

                keyIndex = (keyIndex + 1) % key.length(); 
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    // Hàm giải mã
    public static String decrypt(String cipher, String key) {
        StringBuilder result = new StringBuilder();
        cipher = cipher.toUpperCase();
        key = key.toUpperCase();

        int keyIndex = 0;
        for (char c : cipher.toCharArray()) {
            if (Character.isLetter(c)) {
                int shift = key.charAt(keyIndex) - 'A';
                char ch = (char) ((c - 'A' - shift + 26) % 26 + 'A');
                result.append(ch);

                keyIndex = (keyIndex + 1) % key.length();
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

        System.out.print("Nhập khóa (key): ");
        String key = sc.nextLine();

        String encrypted = encrypt(plainText, key);
        String decrypted = decrypt(encrypted, key);

        System.out.println("\nVăn bản gốc  : " + plainText);
        System.out.println("Mã hóa       : " + encrypted);
        System.out.println("Giải mã      : " + decrypted);

        sc.close();
    }
}
