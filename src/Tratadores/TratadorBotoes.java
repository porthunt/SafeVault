package Tratadores;

import java.awt.HeadlessException;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import DAOs.TriesDAO;
import Interface.FramePrincipal;
import Interface.PainelCadastro;
import Interface.PainelChave;
import Interface.PainelPrincipal;
import Interface.PainelSenha;
import Sistema.Log;
import Sistema.User;

public class TratadorBotoes implements MouseListener {

	public void mouseClicked(MouseEvent arg0) {

		FramePrincipal fp = FramePrincipal.getInstance();
		User user = new User();
		JFrame frame = new JFrame();

		if (arg0.getComponent().getName().equals("Cadastrar"))
		{
			Log log = new Log();
			try {
				log.cadastraLog(5002, fp.user.getLogin(), null);
				log.cadastraLog(6001, fp.user.getLogin(), null);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			fp.cadastraPanel();
		}
		
		if (arg0.getComponent().getName().equals("Admin"))
		{
			fp.logadoPainel("Administrador");
		}
		
		if (arg0.getComponent().getName().equals("ConfirmaSenha"))
		{
			PainelSenha ps = (PainelSenha) arg0.getComponent().getParent();
			String login = fp.user.getLogin();
			Log log = new Log();
			
			if (fp.user.testaIntegridadeSenha(fp.user.getSenha())) {
				try {
					log.cadastraLog(3003, fp.user.getLogin(), null);
					log.cadastraLog(3002, fp.user.getLogin(), null);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				fp.chavePanel();
			} else {
				try {
					log.cadastraLog(3004, fp.user.getLogin(), null);
					TriesDAO tDAO = new TriesDAO();
					tDAO.insereTentativa(login);
					int tentativas = tDAO.buscaTentativa(login);
					
					if (tentativas>0 && tentativas<3) {
						ps.i=ImageIO.read(new File("Senha.png"));
						fp.user.setSenha("");
						ps.limpaSenha();
						ps.limpaConectar();
						fp.senhaPanel();
						ps.repaint();
						JOptionPane.showMessageDialog(frame, "Senha incorreta!", "", JOptionPane.OK_OPTION);
						ps.getAviso().setText("Senha inválida.");
						if(tentativas==1)
							log.cadastraLog(3005, fp.user.getLogin(), null);
						else if(tentativas==2)
							log.cadastraLog(3006, fp.user.getLogin(), null);
					}  else {
						log.cadastraLog(3007, fp.user.getLogin(), null);
						JOptionPane.showMessageDialog(frame, "Programa bloqueado para o usuário '"+fp.user.getLogin()+"'!", "", JOptionPane.OK_OPTION);
						log.cadastraLog(3008, fp.user.getLogin(), null);
						System.exit(1);
					}
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
		if (arg0.getComponent().getName().equals("ConectarChave"))
		{
			PainelChave pch = (PainelChave) arg0.getComponent().getParent();
			Log log = new Log();
			
			try {
				if (fp.user.testaChave(pch.getChavePrivada(), pch.getSeed())) {
					log.cadastraLog(4003, fp.user.getLogin(), null);
					JOptionPane.showMessageDialog(frame, "Logado com sucesso!", "", JOptionPane.PLAIN_MESSAGE);
					log.cadastraLog(4002, fp.user.getLogin(), null);
					fp.user = fp.user.buscarUser(fp.user.getLogin());
					fp.logadoPainel(fp.user.getGrupo());
				} else {
					JOptionPane.showMessageDialog(frame, "Chave incorreta!", "", JOptionPane.OK_OPTION);
				}
			} catch (HeadlessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		else if (arg0.getComponent().getName().equals("Conectar"))
		{
			PainelPrincipal pp = (PainelPrincipal) arg0.getComponent().getParent();
			String login = pp.getLogin().getText();
			
			try {
				Log log = new Log();
				
				if(user.confereUser(login)) {
					fp.user.setLogin(login);
					log.cadastraLog(2001, null, null);
					log.cadastraLog(2003, login, null);
					log.cadastraLog(2002, null, null);
					fp.senhaPanel();
				} else {
					log.cadastraLog(2005, login, null);
					pp.repaint();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		else if (arg0.getComponent().getName().equals("Fechar"))
		{
			Log log = new Log();
			try {
				log.cadastraLog(9002, fp.user.getLogin(), null);
				log.cadastraLog(1002, null, null);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.exit(1);
		}
		
		else if (arg0.getComponent().getName().equals("FecharMenu"))
		{
			Log log = new Log();
			try {
				log.cadastraLog(5004, fp.user.getLogin(), null);
				log.cadastraLog(1002, null, null);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.exit(1);
		}
		
		else if (arg0.getComponent().getName().equals("Consultar"))
		{
			Log log = new Log();
			try {
				log.cadastraLog(5003, fp.user.getLogin(), null);
				log.cadastraLog(8001, fp.user.getLogin(), null);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Consultar");
		}
		
		else if (arg0.getComponent().getName().equals("ConfirmarCadastro"))
		{	
			PainelCadastro pc = (PainelCadastro) arg0.getComponent().getParent();
			Log log = new Log();
			try {
				log.cadastraLog(6002, fp.user.getLogin(), null);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			if (!pc.getTextnome().getText().equals("")
					&& !pc.getTextlogin().getText().equals("")
					&& !pc.getTextsenha().getText().equals("")
					&& !pc.getTextconfirmasenha().getText().equals("")
					&& !pc.getTextgrupo().getText().equals("")
					&& !pc.getTextcaminhochave().getText().equals(""))
			{
				if (user.testaSenha(pc.getTextsenha().getText(), pc.getTextconfirmasenha().getText())) {
					String nome = pc.getTextnome().getText();
					String login = pc.getTextlogin().getText();
					String senha = pc.getTextsenha().getText();
					String confirmasenha = pc.getTextconfirmasenha().getText();
					String grupo = (String) pc.getTextgrupo().getText();
					String caminhochave = pc.getTextcaminhochave().getText();

					//pc.bordaNormal();
					try {
						fp.user.cadastraUser(nome, login, senha.toString(), grupo);
						JOptionPane.showMessageDialog(frame, "Cadastrado com sucesso!", "", JOptionPane.PLAIN_MESSAGE);
					} catch (Exception e) {
						JOptionPane.showMessageDialog(frame, "ID já existente!", "", JOptionPane.OK_OPTION);
					}
					fp.principalPanel();
				} else {
					JOptionPane.showMessageDialog(frame, "A senha não é válida!", "", JOptionPane.OK_OPTION);
				}
			}
			else
				JOptionPane.showMessageDialog(frame, "Preencha os campos destacados!", "", JOptionPane.OK_OPTION);

		}

		else if (arg0.getComponent().getName().equals("Voltar"))
		{	
			Log log = new Log();
			try {
				log.cadastraLog(6003, fp.user.getLogin(), null);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			fp.logadoPainel(fp.user.getGrupo());
		}

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		
		
	}
}
