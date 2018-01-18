package com.chuanqi56.logistics.utils;

import java.security.MessageDigest;

public class MD5 {

	private MD5() {
	}

	public final static String getMessageDigest(byte[] buffer) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(buffer);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 签名字段,拼接后的字符串做MD5运算,运算结果变为大写
	 *
	 * @return
	 */
	public static String getSign(String signStr) {
		String secrets = MD5.getMessageDigest(signStr.getBytes());
		return secrets.toUpperCase();
	}
}
