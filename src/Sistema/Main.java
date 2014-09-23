package Sistema;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Connection;

import javax.swing.ImageIcon;

import DAOs.UserDAO;
import Interface.FramePrincipal;

import com.apple.eawt.Application;


public class Main {

	private Connection con;
	
	public static void main(String[] args) {
		
		Application application = Application.getApplication();
		Image image = Toolkit.getDefaultToolkit().getImage("safevault.png");
		application.setDockIconImage(image);
		FramePrincipal frameprincipal = FramePrincipal.getInstance();
		
		try {
			UserDAO cdao = new UserDAO();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}

}
