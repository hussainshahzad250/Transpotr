package com.trux.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * 
 * @author Mithlesh Kumar
 * 
 */
public class GenerateHashWithSaltPasswordUnderMd5Utils {
	String pass;

	public String password_md5(String pass_word)
			throws NoSuchAlgorithmException {
		if (pass_word != null) {
			try {
				String saltValue = getSaltDb();
				pass = getSHASecurePassword(pass_word, saltValue);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return pass;
	}

	private static String convertToHex(byte[] data) {
	try{StringBuffer buf = new StringBuffer();
		for (int i = 0; i < data.length; i++) {
			int halfbyte = (data[i] >>> 4) & 0x0F;
			int two_halfs = 0;
			do {
				if ((0 <= halfbyte) && (halfbyte <= 9))
					buf.append((char) ('0' + halfbyte));
				else
					buf.append((char) ('a' + (halfbyte - 10)));
				halfbyte = data[i] & 0x0F;
			} while (two_halfs++ < 1);
		}
		return buf.toString();
	}catch(Exception er){er.printStackTrace();return "";}
	}

	// MD5 Hash
	public static String MD5(String text) throws NoSuchAlgorithmException,UnsupportedEncodingException {
		MessageDigest md;
		md = MessageDigest.getInstance("MD5");
		byte[] md5hash = new byte[32];
		md.update(text.getBytes("iso-8859-1"), 0, text.length());
		md5hash = md.digest();
		return convertToHex(md5hash);
	}

	// Main Function
	public static void main(String[] args) throws Exception { 
			String saltValue=TruxUtils.computeMD5Hash("mith@123");
			//9832d8fecf364513ff1b1ebb77918d7b4510V64b106837282789ed034ad288d672ddd
			//9832d8fecf364513ff1b1ebb77918d7b4510Vb988cf73b93963df19b340831c597d44
			saltValue=saltValue+getSalt(); 	
			//System.out.println(saltValue);
			//System.out.println(getRightHashPass("9832d8fecf364513ff1b1ebb77918d7b4510Vc270916f34169eaa4aa8fe759306152a"));	
	}

	public static String getRightHashPass(String passwordWithSalt){
		if(passwordWithSalt!=null){
			try{
				String saltValue=passwordWithSalt.substring(passwordWithSalt.lastIndexOf("4510V"),passwordWithSalt.length()-1);
				String passValue=passwordWithSalt.replace(saltValue, "");
				return passValue;
			}catch(Exception e){e.printStackTrace();
				return "";
			}
			}else
				return "";
		
	}
	public static String getRightHashPassAgent(String passwordWithSalt){
		if(passwordWithSalt!=null){
			try{
				String saltValue=passwordWithSalt.substring(passwordWithSalt.lastIndexOf("4510V"),passwordWithSalt.length());
				String passValue=passwordWithSalt.replace(saltValue, "");
				return passValue;
			}catch(Exception e){e.printStackTrace();
				return "";
			}
			}else
				return "";
		
	}
	public static String getSalt() {
		try {
			SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
			byte[] salt = new byte[16];
			sr.nextBytes(salt);
			return "4510V" + convertToHex(salt);
		} catch (NoSuchAlgorithmException e) {
			return ""+e.getMessage();
		}
	}

	public static String getSaltDb() throws NoSuchAlgorithmException {
		return "e0a5bb87f8980b038579e94b15e756a6";
	}

	private static String getSHASecurePassword(String passwordToHash,String salt) {
		String generatedPassword = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			md.update(salt.getBytes());
			byte[] bytes = md.digest(passwordToHash.getBytes());
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16)
						.substring(1));
			}
			generatedPassword = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return generatedPassword;
	}

	public String getSHASecurePasswords(String passwordToHash, String salt) {
		String generatedPassword = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			md.update(salt.getBytes());
			byte[] bytes = md.digest(passwordToHash.getBytes());
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16)
						.substring(1));
			}
			generatedPassword = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return generatedPassword;
	}
}