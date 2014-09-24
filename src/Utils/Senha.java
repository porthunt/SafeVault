package Utils;

public class Senha {
	
	private final static int tamanho = 6;
	

	public static boolean verificaTamanho(String senha){
		return senha.length() == tamanho;
	}
	
	
	public static boolean verificaSequencia(String senha) {
		int i = 1, num1, num2, j=0;
		for(i=1; i < senha.length(); i++, j++){
			num1 = senha.charAt(j);
			num2 = senha.charAt(i);
			if(num1 != num2){
				return false;
			}
		}
		return true;
	}
	
	public static String toHex(byte[] x) throws Exception{
	    StringBuffer buf = new StringBuffer();
	    for(int i = 0; i < x.length; i++) {
	       String hex = Integer.toHexString(0x0100 + (x[i] & 0x00FF)).substring(1);
	       buf.append((hex.length() < 2 ? "0" : "") + hex);
	    }
	    return buf.toString();
	}
}
