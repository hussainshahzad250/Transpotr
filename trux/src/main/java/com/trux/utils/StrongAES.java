package com.trux.utils;

import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class StrongAES {
	public void run() {
		try {
			String user = "Trux1";
			String password = "trux@123";
			String key = "Bar12345Bar12345";

			Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES");
			Cipher cipherPass = Cipher.getInstance("AES");

			cipher.init(Cipher.ENCRYPT_MODE, aesKey);
			cipherPass.init(Cipher.ENCRYPT_MODE, aesKey);

			byte[] encrypted = cipher.doFinal(user.getBytes());
			byte[] encryptedPass = cipher.doFinal(password.getBytes());

			System.out.println(new String(encrypted));
			System.out.println(new String(encryptedPass));

			cipher.init(Cipher.DECRYPT_MODE, aesKey);
			cipherPass.init(Cipher.DECRYPT_MODE, aesKey);

			String decrypted = new String(cipher.doFinal(encrypted));
			String decryptedPass = new String(cipher.doFinal(encryptedPass));
			System.out.println(decrypted);
			System.out.println(decryptedPass);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static byte[] encriptedUser(String user) {
		 
		try {
			String key = "Bar12345Bar12345";
			Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, aesKey);
			byte[] encrypted = cipher.doFinal(user.getBytes());
			return encrypted;
		} catch (Exception er) {
			er.printStackTrace();
			return null;
		}
	}

	public static byte[] encriptedPassword(String password) {
		 	try {
			String key = "Bar12345Bar12345";
			Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
			Cipher cipherPass = Cipher.getInstance("AES");
			cipherPass.init(Cipher.ENCRYPT_MODE, aesKey);
			byte[] encrypted = cipherPass.doFinal(password.getBytes());
			return encrypted;
		} catch (Exception er) {
			er.printStackTrace();

			return null;
		}
	}

	public static String decriptedUser(byte[] encrypteduser) {

		String decrypted = "";
		try {
			String key = "Bar12345Bar12345";
            Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES"); 
        	cipher.init(Cipher.DECRYPT_MODE, aesKey); 
           	return new String(cipher.doFinal(encrypteduser));
		} catch (Exception er) {
			er.printStackTrace();
			return decrypted;
		}
	}

	public static String decriptedPassword(byte[] encryptedPassword) {
		String decrypted = "";
		try {
			String key = "Bar12345Bar12345";
			Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES"); 
        	cipher.init(Cipher.DECRYPT_MODE, aesKey); 
            decrypted = new String(cipher.doFinal(encryptedPassword));
            return decrypted;
		} catch (Exception er) {
			er.printStackTrace();
			return decrypted;
		}
	}

	public static void main(String[] args) {
	byte[] user= encriptedUser("Trux1");
	byte[] password= encriptedPassword("trux@123");
	System.out.println(user +"   " + password);
	
	String decriptUser=decriptedUser(user);
	
	String decpritPassw=decriptedPassword(password);
	System.out.println(decriptUser  +" P  ---" +decpritPassw);
	 
	}
}