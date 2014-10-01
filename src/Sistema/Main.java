package Sistema;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Connection;

import DAOs.LogDAO;
import DAOs.TriesDAO;
import DAOs.UserDAO;
import Interface.FramePrincipal;

import com.apple.eawt.Application;


public class Main {

	private Connection con;
	
	public static void main(String[] args) {
		
		Application application = Application.getApplication();
		Image image = Toolkit.getDefaultToolkit().getImage("imagens/logo.png");
		application.setDockIconImage(image);
		FramePrincipal frameprincipal = FramePrincipal.getInstance();
		
		try {
			TriesDAO tdao = new TriesDAO();
			tdao.removeTentativas();
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
