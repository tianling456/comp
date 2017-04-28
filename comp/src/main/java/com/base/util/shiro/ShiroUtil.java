package com.base.util.shiro;

import java.util.UUID;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;

/**
 *项目名：
 *创建时间：2017-3-18
 *创建人：Aobo
 *类名：ShiroUtil
 *所属包名：com.base.util
 *项目名称：comp
 *类功能描述：
 */
public class ShiroUtil {
	/**
	 * 生成密码
	 * @param password  密码
	 * @param salt 盐
	 * @return
	 */
	public static String getPassword(String password,String salt){
		Md5Hash hash = new Md5Hash(password,salt,2);
		return hash.toString();
	}
	/**
	 * 
	 * @param digest 加密方式 ，默认为MD5
	 * @param password  密码
	 * @param salt 盐
	 * @return
	 */
	public static String getPassword(String digest,String password,String salt){
		SimpleHash hash = new SimpleHash(digest, password,salt,2);
		return hash.toString();
	}
	/**
	 * 生成随机字符串
	 * @param length  长度，默认为3字节（6个字符）
	 * @return
	 */
	public static String generateSalt(int length){
        SecureRandomNumberGenerator secureRandom = new SecureRandomNumberGenerator();
        String hex = "";
        if(length==0){
        	hex = secureRandom.nextBytes(4).toHex(); //一个Byte占两个字节，此处生成的3字节，字符串长度为6
        }else{
        	hex = secureRandom.nextBytes(length).toHex(); //一个Byte占两个字节，此处生成的3字节，字符串长度为6
        }
        return hex;
	}
	public static void main(String[] args) {
		String salt = ShiroUtil.generateSalt(0);
		String password = ShiroUtil.getPassword("4030tianling", "c1c147c56");
		UUID uuid = UUID.randomUUID();
		System.out.println("salt==="+salt);
		System.out.println("password==="+password);
		System.out.println("uuid==="+uuid);
	}
}

