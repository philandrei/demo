package com.example.demo;


import javax.crypto.*;
import javax.crypto.spec.GCMParameterSpec;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class AESUtil {

    private static final int TAG_LENGTH_BIT = 128;
    private static final int IV_LENGTH = 12;
    private static final String ENCRYPT_ALG = "AES/GCM/NoPadding";

    private static final Charset UTF_8 = StandardCharsets.UTF_8;

    public static SecretKey generateKey(int n) throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(n, SecureRandom.getInstanceStrong());
        SecretKey secretKey = keyGenerator.generateKey();
        return secretKey;
    }

    public static byte[] generateIv() {
        byte[] iv = new byte[IV_LENGTH];
        new SecureRandom().nextBytes(iv);
        return iv;
    }

    public static String hex(byte[] bytes) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bytes) {
            stringBuilder.append(String.format("%02x", b));
        }
        return stringBuilder.toString();
    }

    public static byte[] encryptWithPrefixIV(String pText, SecretKey secret, byte[] iv) throws Exception {
        byte[] cipherText = encrypt(secret, pText, iv);
        byte[] cipherTextWithIv = ByteBuffer.allocate(iv.length + cipherText.length).put(iv).put(cipherText).array();
        return cipherTextWithIv;

    }

    public static byte[] encrypt(SecretKey secretKey, String input, byte[] IV) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(TAG_LENGTH_BIT, IV);
        Cipher cipher = Cipher.getInstance(ENCRYPT_ALG);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, gcmParameterSpec);
        byte[] cipherText = cipher.doFinal(input.getBytes(UTF_8));
        return cipherText;
    }


    public static String decrypt(byte[] cipherText, SecretKey secretKey, byte[] iv) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException {
        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(TAG_LENGTH_BIT, iv);
        Cipher cipher = Cipher.getInstance(ENCRYPT_ALG);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, gcmParameterSpec);
        byte[] plainText = cipher.doFinal(cipherText);
        return new String(plainText, UTF_8);
    }

    public static String decryptWithPrefixIV(String cText, SecretKey secret) throws Exception {
        byte[] byteText = Base64.getDecoder().decode(cText);
        ByteBuffer bb = ByteBuffer.wrap(byteText);

        byte[] iv = new byte[IV_LENGTH];
        bb.get(iv);
//        bb.get(iv, 0, iv.length);

        byte[] cipherText = new byte[bb.remaining()];
        bb.get(cipherText);

        String plainText = decrypt(cipherText, secret, iv);
        return plainText;

    }


}
