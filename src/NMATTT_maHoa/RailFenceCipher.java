package NMATTT;

import java.util.Scanner;

public class RailFenceCipher {

    // Mã hóa
    public static String encrypt(String text, int rails) {
        if (rails <= 1) return text;

        StringBuilder[] fence = new StringBuilder[rails];
        for (int i = 0; i < rails; i++) fence[i] = new StringBuilder();

        int rail = 0;
        boolean down = true;

        for (char c : text.toCharArray()) {
            fence[rail].append(c);

            if (rail == 0) {
                down = true;
            } else if (rail == rails - 1) {
                down = false;
            }

            rail += down ? 1 : -1;
        }

        StringBuilder result = new StringBuilder();
        for (StringBuilder row : fence) {
            result.append(row);
        }

        return result.toString();
    }

    // Giải mã
    public static String decrypt(String cipher, int rails) {
        if (rails <= 1) return cipher;

        boolean[][] mark = new boolean[rails][cipher.length()];

        int rail = 0;
        boolean down = true;

        // Đánh dấu vị trí zigzag
        for (int i = 0; i < cipher.length(); i++) {
            mark[rail][i] = true;

            if (rail == 0) {
                down = true;
            } else if (rail == rails - 1) {
                down = false;
            }

            rail += down ? 1 : -1;
        }

        // Điền ciphertext vào zigzag
        char[][] matrix = new char[rails][cipher.length()];
        int index = 0;
        for (int r = 0; r < rails; r++) {
            for (int c = 0; c < cipher.length(); c++) {
                if (mark[r][c] && index < cipher.length()) {
                    matrix[r][c] = cipher.charAt(index++);
                }
            }
        }

        // Đọc lại theo zigzag để giải mã
        StringBuilder result = new StringBuilder();
        rail = 0;
        down = true;
        for (int i = 0; i < cipher.length(); i++) {
            result.append(matrix[rail][i]);

            if (rail == 0) {
                down = true;
            } else if (rail == rails - 1) {
                down = false;
            }

            rail += down ? 1 : -1;
        }

        return result.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhập văn bản: ");
        String plainText = sc.nextLine();

        System.out.print("Nhập số rail (hàng rào): ");
        int rails = sc.nextInt();

        String encrypted = encrypt(plainText, rails);
        String decrypted = decrypt(encrypted, rails);

        System.out.println("\nVăn bản gốc  : " + plainText);
        System.out.println("Mã hóa       : " + encrypted);
        System.out.println("Giải mã      : " + decrypted);

        sc.close();
    }
}
