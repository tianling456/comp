package com.base.util.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

/**
 * 加密工具。
 * 
 * <p>
 * Title: DoradoUtil.java
 * </p>
 * <p>
 * Description: com.computech.common.util
 * </p>
 * <p>
 * Copyright: Copyright (c)
 * </p>
 * <p>
 * Company: computech
 * </p>
 * 
 * @author (dongmy,wangke)
 * @version 1.0
 */
public class Encoder {
	private static byte[] keyBytes = { 0x33, 0x31, 0x38, 0x31, 0x36, 0x37, 0x38, 0x34, 0x34, 0x35, 0x32, 0x31, 0x37, 0x39, 0x30, 0x35 };

	public static void main(String[] args) {
		Encoder encoder = new Encoder();
		try {
			String username="root";
			String password="tianling";
			System.out.println("AES encrype ========================================");
			System.out.println("plase enter userid:");
			System.out.println("encoder userid: " + encoder.encryptToAES(username));
			System.out.println("plase enter password:");
			System.out.println("encoder passwd: " + encoder.encryptToAES(password));
			System.out.println("MD5 encrype ========================================");
			System.out.println("plase enter userid:");
			System.out.println("plase enter password:");
			String password1="12345";
			System.out.println("====" + encoder.encryptToMD5(password, password));
			System.out.println(encoder.decryptToAES("d151bb6bb95bb283284a750b5f3a1fab"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 进行密文加密，<b>无法解密</b>。
	 * 
	 * @param obj
	 * @return
	 */
	public String encryptToMD5(String value) {
		String caesarStr = caesarEncoder(value, 9);
		String md5Str = md5Encoder(caesarStr);
		return md5Str;
	}

	/**
	 * 进行密文加密，<b>无法解密</b>。
	 * 
	 * @param str1
	 * @param str2
	 * @return
	 */
	public String encryptToMD5(String str1, String str2) {
		String caesarStr = caesarEncoder(str1 + str2, 9);
		String md5Str = md5Encoder(caesarStr);
		return md5Str;
	}

	/**
	 * 密码校验。
	 * 
	 * @param process
	 * @param raw
	 * @return
	 */
	public boolean isPasswordValid(String process, String raw) {
		String passwordOne = process;
		String passwordTwo = encryptToMD5(raw);
		return passwordOne.equals(passwordTwo);
	}

	/**
	 * 进行密文加密。
	 * 
	 * @param value
	 * @return
	 */
	public String encryptToAES(String value) {
		String str = null;
		try {
			int mode = Cipher.ENCRYPT_MODE;
			Cipher cipher = initCipher(mode);
			byte[] outBytes = cipher.doFinal(value.getBytes());
			str = String.valueOf(Hex.encodeHex(outBytes));
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return str;
	}

	/**
	 * 进行密文解密。
	 * 
	 * @param value
	 * @return
	 */
	public String decryptToAES(String value) {
		String str = null;
		try {
			int mode = Cipher.DECRYPT_MODE;
			Cipher cipher = initCipher(mode);
			byte[] outBytes = cipher.doFinal(Hex.decodeHex(value.toCharArray()));
			str = new String(outBytes);
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (DecoderException e) {
			e.printStackTrace();
		}
		return str;
	}

	/**
	 * MD5加密。
	 * 
	 * @param value
	 * @return
	 */
	private String md5Encoder(String value) {
		String encryptStr = null;
		try {
			/* 加密方式MD5或SHA-1。 */
			MessageDigest encoderMode = MessageDigest.getInstance("MD5");
			encoderMode.update(value.getBytes());
			byte[] digest = encoderMode.digest();
			encryptStr = byte2hex(digest);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return encryptStr;
	}

	/**
	 * 凯撒加密。
	 * 
	 * @param obj
	 * @param key
	 * @return
	 */
	private String caesarEncoder(Object obj, int key) {
		String raw = obj.toString();
		StringBuffer process = new StringBuffer("");
		if (raw != null && !"".equals(raw)) {
			for (int i = 0; i < raw.length(); i++) {
				char character = raw.charAt(i);
				if (character == 32) {
					process.append("");
				}
				if (character >= '0' && character <= '9') {
					character = (char) ('0' + ((character - 48 + key) % 10));
					process.append(character);
				} else if (character >= 'A' && character <= 'Z') {
					character = (char) ('A' + ((character - 65 + key) % 26));
					process.append(character);
				} else if (character >= 'a' && character <= 'z') {
					character = (char) ('a' + ((character - 97 + key) % 26));
					process.append(character);
				}
			}
		}
		return process.toString();
	}

	/**
	 * AES加密。
	 * 
	 * @param mode
	 * @return
	 */
	private Cipher initCipher(int mode) {
		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			Key key = new SecretKeySpec(keyBytes, "AES");
			cipher.init(mode, key);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}
		return cipher;
	}

	/**
	 * 二进制字符转换。
	 * 
	 * @param b
	 * @return
	 */
	private static String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int i = 0; i < b.length; i++) {
			stmp = Integer.toHexString(b[i] & 0XFF);
			if (stmp.length() == 1) {
				hs = hs + "0" + stmp;
			} else {
				hs = hs + stmp;
			}

			// if(i < b.length - 1)
			// {
			// hs = hs + ":";
			// }
		}
		return hs.toUpperCase();
	}
}