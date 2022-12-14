package com.padmakar.aes256cbc;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
public enum AESUtil {
    ;
  // private static final String ENCRYPTION_KEY = "9427bfe5b2a07ddfbdc30a254f44fd09bdd216c6ab1b37738c5758e4fa4d114d";
  // private static final String ENCRYPTION_IV = "59ec1871179215f16986aa4e4092cda8";
    /*16 bit Encrption*/
    private static final String ENCRYPTION_KEY = "bf3c199c2470cb477d907b1e0917c17b";
    private static final String ENCRYPTION_IV = "5183666c72eec9e4";

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String encrypt(String src) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, makeKey(), makeIv());
            Base64.Encoder encoder = Base64.getEncoder();
            return encoder.encodeToString(cipher.doFinal(src.getBytes()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String decrypt(String src) {
        String decrypted = "";
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, makeKey(), makeIv());
            Base64.Decoder decoder = Base64.getDecoder();
            decrypted = new String(cipher.doFinal(decoder.decode(src)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return decrypted;
    }

    static AlgorithmParameterSpec makeIv() {
        try {
           // return new IvParameterSpec(ENCRYPTION_IV.getBytes("UTF-8"));
            return new IvParameterSpec(ENCRYPTION_IV.substring(0,16).getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    static Key makeKey() {
        try {
            byte[] key = ENCRYPTION_KEY.substring(0,32).getBytes("UTF-8");
            return new SecretKeySpec(key, "AES");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }

}
