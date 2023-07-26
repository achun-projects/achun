package site.achun.user.app.utils;

import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

public class RsaUtil {

    public static final String DEFAULT_RSA_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCwtR4cn6aAQ8v9pVuTFi/axUEVpOm2WosZ1MdX7ywXIO1XakUxzwE2uyYBRAmN7uhAIW1n/iFMdKoycS09OM5SxZgFIykOu+P3ZILE+K7Wr1uVVfZHMG97CTAxMKJNISk4JldbEfIvbVCwoY5bQSBY0o5toTI7sISw6tQNsP3PEwIDAQAB";
    public static String DEFAULT_RSA_PRIVATE_KEY = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBALC1HhyfpoBDy/2lW5MWL9rFQRWk6bZaixnUx1fvLBcg7VdqRTHPATa7JgFECY3u6EAhbWf+IUx0qjJxLT04zlLFmAUjKQ674/dkgsT4rtavW5VV9kcwb3sJMDEwok0hKTgmV1sR8i9tULChjltBIFjSjm2hMjuwhLDq1A2w/c8TAgMBAAECgYATQtKy7QYBkiJAlg8aw77YmkqjWNPuwATMfoByCcxlAv5L2nYLXVR1CkY0uh1Ofr2LC/m4bZ9kjYzlradwNmpqtbu1ll5ZQVhH6Pl1Coh8XZ+mkiVPZ4waAczHwuSiJc3nEhWLG0L2aBY1nTZ+WWhFi0Ks/RUzT/bzEXxeBkgayQJBAOfjuacbVvzOrWQLRyvdbuSi8At0HJZqgyuaBKlV6omnvGAHUAuHAWwB4l3Wj1D+qatsdYPVXZ9ohP81hO7k2lkCQQDDFJg+gZduwPS8qxiGk7K4/9irnMqVBRwQclXApBgbeRD7RnxZEt8ywig3tXJ1ByYYDjJAJ0uT/pZN3jvUR69LAkBd/rPxZaakAQ7Nn7iwsihSKy3MJruzyOb0lJDRJw9TzxcPgcdTdN0Br93XFbKwLRoPb35O+vzhMde/Ly18PG25AkANstKGAR+bHV2SKgmM2a5BI2YZlub60+MGnKC86EjxFO1GV8q/jn2x2MRuNQWWhiv8oHKS4q+B8uynByX4YBhFAkBd76tcgadmZWPa+7Er0c9xdD8VCRMDoFreSSLj+WxOBLfF3pF2iPi+jGw8hBncWZX4eRw1FFuz6rOGJggCIrKE";
    private static final String ENCRYPT_TYPE = "RSA";
    private static final String PUBLIC_KEY = "RSAPublicKey";
    private static final String PRIVATE_KEY = "RSAPrivateKey";

    private RsaUtil() {
    }

    public static String encrypt(String content, PublicKey publicKey) {
        try {
            RSA rsa = new RSA((PrivateKey)null, publicKey);
            return rsa.encryptBase64(content, KeyType.PublicKey);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static String encrypt(String content, String publicKey) {
        try {
            RSA rsa = new RSA((String)null, publicKey);
            return rsa.encryptBase64(content, KeyType.PublicKey);
        } catch (Exception var3) {
            return null;
        }
    }

    public static void main(String[] args) {
        String encrypt = encrypt("yunbin", DEFAULT_RSA_PUBLIC_KEY);
        System.out.println(encrypt);
        System.out.println(decrypt(encrypt, DEFAULT_RSA_PRIVATE_KEY));
    }

    public static String decrypt(String content, PrivateKey privateKey) {
        try {
            RSA rsa = new RSA(privateKey, (PublicKey)null);
            return rsa.decryptStr(content, KeyType.PrivateKey);
        } catch (Exception var3) {
            return null;
        }
    }

    public static String decrypt(String content, String privateKey) {
        try {
            RSA rsa = new RSA(privateKey, (String)null);
            return rsa.decryptStr(content, KeyType.PrivateKey);
        } catch (Exception ex) {
            return null;
        }
    }

    public static Map<String, String> generateKeyPair() {
        try {
            KeyPair pair = SecureUtil.generateKeyPair("RSA");
            PrivateKey privateKey = pair.getPrivate();
            PublicKey publicKey = pair.getPublic();
            byte[] pubEncBytes = publicKey.getEncoded();
            byte[] priEncBytes = privateKey.getEncoded();
            String pubEncBase64 = Base64.encode(pubEncBytes);
            String priEncBase64 = Base64.encode(priEncBytes);
            Map<String, String> map = new HashMap(2);
            map.put("RSAPublicKey", pubEncBase64);
            map.put("RSAPrivateKey", priEncBase64);
            return map;
        } catch (Exception var8) {
            return null;
        }
    }
}
