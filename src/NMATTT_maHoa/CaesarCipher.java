package NMATTT;

import java.util.Scanner;

public class CaesarCipher {

	 public static String encrypt(String text, int shift) {
	        StringBuilder result = new StringBuilder();

	        for (char c : text.toCharArray()) {
	            if (Character.isLetter(c)) {
	                char base = Character.isUpperCase(c) ? 'A' : 'a';
	                char ch = (char) ((c - base + shift) % 26 + base);
	                result.append(ch);
	            } else {
	                result.append(c); 
	            }
	        }
	        return result.toString();
	    }

	    public static String decrypt(String cipher, int shift) {
	        return encrypt(cipher, 26 - (shift % 26));
	    }

	    public static void main(String[] args) {
	        Scanner sc = new Scanner(System.in);

	        System.out.print("Nhập văn bản: ");
	        String plainText = sc.nextLine();

	        System.out.print("Nhập số shift (key): ");
	        int shift = sc.nextInt();

	        String encrypted = encrypt(plainText, shift);
	        String decrypted = decrypt(encrypted, shift);

	        System.out.println("\nVăn bản gốc  : " + plainText);
	        System.out.println("Mã hóa       : " + encrypted);
	        System.out.println("Giải mã      : " + decrypted);

	        sc.close();
	    }
	}
