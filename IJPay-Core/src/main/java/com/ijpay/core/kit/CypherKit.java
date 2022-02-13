package com.ijpay.core.kit;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

/**
 * @author xyb
 * @Description
 * @Date 2022/2/13 15:32
 */
public class CypherKit {

	private static final String HMACSHA256 = "HmacSHA256";

	private static final String KEY_AES = "AES";

	public static byte[] encodeToBytes(String input){
		return Base64.encodeBase64(input.getBytes(StandardCharsets.UTF_8));
	}

	public static String encode(String input){
		return Base64.encodeBase64String(input.getBytes(StandardCharsets.UTF_8));
	}
	public static String encode(byte[] bytes){
		return Base64.encodeBase64String(bytes);
	}

	public static byte[] decodeToBytes (String input){
		return Base64.decodeBase64(input);
	}

	public static String decode (String input){
		byte[] bs = Base64.decodeBase64(input);
		return new String(bs);
	}

	/**
	 * 生成16进制 MD5 字符串
	 *
	 * @param data 数据
	 * @return MD5 字符串
	 */
	public static String md5(String data) {
		return DigestUtils.md5Hex(data);
	}

	/**
	 * 生成 HMACSHA256
	 *
	 * @param data 待处理数据
	 * @param key  密钥
	 * @return 加密结果
	 * @throws Exception
	 */
	public static String hmacSha256(String data, String key) {
		SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), HMACSHA256);
		try {
			Mac sha256Hmac = Mac.getInstance(HMACSHA256);
			sha256Hmac.init(secretKey);
			byte[] array = sha256Hmac.doFinal(data.getBytes(StandardCharsets.UTF_8));
			return toHex(array);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * AES 解密
	 *
	 * @param input 需要解密的数据
	 * @param key        密钥
	 * @return 解密后的数据
	 */
	public static String decryptAes(String input, String key) {
		if (StringUtils.isEmpty(key) || 16 != key.length()) {
			throw new RuntimeException("key不满足条件");
		}
		key = md5(key);
		byte[] keys = key.getBytes(StandardCharsets.UTF_8);
		SecretKeySpec spec = new SecretKeySpec(keys, KEY_AES);
		try {
			// 确定算法
			Cipher cipher = Cipher.getInstance(KEY_AES);
			cipher.init(Cipher.DECRYPT_MODE, spec);
			byte[] inputBs = decodeToBytes(input);
			byte[] bs = cipher.doFinal(inputBs);
			return new String(bs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * AES 加密
	 *
	 * @param data 需要加密的数据
	 * @param key  密钥
	 * @return 加密后的数据
	 */
	public static String encryptAes(String data, String key) {
		if (StringUtils.isEmpty(key) || 16 != key.length()) {
			throw new RuntimeException("key不满足条件");
		}
		key = md5(key);
		byte[] keys = key.getBytes(StandardCharsets.UTF_8);
		SecretKeySpec spec = new SecretKeySpec(keys, KEY_AES);
		try {
			// 确定算法
			Cipher cipher = Cipher.getInstance(KEY_AES);
			cipher.init(Cipher.ENCRYPT_MODE, spec);
			byte[] bs = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
			return encode(bs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static String toHex (byte[] bs){
		StringBuilder sb = new StringBuilder();
		for (byte item : bs) {
			sb.append(Integer.toHexString((item & 0xFF) | 0x100), 1, 3);
		}
		return sb.toString().toUpperCase();
	}

}
