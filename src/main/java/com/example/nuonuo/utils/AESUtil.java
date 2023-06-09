package com.example.nuonuo.utils;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AESUtil {

    public static void main(String[] args) {
        String str = "{\"Code\": \"\"}";
        String today = new SimpleDateFormat("yyyyMMdd").format(new Date());//当日
        String key = Md5.md5(today);
        //String key = Md5.md5("20221104");//new SimpleDateFormat("yyyyMMdd").format(new Date());//当日
        System.out.println("加密前：" +  str);
        try {
            String encryptResult = AESUtil.encrypt(str, key);
            System.out.println("加密后：" + encryptResult);
            String decryptResult = AESUtil.decrypt(encryptResult, key);
            System.out.println("解密后：" + decryptResult);
        } catch (Exception e) {
            System.out.println("AES加密解密出现异常:" + e);
        }
    }


    /**
     * AES加密
     *
     * @param content  需要加密的内容
     * @param password 加密密码
     * @return
     */
    public static String encrypt(String content, String password) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(128, new SecureRandom(password.getBytes()));
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            byte[] byteContent = content.getBytes("utf-8");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] result = cipher.doFinal(byteContent);
            Base64 base64 = new Base64();
            return base64.encodeToString(result);
        } catch (Exception e) {
            System.out.println("AES加密出现异常:" + e);
        }
        return null;
    }

    /**
     * AES解密
     *
     * @param content  待解密内容
     * @param password 解密密钥
     * @return
     */
    public static String decrypt(String content, String password) {
        try {
            Base64 base64 = new Base64();
            byte[] text = base64.decode(content);
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(128, new SecureRandom(password.getBytes()));
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] result = cipher.doFinal(text);
            return new String(result);
        } catch (Exception e) {
            System.out.println("AES解密出现异常:" + e);
        }
        return null;
    }

}
