package Sistema;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;

import Interface.FramePrincipal;

public class Arquivos {

	String caminhoPasta = "/Users/porthunt/Pacote-T3/Files/";

	public Arquivos() {

	}

	public void decriptaArquivo(String arquivo) throws Exception {
		FramePrincipal fp = FramePrincipal.getInstance();
		File env = new File(caminhoPasta+arquivo+".env");
		File enc = new File(caminhoPasta+arquivo+".enc");
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
			FileOutputStream fileDecriptado = new FileOutputStream(caminhoPasta+"decriptados/"+arquivo+".txt");
			BufferedOutputStream out = new BufferedOutputStream(fileDecriptado);
			out.write(newPlainText);
			out.close();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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

}
