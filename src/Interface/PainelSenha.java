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

public class PainelSenha extends JPanel {

	public final int LARG_DEFAULT = 690;
	public final int ALT_DEFAULT = 510;
	public final int COORD_Y=110;
	private BufferedImage i;
	TratadorBotoes tb;
	JTextField login;
	JLabel aviso;

	public PainelSenha() {
		
		tb = new TratadorBotoes();

		try
		{
			i=ImageIO.read(new File("confirmar.png"));
		}
		catch(IOException e)
		{
			System.out.println(e.getMessage());
			System.exit(1);
		}
		
		Font font = new Font("Helvetica", Font.BOLD,18);
				
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

	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    // paint the background image and scale it to fill the entire space
	    g.drawImage(i, 0, 0, getWidth(), getHeight(), this);
	  }
}
