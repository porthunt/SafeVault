package Utils;

import java.io.File;
import java.io.FileFilter;

public class FileExtensionFilter implements FileFilter{
	String extension;
	String name;
	public FileExtensionFilter (String name, String ex){
		extension = ex;
		this.name = name;
	} 
	
	@Override
	public boolean accept(File fileName) {
        if ( fileName.getName().startsWith(name) && (fileName.getName().endsWith(extension.toLowerCase())) || fileName.getName().endsWith(extension.toUpperCase()))
        {
             return true;
        }
        return false;
	}
}
