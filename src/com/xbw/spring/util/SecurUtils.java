package com.xbw.spring.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;


/**
 * 
 * @ClassName: SerUtils
 * @Description:   加密算法
 * @author: xubowen
 * @date: 2016年6月1日 下午5:41:43
 *
 */
public class SecurUtils {
	private static final String ENMETHOD = "AES";
	private SecurUtils(){
		//private 
	}
	public static String md5(String plainText) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes());
			byte b[] = md.digest();
			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			// 32位加密
			return buf.toString();
		} catch (NoSuchAlgorithmException e) {
			CommUtils.log(e,null);
			return "";
		}
	}
	/**
	  * @Title: encrAes 
	  * @Description: aes加密
	  * @param content
	  * @param password
	  * @return 
	  * @return byte[] 
	  * @throws
	 */
	public static byte[] encrAes(String content, String password) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance(ENMETHOD);
			kgen.init(128, new SecureRandom(password.getBytes()));
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, ENMETHOD);
			Cipher cipher = Cipher.getInstance(ENMETHOD);// 创建密码器
			byte[] byteContent = content.getBytes("utf-8");
			cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
			return cipher.doFinal(byteContent);
		} catch ( Exception e) {
			CommUtils.log(e,null);
		}  
		return new byte[0];
	}
	/**
	  * @Title: decryptAes 
	  * @Description: aes解密
	  * @param content
	  * @param password
	  * @return 
	  * @return byte[] 
	  * @throws
	 */
	public static byte[] decryptAes(byte[] content, String password) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance(ENMETHOD);
			kgen.init(128, new SecureRandom(password.getBytes()));
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, ENMETHOD);
			Cipher cipher = Cipher.getInstance(ENMETHOD);// 创建密码器
			cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
			return cipher.doFinal(content); // 加密
		} catch ( Exception e) {
			CommUtils.log(e,null);
		}  
		return new byte[0];
	}

	/**
	 * 2进制转化为16进制
	 * 
	 * @Title: parseByte2HexStr
	 * @Author: xubowen
	 * @Create Date: 2016年6月1日 下午5:48:41
	 * @History: 2016年6月1日 下午5:48:41 xubowen Created.
	 *
	 * @param buf
	 * @return
	 *
	 */
	public static String parseByte2HexStr(byte[] buf) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
	}

	/**
	 * @Title: parseHexStr2Byte
	 * @Description: 16进制转2进制
	 * @Author: xubowen
	 * @Create Date: 2016年6月1日 下午5:49:12
	 * @History: 2016年6月1日 下午5:49:12 xubowen Created.
	 *
	 * @param hexStr
	 * @return
	 *
	 */
	public static byte[] parseHexStr2Byte(String hexStr) {
		if (hexStr.length() < 1)
			return new byte[0];
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2),
					16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}
}
