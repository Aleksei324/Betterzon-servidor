package clases;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.Cipher;

public class EncriptadoRSA {

//	// texto a encriptar
//	private String text = "Betterzon";
//    
//	// Crear las llaves
//	KeyPair keyPair = crearLlaves();
//
//  // Sacar la llave publica y privada
//  PublicKey publicKey = keyPair.getPublic();
//  PrivateKey privateKey = keyPair.getPrivate();
//        
//  // Encriptar el texto (podria enviarse en byte[] sin problema)
//  byte[] cipherTextArray = encriptar(text, publicKey);
//	String encryptedText = Base64.getEncoder().encodeToString(cipherTextArray);
//
//  // Decifra el texto
//  String decryptedText = decifrar(cipherTextArray, privateKey);
    
    public static KeyPair crearLlaves() {
    	
        KeyPairGenerator keyPairGenerator;
        
		try {
			
			keyPairGenerator = KeyPairGenerator.getInstance("RSA");
			keyPairGenerator.initialize(4096);
			
			return keyPairGenerator.generateKeyPair();
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
        
    } // crearLlaves()
    
    
    public static byte[] encriptar(String plainText, PublicKey publicKey) {
    	
		try {
			
			Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWITHSHA-512ANDMGF1PADDING");
	        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
	        
	        return cipher.doFinal(plainText.getBytes());
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
        
    } // encriptar()
    
    
    public static String decifrar(byte[] cipherTextArray, PrivateKey privateKey) {
    	
		try {

			Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWITHSHA-512ANDMGF1PADDING");
	        cipher.init(Cipher.DECRYPT_MODE, privateKey);
	        byte[] decryptedTextArray = cipher.doFinal(cipherTextArray);
	        
	        return new String(decryptedTextArray);
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
           
    } // decifrar()
	
} // EncriptadoRSA
