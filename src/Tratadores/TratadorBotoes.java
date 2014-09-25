package Tratadores;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import DAOs.TriesDAO;
import Interface.FramePrincipal;
import Interface.PainelCadastro;
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
			fp.cadastraPanel();
		}
		
		if (arg0.getComponent().getName().equals("ConfirmaSenha"))
		{
			PainelSenha pp = (PainelSenha) arg0.getComponent().getParent();
			if (fp.user.testaIntegridadeSenha(fp.user.getSenha())) {
				fp.cadastraPanel();
			} else {
				JOptionPane.showMessageDialog(frame, "Senha incorreta!", "", JOptionPane.OK_OPTION);
			}
		}
		
		else if (arg0.getComponent().getName().equals("Conectar"))
		{
			PainelPrincipal pp = (PainelPrincipal) arg0.getComponent().getParent();
			String login = pp.getLogin().getText();
			
			try {
				if(user.buscarUser(login)) {
					fp.user.setLogin(login);
					fp.senhaPanel();
				} else {
					TriesDAO tDAO = new TriesDAO();
					tDAO.insereTentativa(login);
					int tentativas = tDAO.buscaTentativa(login);
					if (tentativas == 2) {
						pp.getAviso().setText("Usuário inválido. Você possui mais 1 tentativa.");
					} else if (tentativas>0 && tentativas<2) {
						pp.getAviso().setText("Usuário inválido. Você possui mais "+(3-tentativas)+" tentativas.");
					} else {
						pp.getAviso().setText("Programa bloqueado.");
					}
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
				log.cadastraLog(1002, null);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.exit(0);
		}
		
		else if (arg0.getComponent().getName().equals("Confirmar"))
		{	
			PainelCadastro pc = (PainelCadastro) arg0.getComponent().getParent();

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
			fp.principalPanel();
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
