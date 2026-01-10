package HamBam_Va_ChuKySo;

import java.security.*;
import java.util.Base64;

public class DigitalSignatureUtil {
    private static final String SIGNATURE_ALGO = "SHA256withRSA";
    private static final String KEY_ALGO = "RSA";
    private static final int KEY_SIZE = 2048;

    /**
     * Tạo cặp khóa RSA (public/private).
     */
    public static KeyPair generateRSAKeyPair() {
        try {
            KeyPairGenerator kpg = KeyPairGenerator.getInstance(KEY_ALGO);
            kpg.initialize(KEY_SIZE);
            return kpg.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("RSA algorithm not available", e);
        }
    }

    /**
     * Ký dữ liệu bằng private key, trả về chuỗi Base64.
     */
    public static String sign(byte[] data, PrivateKey privateKey) {
        try {
            Signature sig = Signature.getInstance(SIGNATURE_ALGO);
            sig.initSign(privateKey);
            sig.update(data);
            byte[] signatureBytes = sig.sign();
            return Base64.getEncoder().encodeToString(signatureBytes);
        } catch (GeneralSecurityException e) {
            throw new RuntimeException("Signing failed", e);
        }
    }

    /**
     * Kiểm tra chữ ký (signatureBase64) của dữ liệu bằng public key.
     */
    public static boolean verify(byte[] data, String signatureBase64, PublicKey publicKey) {
        try {
            Signature sig = Signature.getInstance(SIGNATURE_ALGO);
            sig.initVerify(publicKey);
            sig.update(data);
            byte[] signatureBytes = Base64.getDecoder().decode(signatureBase64);
            return sig.verify(signatureBytes);
        } catch (GeneralSecurityException e) {
            throw new RuntimeException("Verify failed", e);
        }
    }
}
