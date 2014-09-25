package Utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.KeyGenerator;

import java.security.SecureRandom;

public class Seguranca {
	
	public static Key getKeyFromEnvelope(String dirPath, String fileNameNoExt, PrivateKey kPriv) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException{
	      byte[] seed = Seguranca.getSeed(dirPath, fileNameNoExt, kPriv);
		    SecureRandom random = new SecureRandom(seed);
	        KeyGenerator keyGen = KeyGenerator.getInstance("DES");
	        keyGen.init(56, random);
	        return keyGen.generateKey();
	}
	
	public static void decryptFile(String dirPath, String fileNameIn, Key key, String fileNameOut) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException{

        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        File encriptado = getArqEncriptadoFile(dirPath, fileNameIn);
		byte[] cipherText = Arquivo.readBytesFromFile(encriptado);
	    
	    cipher.init(Cipher.DECRYPT_MODE, key);
	    byte[] newPlainText = cipher.doFinal(cipherText);
	    
	    String dirPathD = "C:\\myworkspace\\INF1416TRAB3MARCOMBAT\\decriptados"; 
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(dirPathD +"\\"+ fileNameOut));
//        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(dirPath + "\\" + fileNameOut));
        out.write(newPlainText);
        out.close();
	}
	
	public static boolean verifyDecryptedFile(String dirPath, String dirPathDecript, String fileNameNoExt, PublicKey kPub, String fileNameDecrypted) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException{
		File file = getAssinaturaDigitalFile(dirPath, fileNameNoExt);
		byte[] digitalSignatureText = Arquivo.readBytesFromFile(file);
		byte[] plainText = Arquivo.readBytesFromFile(new File(dirPathDecript + "\\" + fileNameDecrypted));
	    //
	    // verifica a assinatura com a chave publica
	    Signature sig = Signature.getInstance("MD5WithRSA");
	    sig.initVerify(kPub);
	    sig.update(plainText);
	    
      return sig.verify(digitalSignatureText);
	}
	
	public static File getEnvelopeDigitalFile(String dirPath, String fileNameNoExt) {
		File dir = new File(dirPath);
		File[] files = dir.listFiles(new FileExtensionFilter(fileNameNoExt,".env") );
		return files[0];
	}

	public static File getAssinaturaDigitalFile(String dirPath, String fileNameNoExt) {
		File dir = new File(dirPath);
		File[] files = dir.listFiles(new FileExtensionFilter(fileNameNoExt,".asd"));
		return files[0];
	}
	
	public static File getArqEncriptadoFile(String dirPath, String fileNameNoExt) {
		File dir = new File(dirPath);
		File[] files = dir.listFiles(new FileExtensionFilter(fileNameNoExt,".enc"));
		return files[0];
	}
	
	public static File getChavePrivadaFile(String filePath){
		return new File(filePath);
	}

	private static byte[] getSeed(String dirPath, String fileNameNoExt, PrivateKey kPriv) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException{
		byte[] newPlainText = null;
		File file = getEnvelopeDigitalFile(dirPath, fileNameNoExt);
		byte[] envelopeText = Arquivo.readBytesFromFile(file);

	    // desencripta o texto cifrado utilizando a chave privada
	    Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
	    cipher.init(Cipher.DECRYPT_MODE, kPriv);
	    newPlainText = cipher.doFinal(envelopeText);
		return  newPlainText;
	}
	
	
}
