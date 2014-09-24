package Utils;

import java.io.File;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.swing.JOptionPane;

public class Chave {
	
	public static PrivateKey readPrivateKeyFromFile(File file, String chaveSecreta)throws Exception {
		try {
		    SecureRandom random = new SecureRandom(chaveSecreta.getBytes());
	        KeyGenerator keyGen = KeyGenerator.getInstance("DES");
	        keyGen.init(56, random);
	        Key key = keyGen.generateKey();
			
			byte[] privateKeyBytes = decryptPrivateKeyFile(file, key);
			
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
			PrivateKey privateKey = null;
			try {
				privateKey = keyFactory.generatePrivate(privateKeySpec);
			} catch (InvalidKeySpecException e) {
				//JOptionPane.showMessageDialog(null, "Falha ao tentar ler a chave privada.");
			}
			return privateKey;
		} catch (NoSuchAlgorithmException e) {
			//JOptionPane.showMessageDialog(null, "Falha ao tentar ler a chave privada.");
		}
		return null;
	}
	
	public static PublicKey readPublicKeyFromBytes(byte[] publicKeyBytes) throws Exception {


		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyBytes);
		PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);
		return publicKey;
	
	}
	
	public static PublicKey readPublicKeyFromFile(File file) throws Exception  {
		return readPublicKeyFromBytes(Arquivo.readBytesFromFile(file));
	}
	
	public static byte[] decryptPrivateKeyFile(File file, Key key) throws Exception{
		 byte[] newPlainText = null;
		
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        System.out.println( "\n" + cipher.getProvider().getInfo() );
		byte[] cipherText = Arquivo.readBytesFromFile(file);
	    
	    System.out.println( "\nStart private key decryption" );
	    cipher.init(Cipher.DECRYPT_MODE, key);
	    newPlainText = cipher.doFinal(cipherText);
	    System.out.println( "Finish private key decryption" );
        
		return newPlainText;
		
	}

}
