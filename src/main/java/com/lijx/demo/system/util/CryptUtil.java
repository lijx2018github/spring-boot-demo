package com.lijx.demo.system.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.SecureRandom;

public class CryptUtil {


    private static final String KEY_MD5 = "MD5";
    private static final String KEY_SHA = "SHA";
    /**
     * MAC算法可选以下多种算法
     *
     * <pre>
     *
     * HmacMD5
     * HmacSHA1
     * HmacSHA256
     * HmacSHA384
     * HmacSHA512
     * </pre>
     */

    public static final String KEY_MAC = "HmacMD5";

    /**
     * BASE64解密
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decryptBASE64(String key) throws Exception {
        return (new BASE64Decoder()).decodeBuffer(key);
    }

    /**
     * BASE64 加密
     *
     * @param key
     * @return
     * @throws Exception
     */

    public static String encryptBASE64(byte[] key) throws Exception {
        return (new BASE64Encoder()).encodeBuffer(key);
    }

    /**
     * MD5加密
     *
     * @param data
     * @return
     * @throws Exception
     */

    public static byte[] encryptMD5(byte[] data) throws Exception {
        MessageDigest md5 = MessageDigest.getInstance(KEY_MD5);
        md5.update(data);
        return md5.digest();
    }

    /**
     * SHA加密
     *
     * @param data
     * @return
     * @throws Exception
     */

    public static byte[] encryptSHA(byte[] data) throws Exception {
        MessageDigest sha = MessageDigest.getInstance(KEY_SHA);
        sha.update(data);
        return sha.digest();
    }

    /**
     * 初始化HMAC密钥
     *
     * @return
     * @throws Exception
     */

    public static String initMacKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_MAC);
        SecretKey secretKey = keyGenerator.generateKey();
        return encryptBASE64(secretKey.getEncoded());
    }

    /**
     * HMAC 加密
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */

    public static byte[] encryptHMAC(byte[] data, String key) throws Exception {
        SecretKey secretKey = new SecretKeySpec(decryptBASE64(key), KEY_MAC);
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());
        mac.init(secretKey);
        return mac.doFinal(data);
    }

    /**
     * DES 算法 <br>
     * 可替换为以下任意一种算法，同时key值的size相应改变。
     *
     * <pre>
     * DES   key size must be equal to 56
     * DESede(TripleDES)    key size must be equal to 112 or 168
     * AES                  key size must be equal to 128, 192 or 256,but 192 and 256 bits may not be available
     * Blowfish             key size must be multiple of 8, and can only range from 32 to 448 (inclusive)
     * RC2                  key size must be between 40 and 1024 bits
     * RC4(ARCFOUR)         key size must be between 40 and 1024 bits
     * </pre>
     */

    public static final String ALGORITHM = "DES";

    /**
     * DES 算法转换密钥<br>
     *
     * @param key
     * @return
     * @throws Exception
     */

    private static Key toKey(byte[] key) throws Exception {
        SecretKey secretKey = null;
        if (ALGORITHM.equals("DES") || ALGORITHM.equals("DESede")) {
            DESKeySpec dks = new DESKeySpec(key);
            SecretKeyFactory keyFactory = SecretKeyFactory
                    .getInstance(ALGORITHM);
            secretKey = keyFactory.generateSecret(dks);
        } else {
            // 当使用其他对称加密算法时，如AES、Blowfish等算法时，用下述代码替换上述三行代码
            secretKey = new SecretKeySpec(key, ALGORITHM);
        }
        return secretKey;
    }

    /**
     * 对数据进行DES加密.
     *
     * @param data
     *            待进行DES加密的数据
     * @return 返回经过DES加密后的数据
     * @throws Exception
     * @author <a href="mailto:xiexingxing1121@126.com"
     *         mce_href="mailto:xiexingxing1121@126.com">AmigoXie</a> Creation
     *         date: 2007-7-31 - 下午12:06:24
     */
    public final static String decrypt(String data,String key) throws Exception {
        return new String(decrypt(hex2byte(data.getBytes()),
                key.getBytes()));
    }

    /**
     * 用指定的key对数据进行DES解密.
     *
     * @param data
     *            待解密的数据
     * @param key
     *            DES解密的key
     * @return 返回DES解密后的数据
     * @throws Exception
     * @author <a href="mailto:xiexingxing1121@126.com"
     *         mce_href="mailto:xiexingxing1121@126.com">AmigoXie</a> Creation
     *         date: 2007-7-31 - 下午12:10:34
     */
    private static byte[] decrypt(byte[] data, byte[] key) throws Exception {
        // DES算法要求有一个可信任的随机数源
        SecureRandom sr = new SecureRandom();
        // 从原始密匙数据创建一个DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);
        // 创建一个密匙工厂，然后用它把DESKeySpec对象转换成
        // 一个SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
        SecretKey securekey = keyFactory.generateSecret(dks);
        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        // 用密匙初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, securekey, sr);
        // 现在，获取数据并解密
        // 正式执行解密操作
        return cipher.doFinal(data);
    }

    public static byte[] hex2byte(byte[] b) {
        if ((b.length % 2) != 0)
            throw new IllegalArgumentException("长度不是偶数");
        byte[] b2 = new byte[b.length / 2];
        for (int n = 0; n < b.length; n += 2) {
            String item = new String(b, n, 2);
            b2[n / 2] = (byte) Integer.parseInt(item, 16);
        }
        return b2;
    }
    /**
     * 对用DES加密过的数据进行解密.
     *
     * @param data
     *            DES加密数据
     * @return 返回解密后的数据
     * @throws Exception
     * @author <a href="mailto:xiexingxing1121@126.com"
     *         mce_href="mailto:xiexingxing1121@126.com">AmigoXie</a> Creation
     *         date: 2007-7-31 - 下午12:07:54
     */
    public final static String encrypt(String data,String key) throws Exception {
        return byte2hex(encrypt(data.getBytes(), key.getBytes()));
    }

    public static String byte2hex(byte[] b) {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1)
                hs = hs + "0" + stmp;
            else
                hs = hs + stmp;
        }
        return hs.toUpperCase();
    }

    private static byte[] encrypt(byte[] data, byte[] key) throws Exception {
        // DES算法要求有一个可信任的随机数源
        SecureRandom sr = new SecureRandom();
        // 从原始密匙数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);
        // 创建一个密匙工厂，然后用它把DESKeySpec转换成
        // 一个SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
        SecretKey securekey = keyFactory.generateSecret(dks);
        // Cipher对象实际完成加密操作
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        // 用密匙初始化Cipher对象
        cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
        // 现在，获取数据并加密
        // 正式执行加密操作
        return cipher.doFinal(data);
    }
    public String getMD5(String vars) throws Exception {
        // 首先用生成一个MessageDigest类,确定计算方法
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        // 添加要进行加密的信息
        md5.update(vars.getBytes("UTF-8"));
        // 开始进行加密
        byte[] digesta = md5.digest();
        BASE64Encoder base64 = new BASE64Encoder();
        return base64.encode(digesta);
    }

    public static String getSHA(String vars) throws Exception {
        // 首先用生成一个MessageDigest类,确定计算方法
        MessageDigest md5 = MessageDigest.getInstance("SHA-1");
        // 添加要进行加密的信息
        md5.update(vars.getBytes("UTF-8"));
        // 开始进行加密
        byte[] digesta = md5.digest();
        BASE64Encoder base64 = new BASE64Encoder();
        return base64.encode(digesta);
    }


    public static void main(String[] args) {
        try {
            String s = "syscc";
            byte[] c;
			/*String b = CryptUtil.encryptBASE64(s.getBytes("UTF-8"));
			System.out.println("BASE64加密后:" + b);
			byte[] c = CryptUtil.decryptBASE64(b);
			System.out.println("BASE64解密后:" + new String(c, "UTF-8"));
			c = encryptMD5(s.getBytes());
			System.out.println("MD5   加密后:" + new BigInteger(c).toString(16));
			c = encryptSHA(s.getBytes());
			System.out.println("SHA   加密后:" + new BigInteger(c).toString(16));
			// String key = initMacKey();
			// System.out.println("HMAC密匙:" + key);
			// c = encryptHMAC(s.getBytes(), key);
			// System.out.println("HMAC  加密后:" + new
			// BigInteger(c).toString(16));
			// String key = initKey();
			 */
            String key = "CY9rzUYh03PK3k6DJie09g==";
            System.out.println(ALGORITHM + "密钥:\t" + key);
            String md5Password = "syscc";
            String str = encrypt(md5Password,key);
            System.out.println("str:"+str);
            str = decrypt(str,key);
            System.out.println("str: " + str);

        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
