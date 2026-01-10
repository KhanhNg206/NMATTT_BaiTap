package NMATTT;

import java.util.*;

public class PlayfairCipher {
    private static char[][] matrix = new char[5][5];
    private static Map<Character, int[]> pos = new HashMap<>();

    // Tạo bảng từ khóa
    public static void generateMatrix(String key) {
        key = key.toUpperCase().replaceAll("[^A-Z]", "").replace("J", "I");
        StringBuilder sb = new StringBuilder();

        // Thêm key
        for (char c : key.toCharArray()) {
            if (sb.indexOf(String.valueOf(c)) == -1) {
                sb.append(c);
            }
        }
        // Thêm các chữ cái còn lại
        for (char c = 'A'; c <= 'Z'; c++) {
            if (c == 'J') continue;
            if (sb.indexOf(String.valueOf(c)) == -1) {
                sb.append(c);
            }
        }

        // Đưa vào bảng 5x5
        int index = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                matrix[i][j] = sb.charAt(index);
                pos.put(matrix[i][j], new int[]{i, j});
                index++;
            }
        }
    }

    // Chia văn bản thành cặp
    public static List<String> prepareText(String text) {
        text = text.toUpperCase().replaceAll("[^A-Z]", "").replace("J", "I");
        List<String> pairs = new ArrayList<>();

        int i = 0;
        while (i < text.length()) {
            char a = text.charAt(i);
            char b;
            if (i + 1 < text.length()) {
                b = text.charAt(i + 1);
                if (a == b) {
                    b = 'X'; // chèn X nếu trùng
                    i++;
                } else {
                    i += 2;
                }
            } else {
                b = 'X'; // thêm X nếu lẻ
                i++;
            }
            pairs.add("" + a + b);
        }
        return pairs;
    }

    // Mã hóa
    public static String encrypt(String text) {
        List<String> pairs = prepareText(text);
        StringBuilder result = new StringBuilder();

        for (String pair : pairs) {
            char a = pair.charAt(0);
            char b = pair.charAt(1);

            int[] pa = pos.get(a);
            int[] pb = pos.get(b);

            if (pa[0] == pb[0]) { // cùng hàng
                result.append(matrix[pa[0]][(pa[1] + 1) % 5]);
                result.append(matrix[pb[0]][(pb[1] + 1) % 5]);
            } else if (pa[1] == pb[1]) { // cùng cột
                result.append(matrix[(pa[0] + 1) % 5][pa[1]]);
                result.append(matrix[(pb[0] + 1) % 5][pb[1]]);
            } else { // hình chữ nhật
                result.append(matrix[pa[0]][pb[1]]);
                result.append(matrix[pb[0]][pa[1]]);
            }
        }
        return result.toString();
    }

    // Giải mã
    public static String decrypt(String text) {
        List<String> pairs = prepareText(text);
        StringBuilder result = new StringBuilder();

        for (String pair : pairs) {
            char a = pair.charAt(0);
            char b = pair.charAt(1);

            int[] pa = pos.get(a);
            int[] pb = pos.get(b);

            if (pa[0] == pb[0]) { // cùng hàng
                result.append(matrix[pa[0]][(pa[1] + 4) % 5]);
                result.append(matrix[pb[0]][(pb[1] + 4) % 5]);
            } else if (pa[1] == pb[1]) { // cùng cột
                result.append(matrix[(pa[0] + 4) % 5][pa[1]]);
                result.append(matrix[(pb[0] + 4) % 5][pb[1]]);
            } else { // hình chữ nhật
                result.append(matrix[pa[0]][pb[1]]);
                result.append(matrix[pb[0]][pa[1]]);
            }
        }
        return result.toString();
    }

    // In bảng
    public static void printMatrix() {
        System.out.println("Bảng Playfair:");
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhập khóa (key): ");
        String key = sc.nextLine();
        generateMatrix(key);
        printMatrix();

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
