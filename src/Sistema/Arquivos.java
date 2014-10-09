package Sistema;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.Signature;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;

import Interface.FramePrincipal;

public class Arquivos {

	String caminhoPasta = "/Users/porthunt/Pacote-T3/Files/";

	public Arquivos() {

	}

	/* 0 - Erro.
	 * 1 - Autentico.
	 * 2 - Autentico e Integro.
	 */
	public byte[] decriptaArquivo(String arquivo, String extensao, String arqfinal, int flag) throws Exception {
		FramePrincipal fp = FramePrincipal.getInstance();
		File env = new File(caminhoPasta+arquivo.trim()+".env");
		File enc = new File(caminhoPasta+arquivo.trim()+".enc");
		Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
		try {
			/* Abre envelope (.env) com chave privada e usa chave simétrica para decriptar .enc. */
			SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
			random.setSeed(getChave(env));
			KeyGenerator keyGen = KeyGenerator.getInstance("DES");
			keyGen.init(56, random);
			Key chave = keyGen.generateKey();
			cipher.init(Cipher.DECRYPT_MODE, chave);
			byte[] newPlainText = cipher.doFinal(fp.user.getBytesFromFile(enc));
			if(flag==0) {
				FileOutputStream fileDecriptado = new FileOutputStream(caminhoPasta+"decriptados/"+arqfinal+extensao);
				BufferedOutputStream out = new BufferedOutputStream(fileDecriptado);
				out.write(newPlainText);
				out.close();
			}
			return newPlainText;
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}

	public boolean checaIntegridade(String arquivo, byte[] newFile) throws Exception {
		File asd = new File(caminhoPasta+arquivo.trim()+".asd");
		FramePrincipal fp = FramePrincipal.getInstance();
		try {
			Signature s = Signature.getInstance("MD5WithRSA");
			s.initVerify(fp.user.pubKey);
			s.update(newFile);
			return s.verify(fp.user.getBytesFromFile(asd));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static StringBuffer createDigest(byte[] plainText) throws Exception {
		MessageDigest messageDigest = MessageDigest.getInstance("MD5");
		messageDigest.update(plainText);
		byte [] digest = messageDigest.digest();
		StringBuffer buf = new StringBuffer();
		for(int i = 0; i < digest.length; i++) {
			String hex = Integer.toHexString(0x0100 + (digest[i] & 0x00FF)).substring(1);
			buf.append((hex.length() < 2 ? "0" : "") + hex);
		}
		return buf;
	}

	public byte[] getChave(File env) throws Exception {
		FramePrincipal fp = FramePrincipal.getInstance();
		try {
			Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
			byte[] cipherText = fp.user.getBytesFromFile(env);
			cipher.init(Cipher.DECRYPT_MODE, fp.user.privKey);
			byte[] plainText = cipher.doFinal(cipherText);
			return plainText;
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<String> ReadFileAsList (String file) {
		String fileName = caminhoPasta+"decriptados/"+file+".txt";
		List<String> lines = null;
		try {
			lines = Files.readAllLines(Paths.get(fileName), Charset.defaultCharset());
			return lines;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lines;
	}

	public String getCaminhoPasta() {
		return caminhoPasta;
	}

	public void removeArqDecriptados() {
		String decript = caminhoPasta+"decriptados/";
		File f = new File(decript);
		Log log = new Log();
		FramePrincipal fp = FramePrincipal.getInstance();
		if(f.isDirectory()) {
			File[] files = f.listFiles();
			for (File file : files) {
				try {
					file.delete();
				} catch (Exception e) {
					try {
						log.cadastraLog(9005, fp.user.getLogin(), file.getName());
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}

			if(fp.user.getLogin()!="") {
				try {
					log.cadastraLog(9004, fp.user.getLogin(), null);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
	}

}
