package HamBam_Va_ChuKySo;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

public class test {

    public static void main(String[] args) {

        // Dữ liệu cần băm và ký
        String message = "Đây là dữ liệu cần băm và ký số.";

        // 1) Hàm băm SHA-256
        String hashHex = HashUtil.sha256Hex(message);
        System.out.println("Dữ liệu: " + message);
        System.out.println("SHA-256 (hex): " + hashHex);

        // 2) Tạo cặp khóa RSA
        KeyPair keyPair = DigitalSignatureUtil.generateRSAKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();

        // In public key dạng Base64 (tham khảo)
        String pubKeyB64 = Base64.getEncoder().encodeToString(publicKey.getEncoded());
        System.out.println("\nPublic Key (Base64): " + pubKeyB64);

        // 3) Ký dữ liệu
        String signatureBase64 = DigitalSignatureUtil.sign(message.getBytes(), privateKey);
        System.out.println("\nChữ ký (Base64): " + signatureBase64);

        // 4) Kiểm tra chữ ký
        boolean valid = DigitalSignatureUtil.verify(message.getBytes(), signatureBase64, publicKey);
        System.out.println("\nChữ ký hợp lệ? " + valid);

        // 5) Ví dụ ký trên dữ liệu đã băm
        String hashHex2 = HashUtil.sha256Hex(message);
        String sigOnHash = DigitalSignatureUtil.sign(hashHex2.getBytes(), privateKey);
        boolean validOnHash = DigitalSignatureUtil.verify(hashHex2.getBytes(), sigOnHash, publicKey);
        System.out.println("\nKý trên dữ liệu băm, hợp lệ? " + validOnHash);
    }
}
