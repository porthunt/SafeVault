package Interface;
import java.security.PrivateKey;
import java.text.ParseException;

import javax.swing.JFrame;

import Sistema.Log;
import Sistema.User;

public class FramePrincipal extends JFrame {

	public final int LARG_DEFAULT = 690;
	public final int ALT_DEFAULT = 510;

	private PainelPrincipal pp;
	private PainelCadastro pc;
	private PainelConsulta pcon;
	private PainelSenha ps;
	private PainelChave pch;
	private PainelLogado pl;
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

	public void cadastraPanel() throws Exception {

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
	
	public PainelPrincipal getPainelPrincipal() {
		return pp;
	}

	public PainelSenha getPainelSenha() {
		return ps;
	}

	public PainelChave getPainelChave() {
		return pch;
	}

	public PainelLogado getPainelLogado() {
		return pl;
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
		if (ps!=null)
			this.remove(ps);
		
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

		ps = new PainelSenha("imagens/telas/Senha.png");
		if(this.user.tentativas==1)
			ps.aviso.setText("Senha inválida. Você possui mais 2 tentativas.");
		else if(this.user.tentativas==2)
			ps.aviso.setText("Senha inválida. Você possui mais 1 tentativa.");
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
			pl = new PainelLogado("Administrador");
		if (grupo.equals("Usuários"))
			pl = new PainelLogado("Usuários");
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
	
	public void consultaPanel() {

		if (pc!=null)
			this.remove(pc);
		if (pp!=null)
			this.remove(pp);
		if (ps!=null)
			this.remove(ps);
		if (pch!=null)
			this.remove(pch);
		if (pl!=null)
			this.remove(pl);
		
		Log log = new Log();
		try {
			log.cadastraLog(8001, this.user.getLogin(), null);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		pcon = new PainelConsulta();
		this.repaint();
		this.add(pcon);
	}


}
