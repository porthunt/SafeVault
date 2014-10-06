package Interface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Tratadores.TratadorBotoes;

public class PainelLogado extends JPanel {

	public final int LARG_DEFAULT = 690;
	public final int ALT_DEFAULT = 510;
	public final int COORD_Y=110;
	private BufferedImage i;
	TratadorBotoes tb;
	JTextField login;
	JLabel aviso;

	public PainelLogado(String grupoIdent) {

		tb = new TratadorBotoes();

		try
		{
			if(grupoIdent.equals("Administrador"))
				i=ImageIO.read(new File("imagens/telas/PainelAdmin.png"));
			else
				i=ImageIO.read(new File("imagens/telas/PainelUsuario.png"));
		}
		catch(IOException e)
		{
			System.out.println(e.getMessage());
			System.exit(1);
		}

		Font font = new Font("Helvetica", Font.BOLD,16);
		FramePrincipal fp = FramePrincipal.getInstance();

		JLabel login = new JLabel(fp.user.getLogin());
		login.setFont(font);
		login.setForeground(Color.WHITE);
		login.setBounds(90,15,50, 20);
		login.setVisible(true);
		this.add(login);

		JLabel grupo = new JLabel(fp.user.getGrupo());
		grupo.setFont(font);
		grupo.setForeground(Color.WHITE);
		grupo.setBounds(90,50,150, 20);
		grupo.setVisible(true);
		this.add(grupo);

		JLabel nome = new JLabel(fp.user.getNome());
		nome.setFont(font);
		nome.setForeground(Color.WHITE);
		nome.setBounds(300,40,350, 20);
		nome.setVisible(true);
		this.add(nome);

		if(grupoIdent.equals("Administrador")) {
			ActionButton cadastrar = new ActionButton("imagens/botoes/cadastrarMenu.png");
			cadastrar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
			cadastrar.setBounds(11, 152, 283, 25);
			cadastrar.setVisible(true);
			cadastrar.setName("Cadastrar");
			cadastrar.addMouseListener(tb);
			this.add(cadastrar);
		}

		ActionButton consultar = new ActionButton("imagens/botoes/ConsultarMenu.png");
		consultar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		consultar.setBounds(13, 196, 375, 25);
		consultar.setVisible(true);
		consultar.setName("Consultar");
		consultar.addMouseListener(tb);
		this.add(consultar);

		ActionButton sair = new ActionButton("imagens/botoes/SairMenu.png");
		sair.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		sair.setBounds(15, 241, 175, 22);
		sair.setVisible(true);
		sair.setName("FecharMenu");
		sair.addMouseListener(tb);
		this.add(sair);

		CloseButton close = new CloseButton();
		close.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		close.setBounds(314, 447, 45, 45);
		close.setVisible(true);
		close.setName("Fechar");
		close.addMouseListener(tb);
		this.add(close);

		this.setLayout(null);
		Dimension size = new Dimension(i.getWidth(null), i.getHeight(null));
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		setSize(size);

	}

	public JTextField getLogin() {
		return login;
	}

	public JLabel getAviso() {
		return aviso;
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// paint the background image and scale it to fill the entire space
		g.drawImage(i, 0, 0, getWidth(), getHeight(), this);
	}
}
