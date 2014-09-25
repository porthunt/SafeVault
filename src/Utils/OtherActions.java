package Utils;

import java.io.File;

public class OtherActions {
	
	private void removeArquivosDecriptados()
	{
		String dirPath = "C:\\myworkspace\\INF1416TRAB3MARCOMBAT\\decriptados";
		File f = new File(dirPath);
		if(f.isDirectory())
		{
			File[] files = f.listFiles();
			for (File file : files) {
				file.delete();
			}
		}
	}

}
