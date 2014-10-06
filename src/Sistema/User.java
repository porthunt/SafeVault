package Sistema;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.swing.JLabel;

import DAOs.UserDAO;
import Interface.FramePrincipal;

public class User {

	String nome, login, senha, grupo;
	byte[] chavePublica;
	public int tentativas;
	PrivateKey privKey;
	PublicKey pubKey;
	
	public User() {
		nome = "";
		login = "";
		senha = "";
		grupo = "";
		tentativas=0;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public byte[] getChavePublica() {
		return chavePublica;
	}

	public void setChavePublica(byte[] chavePublica) {
		this.chavePublica = chavePublica;
	}

	public void cadastraUser(String nome, String login, String senha, String grupo, byte[] pub_chave) throws Exception {
		this.nome = nome;
		this.login = login;
		this.senha = senha;
		this.grupo = grupo;
		this.chavePublica = pub_chave;

		try {
			UserDAO cDAO = new UserDAO();
			cDAO.insereUser(this);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception("ID já existente. " + e);
		}

	}
	
	public void setPrivKey(PrivateKey privKey) {
		this.privKey = privKey;
	}

	public PrivateKey getPrivKey() {
		return privKey;
	}

	public boolean confereUser(String login) throws Exception {
		try {
			UserDAO cDAO = new UserDAO();
			Log log = new Log();
			Boolean bool = cDAO.confereUser(login);
			return bool;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception("Erro ao buscar. " + e);
		}

	}
	
	public Integer contaUsers() throws Exception {
		try {
			UserDAO cDAO = new UserDAO();
			return cDAO.contaUsers();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception("Erro ao buscar. " + e);
		}
	}
	
	public User buscarUser(String login) throws Exception {
		try {
			FramePrincipal fp = FramePrincipal.getInstance();
			User usr = new User();
			UserDAO cDAO = new UserDAO();
			usr = cDAO.buscaUser(login);
			usr.privKey = fp.user.privKey;
			return usr;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception("Erro ao buscar. " + e);
		}

	}
	
	public String buscarSaltUser(String login) throws Exception {
		try {
			UserDAO cDAO = new UserDAO();
			return cDAO.buscaSaltUser(login);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception("Erro ao conferir. " + e);
		}

	}
	
	public String buscaHashSenha(String login) throws Exception {
		if (login==null)
			System.exit(1);
		
		try {
			UserDAO cDAO = new UserDAO();
			return cDAO.buscaHashSenha(login);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception("Erro ao conferir. " + e);
		}

	}
	
	public boolean testaChave(String pathChave, String seed) throws Exception {
		FramePrincipal fp = FramePrincipal.getInstance();
		KeyGenerator keyGen = KeyGenerator.getInstance("DES");
		SecureRandom seedRand = SecureRandom.getInstance("SHA1PRNG", "SUN");
		seedRand.setSeed(seed.getBytes());
	    keyGen.init(56, seedRand);
	    Key key = keyGen.generateKey();
	    File file = new File(pathChave);
	    byte[] pubKey;
	    
	    PrivateKey privateKey = decriptaPrivateKey(file, key);
	    
	    try {
			UserDAO cDAO = new UserDAO();
			pubKey = cDAO.buscaChavePubUser(login);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception("Erro ao conferir. " + e);
		}
		
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(pubKey);
		PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);
		
		byte[] random = generateRandomBytes();
		
		Signature signature = Signature.getInstance("MD5WithRSA");
	    signature.initSign(privateKey);
	    signature.update(random);
	    byte[] signatureBytes = signature.sign();
	    signature.initVerify(publicKey);
	    signature.update(random);
	    
	    if(signature.verify(signatureBytes)) {
	    	fp.user.privKey = privateKey;
	    	fp.user.pubKey = publicKey;
			return true;
	    }
	    
		return false;
	}
	
	public byte[] generateRandomBytes() {
		byte[] bytes = new byte[512];
		Random r = new Random();
		r.nextBytes(bytes);
		return bytes;
	}
	
	public PrivateKey decriptaPrivateKey(File file, Key key) throws Exception{
		FramePrincipal fp = FramePrincipal.getInstance();
		byte[] plainText;
		PrivateKey privateKey=null;
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
		byte[] cipherText = getBytesFromFile(file);
		
	    cipher.init(Cipher.DECRYPT_MODE, key);
	    plainText = cipher.doFinal(cipherText);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(plainText);
		
		try {
			privateKey = keyFactory.generatePrivate(privateKeySpec);
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		}
		fp.user.privKey = privateKey;
		return privateKey;
		
	}
	
	public byte[] getBytesFromFile(File file) throws IOException {
		InputStream is = new FileInputStream(file);

		// Get the size of the file
		long length = file.length();

		if (length > Integer.MAX_VALUE) {
			// File is too large
		}

		// Create the byte array to hold the data
		byte[] bytes = new byte[(int)length];

		// Read in the bytes
		int offset = 0;
		int numRead = 0;
		while (offset < bytes.length
				&& (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
			offset += numRead;
		}

		// Ensure all the bytes have been read in
		if (offset < bytes.length) {
			throw new IOException("Could not completely read file "+file.getName());
		}

		// Close the input stream and return bytes
		is.close();
		return bytes;
	}

		
	public boolean testaSenha(String senha, String confirmasenha) {
		if (!senha.equals(confirmasenha))
			return false;
		else {
			for (int i=0; i<senha.length()-2; i++) {
				if (senha.substring(i,i+1).equals(senha.substring(i+1, i+2)))
					return false;
			}

			if (senha.substring(senha.length()-2, senha.length()-1).equals(senha.substring(senha.length()-1, senha.length())))
				return false;
			
			else if (!User.isNumeric(senha))
				return false;
			
			else {
				if (senha.length()!=6)
					return false;
				else
					return true;
			}
		}
	}
	
	public boolean testaIntegridadeSenha(String senha) {

		String salt=null;	
		
		if(senha==null)
			return false;
		
		try {
			salt = this.buscarSaltUser(this.getLogin());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<String[]> chs = new ArrayList<String[]>();

		String[] pt1 = new String[2];
		String[] pt2 = new String[2];
		String[] pt3 = new String[2];
		String[] pt4 = new String[2];
		String[] pt5 = new String[2];
		String[] pt6 = new String[2];
		
		pt1[0] = senha.substring(0, 1);
		pt1[1] = senha.substring(1, 2);
		pt2[0] = senha.substring(2, 3);
		pt2[1] = senha.substring(3, 4);
		pt3[0] = senha.substring(4, 5);
		pt3[1] = senha.substring(5, 6);
		pt4[0] = senha.substring(6, 7);
		pt4[1] = senha.substring(7, 8);
		pt5[0] = senha.substring(8, 9);
		pt5[1] = senha.substring(9, 10);
		pt6[0] = senha.substring(10, 11);
		pt6[1] = senha.substring(11, 12);
		
		chs.add(pt1);
		chs.add(pt2);
		chs.add(pt3);
		chs.add(pt4);
		chs.add(pt5);
		chs.add(pt6);
		
		String[] senhas = new String[64];

		for(int i=0; i<senhas.length; i++) {
			senhas[i] = "";
		}

		int particiona = 32;
		int digit = 0;

		for(int j=0; j<chs.size(); j++) {
			for(int i=0; i<senhas.length; i++) {
				if(i!=0 && i%particiona == 0)
					digit = (digit==0 ? 1 : 0);
				senhas[i] += chs.get(j)[digit];
			}
			particiona /= 2;
			digit = 0;
		}
		
		for(int i=0; i<senhas.length; i++) {
			if(salt!=null) {
				senhas[i]=senhas[i]+salt;
				Digest digest = new Digest();
				try {
					if(digest.getDigest("MD5", senhas[i]).equals(this.buscaHashSenha(this.getLogin()))) {
						return true;
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else {
				System.err.println("Ops! Um erro aconteceu!");
				System.exit(1);
			}
		}
	
		return false;
	}
	
	
	
	public static boolean isNumeric(String str)  
	{  
	  try  
	  {  
	    double d = Double.parseDouble(str);  
	  }  
	  catch(NumberFormatException nfe)  
	  {  
	    return false;  
	  }  
	  return true;  
	}
	
	public void clear() {
		this.login="";
		this.senha="";
	}
	
//	public PrivateKey getPrivateKey () throws Exception {
//		KeyGenerator keyGen = KeyGenerator.getInstance("DES");
//		SecureRandom seedRand = SecureRandom.getInstance("SHA1PRNG", "SUN");
//		seedRand.setSeed("segredo".getBytes());
//	    keyGen.init(56, seedRand);
//	    Key key = keyGen.generateKey();
//	    File file = new File("/userpriv");
//	    
//	    return decriptaPrivateKey(file, key);
//	}

}
