package com.example.demo;


import javax.crypto.*;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Arrays;
import java.util.Base64;

public class AESUtil {

    private static final int GCM_TAG_LENGTH = 128;

    public static SecretKey generateKey(int n) throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(n);
        SecretKey secretKey = keyGenerator.generateKey();
        return secretKey;
    }

    public static IvParameterSpec generateIv(){
        byte[] iv = new byte[16];
        new SecureRandom().nextBytes(iv);
        return new IvParameterSpec(iv);
    }

    public static String encrypt(SecretKey secretKey,String algorithm, String input,byte[] IV) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(GCM_TAG_LENGTH, IV);
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE,secretKey,gcmParameterSpec);
        byte[] cipherText = cipher.doFinal(input.getBytes(StandardCharsets.UTF_8));
        byte[] encrypted = new byte[IV.length+cipherText.length];

        System.arraycopy(IV, 0, encrypted, 0, IV.length);
        System.arraycopy(cipherText, 0, encrypted, IV.length, cipherText.length);

        return Base64.getEncoder().encodeToString(encrypted);
    }

    public static String decrypt(SecretKey secretKey,String algorithm, String cipherText) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException {
        // Get Cipher Instance
        byte[] decoded = Base64.getDecoder().decode(cipherText);
        byte[] iv = Arrays.copyOfRange(decoded, 0, 16);

        Cipher cipher = Cipher.getInstance(algorithm);
        SecretKeySpec keySpec = new SecretKeySpec(secretKey.getEncoded(), "AES");

        // Create GCMParameterSpec
        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(GCM_TAG_LENGTH, iv);

        // Initialize Cipher for DECRYPT_MODE
        cipher.init(Cipher.DECRYPT_MODE, keySpec, gcmParameterSpec);

        // Perform Decryption
        byte[] decryptedText = cipher.doFinal(decoded);
        return Base64.getEncoder().encodeToString(decryptedText);
    }
}
