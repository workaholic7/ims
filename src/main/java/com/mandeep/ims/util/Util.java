package com.mandeep.ims.util;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Util {
	
	@Value("${data.encrypt}")
    private String encryptData;
 
    private static String ENCRYPT_DATA;
 
    @Value("${data.encrypt}")
    public void setEncryptData(String encryptData){
    	Util.ENCRYPT_DATA = encryptData;
    }
	
	private static final String KEY="5Lf3gX4njEnTtP4U";
	private static final String SALT="jMlsBnjvJ1P95Bga";
	private static SecretKeySpec secretKey;

	public static String formatLocalDateTime(LocalDateTime dateTime) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		return dateTime.format(formatter);
	}

	public static void setKey() 
	{
		try {

			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
			KeySpec spec = new PBEKeySpec(KEY.toCharArray(), SALT.getBytes(), 65536, 256);
			SecretKey tmp = factory.generateSecret(spec);
			secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");
		} 
		catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} 
		catch (InvalidKeySpecException e) {
			e.printStackTrace();
		}
	}

	public static String encrypt(String strToEncrypt) {
		if(ENCRYPT_DATA.equalsIgnoreCase("true")) {
			try{
				
				setKey();
				Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
				cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(new byte[16]));
				return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
			} 
			catch (Exception e) 
			{
				System.out.println("Error while encrypting: " + e.toString());
			}
			return null;
		} else {
			return strToEncrypt;
		}
	}

	public static String decrypt(String strToDecrypt) {
		if(ENCRYPT_DATA.equalsIgnoreCase("true")) {
			try{
				setKey();
				Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
				cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(new byte[16]));
				return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
			} 
			catch (Exception e) 
			{
				System.out.println("Error while decrypting: " + e.toString());
			}
			return null;
		} else {
			return strToDecrypt;
		}
	}

}
