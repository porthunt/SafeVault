package Utils;
 
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Arquivo {

	public static byte[] readBytesFromFile(File file) {
		try {
			FileInputStream fin = new FileInputStream(file);
			byte fileContent[] = new byte[(int) file.length()];
			fin.read(fileContent);
			return fileContent;

		} catch (FileNotFoundException e) {
			System.out.println("Arquivo nao foi encontrado" + e);
		} catch (IOException ioe) {
			System.out.println("Erro na leitura do arquivo " + ioe);
		}
		return null;
	}
	
	public static String fileToHex(File file){
		byte[] fileBytes = null;
		String ret = "";
		try {
			fileBytes = readBytesFromFile(file);
			ret = toHex(fileBytes);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}

	private static String toHex(byte[] x) throws Exception{
	    StringBuffer buf = new StringBuffer();
	    for(int i = 0; i < x.length; i++) {
	       String hex = Integer.toHexString(0x0100 + (x[i] & 0x00FF)).substring(1);
	       buf.append((hex.length() < 2 ? "0" : "") + hex);
	    }
	    return buf.toString();
	}
	
	public static String getFileNameNoExtension(String fileName){
	       String s[] = fileName.split("\\."); 
	       
	       if(s == null)
	    	   return "";
	       
	       if(s.length > 2){
	    	   for(int i = 1; i < s.length-1; i++){
	    		   s[0] = s[0]+"."+s[i];
	    	   }
	       }
	       return s[0];
	}
}
