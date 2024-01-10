package kr.co.elink.common.util;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AES256 {

	private static final String secretKey = "12345678901234567890123456789012";
	private static final String iv = "abcdefghijklmnop";

	// 암호화
	public static String encrypt(String text) {
		try {
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, 
						new SecretKeySpec(secretKey.getBytes(), "AES"),
						new IvParameterSpec(iv.getBytes()));
			
			return new String(Base64.getEncoder().encode(cipher.doFinal(text.getBytes("UTF-8"))));
		} catch(Exception e) { 
			return text;
		}
	}
	
	// 복호화
	public static String decrypt(String encryptedText) {
		try {
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE,
						new SecretKeySpec(secretKey.getBytes(), "AES"),
						new IvParameterSpec(iv.getBytes()));
			
			return new String(cipher.doFinal(Base64.getDecoder().decode(encryptedText.getBytes("UTF-8"))));
		} catch(Exception e) {
			return encryptedText;
		}
	}
		
//	public static String encrypt(String strToEncrypt) {
//		try {
//			byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
//			IvParameterSpec ivspec = new IvParameterSpec(iv);
//
//			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
//			KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALT.getBytes(), 65536, 256);
//			SecretKey tmp = factory.generateSecret(spec);
//			SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");
//
//			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
//			cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
//			return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8)));
//		} catch (Exception e) {
//			System.out.println("Error while encrypting: " + e.toString());
//		}
//		return null;
//	}
//
//	public static String decrypt(String strToDecrypt) {
//		try {
//			byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
//			IvParameterSpec ivspec = new IvParameterSpec(iv);
//
//			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
//			KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALT.getBytes(), 65536, 256);
//			SecretKey tmp = factory.generateSecret(spec);
//			SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");
//
//			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
//			cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
//			return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
//		} catch (Exception e) {
//			System.out.println("Error while decrypting: " + e.toString());
//		}
//		return null;
//	}

//	public static void main(String[] args) {
//		String originalString = "23M0000693";
//
//		String encryptedString = AES256.encrypt(originalString);
//		String decryptedString = AES256.decrypt(encryptedString);
//
//		System.out.println(originalString);
//		System.out.println(encryptedString);
//		System.out.println(decryptedString);
//	}

}
