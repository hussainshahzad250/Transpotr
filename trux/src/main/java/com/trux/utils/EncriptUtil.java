package com.trux.utils;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
 
 public class EncriptUtil  {
 
 static   Cipher ecipher;
 static Cipher dcipher;
    // 8-byte Salt
 static   byte[] salt = {
        (byte) 0xA9, (byte) 0x9B, (byte) 0xC8, (byte) 0x32,
        (byte) 0x56, (byte) 0x35, (byte) 0xE3, (byte) 0x03
    };
    // Iteration count
  static  int iterationCount = 19;
    public EncriptUtil() { 
 
    }
 
     
    @SuppressWarnings("restriction")
	public static String  encryp(String plainText)     throws Exception, 
            InvalidKeySpecException, 
            NoSuchPaddingException, 
            InvalidKeyException,
            InvalidAlgorithmParameterException, 
            UnsupportedEncodingException, 
            IllegalBlockSizeException, 
            BadPaddingException{
    	 String secretKey="ezeon8547"; 
          KeySpec keySpec = new PBEKeySpec(secretKey.toCharArray(), salt, iterationCount);
        SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec);        
          AlgorithmParameterSpec paramSpec = new PBEParameterSpec(salt, iterationCount);
          ecipher = Cipher.getInstance(key.getAlgorithm());
         ecipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);      
        String charSet="UTF-8";       
        byte[] in = plainText.getBytes(charSet);
        byte[] out = ecipher.doFinal(in); 
        String encStr=new sun.misc.BASE64Encoder().encode(out);
        return encStr;
    }
     
    @SuppressWarnings("restriction")
	public static String decrypt(String encryptedText)
     throws NoSuchAlgorithmException, 
            InvalidKeySpecException, 
            NoSuchPaddingException, 
            InvalidKeyException,
            InvalidAlgorithmParameterException, 
            UnsupportedEncodingException, 
            IllegalBlockSizeException, 
            BadPaddingException, 
            IOException{
    	 String secretKey="ezeon8547";  
        KeySpec keySpec = new PBEKeySpec(secretKey.toCharArray(), salt, iterationCount);
        SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec);        
         AlgorithmParameterSpec paramSpec = new PBEParameterSpec(salt, iterationCount);
         dcipher=Cipher.getInstance(key.getAlgorithm());
        dcipher.init(Cipher.DECRYPT_MODE, key,paramSpec);
        byte[] enc = new sun.misc.BASE64Decoder().decodeBuffer(encryptedText);
        byte[] utf8 = dcipher.doFinal(enc);
        String charSet="UTF-8";     
        String plainStr = new String(utf8, charSet);
        return plainStr;
    }    
    public static void main(String[] args) throws Exception {
           
        String enc=EncriptUtil.encryp("truxtest");
        String enc2=EncriptUtil.encryp("trux@123");
        String authentication=enc +"[!]"+enc2;
        System.out.println("Original text: "+enc +"[!]"+enc2);
        System.out.println("Encrypted text: "+enc2);
        String[] userToken= authentication.split("[!]");
		String userNames=userToken[0].replace("[", "");
		String userPasswords=userToken[1].replace("]", "");
		
        String plainAfter=EncriptUtil.decrypt(userNames);
        String plainAfterw=EncriptUtil.decrypt(userPasswords);
        System.out.println("Original text after decryption: "+plainAfter +" "+plainAfterw);
    }
}