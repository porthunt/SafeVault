package Interface;
import java.text.ParseException;

import javax.swing.JFrame;

import Sistema.Log;
import Sistema.User;
import Sistema.Version;
import Tratadores.TratadorBotoes;

public class FramePrincipal extends JFrame {

	public final int LARG_DEFAULT = 690;
	public final int ALT_DEFAULT = 510;

	private PainelPrincipal pp;
	private PainelCadastro pc;
	private PainelSenha ps;
	private PainelChave pch;
	private PainelLogadoAdmin pl;
	public User user;
	String version = "0.5";

	private static final FramePrincipal INSTANCE = new FramePrincipal();

	private FramePrincipal() {
		this.setTitle("SafeVault v"+version);
		this.setBounds(0, 0, LARG_DEFAULT, ALT_DEFAULT);
		this.setLocationRelativeTo(null);
		this.setTitle("SafeVault");
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
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
		if (ps!=null)
			this.remove(ps);
		if (pch!=null)
			this.remove(pch);
		if (pl!=null)
			this.remove(pl);

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
		if (ps!=null)
			this.remove(ps);
		if (pch!=null)
			this.remove(pch);
		if (pl!=null)
			this.remove(pl);

		Log log = new Log();
		try {
			log.cadastraLog(2001, null, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.repaint();
		this.add(pp);

	}

	public void senhaPanel() {

		if (pc!=null)
			this.remove(pc);
		if (pp!=null)
			this.remove(pp);
		if (pch!=null)
			this.remove(pch);
		if (pl!=null)
			this.remove(pl);

		Log log = new Log();
		try {
			log.cadastraLog(3001, this.user.getLogin(), null);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		ps = new PainelSenha("imagens/telas/Senha.png");
		this.repaint();
		this.add(ps);
	}

	public void chavePanel() {

		if (pc!=null)
			this.remove(pc);
		if (pp!=null)
			this.remove(pp);
		if (ps!=null)
			this.remove(ps);
		if (pl!=null)
			this.remove(pl);
		
		Log log = new Log();
		try {
			log.cadastraLog(4001, this.user.getLogin(), null);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		pch = new PainelChave();
		this.repaint();
		this.add(pch);
	}
	
	public void logadoPainel(String grupo) {

		if (pc!=null)
			this.remove(pc);
		if (pp!=null)
			this.remove(pp);
		if (ps!=null)
			this.remove(ps);
		if (pch!=null)
			this.remove(pch);
		if (grupo.equals("Administrador"))
			pl = new PainelLogadoAdmin();
		//else if (grupo.equals("Usuário"))
		//	pl = new PainelLogadoUser();
		Log log = new Log();
		try {
			log.cadastraLog(5001, this.user.getLogin(), null);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		this.repaint();
		this.add(pl);
	}

}
