package com.baiyi.order.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encryption {

	public static String encrypt(String info, String algorithm) {
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance(algorithm);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		byte[] src = null;
		try {
			src = info.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		messageDigest.update(src);
		byte[] dest = messageDigest.digest();
		// return Hex.encodeHexString(dest);
		return byte2hex(dest);
	}

	public static String encrypt(String info) {
		return encrypt(info, "SHA-1");
	}

	public static String byte2hex(byte[] b) {
		String result = "";
		int length = b.length;
		for (int n = 0; n < length; n++) {
			String temp = Integer.toHexString(b[n] & 0xFF);
			if (temp.length() == 1) {
				temp = "0" + temp;
			}
			if (n < length - 1) {
				temp = temp + ":";
			}
			result += temp.toUpperCase();
		}
		return result;
	}

	public static void main(String[] args) {
		String s = encrypt("root");
		System.out.println(s);
	}

}
