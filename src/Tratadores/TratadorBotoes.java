package Tratadores;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Interface.FramePrincipal;
import Interface.PainelCadastro;
import Sistema.User;

public class TratadorBotoes implements MouseListener {

	public void mouseClicked(MouseEvent arg0) {

		FramePrincipal fp = FramePrincipal.getInstance();
		JFrame frame = new JFrame();

		if (arg0.getComponent().getName().equals("Cadastrar"))
		{
			fp.cadastraPanel();
		}
		
		else if (arg0.getComponent().getName().equals("Confirmar"))
		{	
			PainelCadastro pc = (PainelCadastro) arg0.getComponent().getParent();
			User user = new User();

			if (!pc.getTextnome().getText().equals("")
					&& !pc.getTextlogin().getText().equals("")
					&& !pc.getTextsenha().getText().equals("")
					&& !pc.getTextconfirmasenha().getText().equals("")
					&& !pc.getTextgrupo().getSelectedItem().equals("")
					&& !pc.getTextcaminhochave().getText().equals(""))
			{
				if (user.testaSenha(pc.getTextsenha().getText(), pc.getTextconfirmasenha().getText())) {
					String nome = pc.getTextnome().getText();
					String login = pc.getTextlogin().getText();
					String senha = pc.getTextsenha().getText();
					String confirmasenha = pc.getTextconfirmasenha().getText();
					String grupo = (String) pc.getTextgrupo().getSelectedItem();
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
		// TODO Auto-generated method stub

	}
}
