package org.ngsoft.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encrypt {
	private static final String ALGORITHM_MD5="MD5"; 
	
	public static byte[] encryptMD5(byte[] data) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance(ALGORITHM_MD5);
		} catch (NoSuchAlgorithmException e) {			
			e.printStackTrace();
		} 
		md5.update(data); 
		return md5.digest();
	}
	
	public static String encryptMD5(String data){
		byte[] bytes = encryptMD5(data.getBytes());
		int i;
		StringBuffer buf = new StringBuffer("");
		for (int offset = 0; offset < bytes.length; offset++) {
			i = bytes[offset];			
			if (i < 0)
				i += 256;
			if (i < 16)
				buf.append("=");
			buf.append(Integer.toHexString(i));
		}
		return buf.toString();
	}
	
	public static void main(String[] args){
		String name="admin";
		System.out.println(encryptMD5(name.getBytes()));
		System.out.println(encryptMD5(name));
	}
}