package sample;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Map;
import java.util.TreeMap;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Encoder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created By Like on 2017/8/25.
 */
@SuppressWarnings("WeakerAccess,unused")
public class EncodeUtils {
    private static Gson gson;
    private String publicKey;
    private String privateKey;
    private static String mKey, mIv;
    private static int mCode;

    private static class EncodeUtilsHolder {
        private static final EncodeUtils INSTANCE = new EncodeUtils();
    }

    private EncodeUtils() {
        GsonBuilder gb = new GsonBuilder();
        gb.disableHtmlEscaping();
        EncodeUtils.gson = gb.create();
    }

    /**
     * @param publicKey
     *        公钥
     * @param privateKey
     *        私钥
     * @return 工具实例
     */
    public static EncodeUtils getInstance(String publicKey, String privateKey) {
        return EncodeUtils.getInstance(publicKey, privateKey,
            -Integer.MAX_VALUE);
    }

    /**
     * @param publicKey
     *        公钥
     * @param privateKey
     *        私钥
     * @param code
     *        三方平台代码
     * @return 工具实例
     */
    public static EncodeUtils getInstance(String publicKey, String privateKey,
            int code) {
        EncodeUtilsHolder.INSTANCE.setPublicKey(publicKey);
        EncodeUtilsHolder.INSTANCE.setPrivateKey(privateKey);
        EncodeUtils.mKey = EncodeUtils.sha1(publicKey).substring(0, 16);
        EncodeUtils.mIv = EncodeUtils.md5(publicKey).substring(8, 24);
        EncodeUtils.mCode = code;
        return EncodeUtilsHolder.INSTANCE;
    }

    SimpleDateFormat dataFormat = new SimpleDateFormat("yyyyMMddHHmmss");

    /**
     * 通过用户数据构建Json请求串，时间默认使用系统当前时间，三方平台代码默认由构造器传入。
     *
     * @param data
     *        数据类对象
     * @return 加密加签后的Json字符串
     */
    public String getRequest(Object data) {
        if (EncodeUtils.mCode == -Integer.MAX_VALUE) {
            throw new IllegalArgumentException(
                "使用此方法构造请求，请在获取EncodeUtils实例时初始化code字段。");
        }
        return this.getRequest(data, EncodeUtils.mCode);
    }

    /**
     * 通过用户数据构建Json请求串，时间默认使用系统当前时间。
     *
     * @param data
     *        数据类对象
     * @param code
     *        三方平台代码
     * @return 加密加签后的Json字符串
     */
    public String getRequest(Object data, int code) {
        String time = this.dataFormat.format(System.currentTimeMillis());
        return this.getRequest(data, time, code);
    }

    /**
     * 通过用户数据构建Json请求串，三方平台代码默认由构造器传入。
     *
     * @param data
     *        数据类对象
     * @param time
     *        时间戳
     * @return 加密加签后的Json字符串
     */
    public String getRequest(Object data, String time) {
        if (EncodeUtils.mCode == -Integer.MAX_VALUE) {
            throw new IllegalArgumentException(
                "使用此方法构造请求，请在获取EncodeUtils实例时初始化code字段。");
        }
        return this.getRequest(data, time, EncodeUtils.mCode);
    }

    /**
     * 通过用户数据构建Json请求串
     *
     * @param data
     *        数据类对象
     * @param time
     *        时间
     * @param code
     *        三方平台代码
     * @return 加密加签后的Json字符串
     */
    public String getRequest(Object data, String time, int code) {
        String src = EncodeUtils.gson.toJson(data);
        String json = this.aes(src.getBytes());
        //使用TreeMap对键值进行排序
        Map<String, Object> map = new TreeMap<>();
        map.put("time", time);
        map.put("third_code", code);
        map.put("data", json);
        String sign = this.getSignature(map);
        Request request = new Request();
        request.request = map;
        request.signature = sign;
        return EncodeUtils.gson.toJson(request);
    }

    static class Request {
        Map<String, Object> request;
        String signature;
    }

    public static String md5(String str) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(str.getBytes());
            return new BigInteger(1, digest.digest()).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String sha1(String str) {
        try {
            MessageDigest sha1;
            sha1 = MessageDigest.getInstance("SHA1");
            sha1.update(str.getBytes());
            byte[] digest = sha1.digest();
            return new BigInteger(1, digest).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    private static final String ALGORITHM = "AES/CBC/PKCS5Padding";

    public String aes(byte[] src) {
        return this.aes(src, EncodeUtils.mKey.getBytes(),
            EncodeUtils.mIv.getBytes());
    }

    public String aes(byte[] srcData, byte[] key, byte[] iv) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
            Cipher cipher = Cipher.getInstance(EncodeUtils.ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(iv));
            byte[] encData = cipher.doFinal(srcData);
            return Base64.getEncoder().encodeToString(encData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public String aesDecode(byte[] enc) {
        return this.aesDecode(enc, EncodeUtils.mKey.getBytes(),
            EncodeUtils.mIv.getBytes());
    }

    public String aesDecode(byte[] encData, byte[] key, byte[] iv) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
            Cipher cipher = Cipher.getInstance(EncodeUtils.ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(iv));
            byte[] doFinal = cipher.doFinal(encData);
            return new String(doFinal);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    private static final String ENCODING = "UTF-8";
    private static final String SIGNATURE_ALGORITHM = "SHA256withRSA";

    public byte[] sign256(String data, PrivateKey privateKey)
            throws NoSuchAlgorithmException, InvalidKeyException,
            SignatureException, UnsupportedEncodingException {
        Signature signature = Signature
            .getInstance(EncodeUtils.SIGNATURE_ALGORITHM);
        signature.initSign(privateKey);
        signature.update(data.getBytes(EncodeUtils.ENCODING));
        return signature.sign();
    }

    public String getSignature(Map<String, Object> params) {
        return this.getSignature(params, this.privateKey);
    }

    public String getSignature(Map<String, Object> params, String secretKey) {
        try {
            PrivateKey privateKey = this.getPrivateKey(secretKey);
            String sortedJson = EncodeUtils.gson.toJson(params);
            return new BASE64Encoder().encode(this.sign256(sortedJson,
                privateKey));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    private RSAPrivateKey getPrivateKey(String privateKey) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(privateKey.getBytes());
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
    }

    public String getPublicKey() {
        return this.publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getPrivateKey() {
        return this.privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }
}
