package Sistema;

import java.security.MessageDigest;

public class Digest {
	
	public String getDigest(String tipo, String senha) throws Exception {
	    byte[] plainText = senha.getBytes("UTF8");
	    MessageDigest messageDigest = MessageDigest.getInstance(tipo);
	    messageDigest.update(plainText);
	    byte [] digest = messageDigest.digest();
	    
	    StringBuffer buf = new StringBuffer();
	    for(int i = 0; i < digest.length; i++) {
	       String hex = Integer.toHexString(0x0100 + (digest[i] & 0x00FF)).substring(1);
	       buf.append((hex.length() < 2 ? "0" : "") + hex);
	    }

	    return buf.toString();
	  }
}
