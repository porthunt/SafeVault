package Interface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Tratadores.TratadorBotoes;

public class PainelChave extends JPanel {

	public final int LARG_DEFAULT = 690;
	public final int ALT_DEFAULT = 510;
	public final int COORD_Y=110;
	public BufferedImage i;
	TratadorBotoes tb;
	FramePrincipal fp = FramePrincipal.getInstance();
	String pathChavePrivada, seed;
	ActionButton go;
	JTextField pathChave, passphrase;
	JLabel aviso;

	public PainelChave() {

		tb = new TratadorBotoes();

		try
		{
			i=ImageIO.read(new File("imagens/telas/Chave.png"));
		}
		catch(IOException e)
		{
			System.out.println(e.getMessage());
			System.exit(1);
		}

		Font font = new Font("Helvetica", Font.BOLD,14);

		pathChave = new JTextField();
		pathChave.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		pathChave.setFont(font);
		pathChave.setHorizontalAlignment(SwingConstants.CENTER);
		pathChave.setBounds(163, 293, 355, 32);
		pathChave.setVisible(true);
		this.add(pathChave);
		
		passphrase = new JTextField();
		passphrase.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		passphrase.setFont(font);
		passphrase.setHorizontalAlignment(SwingConstants.CENTER);
		passphrase.setBounds(163, 364, 355, 32);
		passphrase.setVisible(true);
		this.add(passphrase);
		
		CloseButton close = new CloseButton();
		close.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		close.setBounds(314, 447, 45, 45);
		close.setVisible(true);
		close.setName("Fechar");
		close.addMouseListener(tb);
		this.add(close);
		
		ConnectButton connect = new ConnectButton();
		connect.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		connect.setBounds(290, 402, 94, 42);
		connect.setVisible(true);
		connect.setName("ConectarChave");
		connect.addMouseListener(tb);
		this.add(connect);

		aviso = new JLabel();
		aviso.setBounds(0, 198, LARG_DEFAULT, 100);
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

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// paint the background image and scale it to fill the entire space
		g.drawImage(i, 0, 0, getWidth(), getHeight(), this);
	}
	
	public JLabel getAviso() {
		return aviso;
	}

	public void setAviso(JLabel aviso) {
		this.aviso = aviso;
	}

	public ActionButton getGo() {
		return go;
	}

	public String getChavePrivada() {
		return pathChave.getText();
	}
	
	public String getSeed() {
		return passphrase.getText();
	}

}
