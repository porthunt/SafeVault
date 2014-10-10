package Sistema;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Connection;

import DAOs.LogDAO;
import DAOs.UserDAO;
import Interface.FramePrincipal;

import com.apple.eawt.Application;


public class Main {

	private Connection con;

	public static void main(String[] args) {
		
		File f = new File("acesso.log");
		if(f.exists()) {
			f.delete();
		}

		Application application = Application.getApplication();
		Image image = Toolkit.getDefaultToolkit().getImage("imagens/logo.png");
		application.setDockIconImage(image);
		FramePrincipal fp = FramePrincipal.getInstance();
		
		System.out.println("\tDATA\t\t\tID\tDESCRICAO");

		try {
			UserDAO cdao = new UserDAO();
			LogDAO ldao = new LogDAO();
			Log log = new Log();
			log.geraLog();
			log.cadastraLog(1001, null, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
