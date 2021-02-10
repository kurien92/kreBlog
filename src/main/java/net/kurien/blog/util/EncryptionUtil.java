package net.kurien.blog.util;

import org.jasypt.contrib.org.apache.commons.codec_1_3.binary.Base64;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class EncryptionUtil {
    private static BCryptPasswordEncoder bcryptPasswordEncoder = null;

    private static SecretKey aesSecretKey;
    private static IvParameterSpec aesIv;
    private static Cipher aesCipher;
    private static String kreSalt;

    static {
        bcryptPasswordEncoder = new BCryptPasswordEncoder();

        String kreAesKey = System.getenv("KRE_AES_KEY");
        String kreAesIv = System.getenv("KRE_AES_IV");
        String kreSalt = System.getenv("KRE_SALT");

        aesSecretKey = new SecretKeySpec(kreAesKey.getBytes(), "AES");
        aesIv = new IvParameterSpec(kreAesIv.getBytes(StandardCharsets.UTF_8));

        try {
            aesCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace(); // 잘못된 알고리즘
        } catch (NoSuchPaddingException e) {
            e.printStackTrace(); // 잘못된 패딩
        }
    }

    public static String hashPassword(String password) {
        String encodedPassword = bcryptPasswordEncoder.encode(password + kreSalt);

        return encodedPassword;
    }

    public static boolean checkPassword(String password, String hashedPassword) {
        if(bcryptPasswordEncoder.matches(password + kreSalt, hashedPassword)) {
            return true;
        }

        // Salt 사용 전 회원을 위한 기존 처리 유지
        if(bcryptPasswordEncoder.matches(password, hashedPassword)) {
            return true;
        }

        return false;
    }

    public static String AES256Encrypt(String str) throws InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        aesCipher.init(Cipher.ENCRYPT_MODE, aesSecretKey, aesIv);
        byte[] encryptedBytes = aesCipher.doFinal(str.getBytes(StandardCharsets.UTF_8));

        return new String(Base64.encodeBase64(encryptedBytes));
    }

    public static String AES256Decrypt(String str) throws BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException, InvalidKeyException {
        aesCipher.init(Cipher.DECRYPT_MODE, aesSecretKey, aesIv);
        byte[] decryptedBytes = Base64.decodeBase64(str.getBytes(StandardCharsets.UTF_8));

        return new String(aesCipher.doFinal(decryptedBytes), StandardCharsets.UTF_8);
    }
}
