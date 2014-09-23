package Interface;
import java.text.ParseException;

import javax.swing.JFrame;

import Sistema.User;
import Sistema.Version;
import Tratadores.TratadorBotoes;

public class FramePrincipal extends JFrame {

	public final int LARG_DEFAULT = 690;
	public final int ALT_DEFAULT = 510;
	
	private PainelPrincipal pp;
	private PainelCadastro pc;
	public User user;
	
	private static final FramePrincipal INSTANCE = new FramePrincipal();
	
	private FramePrincipal() {
		Version v = new Version();
		this.setTitle("SafeVault v."+v.getVersion());
		this.setBounds(0, 0, LARG_DEFAULT, ALT_DEFAULT);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLayout(null);
		
		user = new User();
		
		pp = new PainelPrincipal();
		this.add(pp);
		this.setVisible(true);
	}

	public static synchronized FramePrincipal getInstance() {
		return INSTANCE;
	}
	
	public void cadastraPanel() {
		
		if (pp!=null)
			this.remove(pp);
		
		try {
			pc = new PainelCadastro();
			this.repaint();
			this.add(pc);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void principalPanel() {
		
		if (pc!=null)
			this.remove(pc);
		
		this.repaint();
		this.add(pp);
		
	}
	
}
