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

public class PainelPrincipal extends JPanel {

	public final int LARG_DEFAULT = 690;
	public final int ALT_DEFAULT = 510;
	public final int COORD_Y=110;
	private BufferedImage i;
	public ConnectButton connect;
	TratadorBotoes tb;
	JTextField login;
	JLabel aviso;
	FramePrincipal fp = FramePrincipal.getInstance();

	public PainelPrincipal() {
		
		tb = new TratadorBotoes();

		try
		{
			i=ImageIO.read(new File("imagens/telas/tela.png"));
		}
		catch(IOException e)
		{
			System.out.println(e.getMessage());
			System.exit(1);
		}
		
		Font font = new Font("Helvetica", Font.BOLD,18);
		
		login = new JTextField();
		login.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		login.setFont(font);
		login.setHorizontalAlignment(SwingConstants.CENTER);
		login.setBounds(205, 320, 265, 22);
		login.setVisible(true);
		this.add(login);
		
		connect = new ConnectButton();
		connect.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		connect.setBounds(290, 364, 94, 42);
		connect.setVisible(true);
		connect.setName("Conectar");
		connect.addMouseListener(tb);
		this.add(connect);
		
		CloseButton close = new CloseButton();
		close.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		close.setBounds(314, 447, 45, 45);
		close.setVisible(true);
		close.setName("Fechar");
		close.addMouseListener(tb);
		this.add(close);
		
		JButton cadastro = new JButton();
		cadastro.setBounds(10, 10, 20, 20);
		cadastro.setVisible(true);
		cadastro.setName("Consultar");
		cadastro.addMouseListener(tb);
		this.add(cadastro);
		
		aviso = new JLabel();
		aviso.setBounds(0, 375, LARG_DEFAULT, 100);
		aviso.setHorizontalAlignment(SwingConstants.CENTER);
		aviso.setFont(new Font("Helvetica", Font.PLAIN,13));
		aviso.setForeground(Color.RED);
		aviso.setVisible(true);
		this.add(aviso);

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
