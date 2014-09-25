package Interface;

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

import Sistema.RandomNumber;
import Tratadores.TratadorBotoes;

public class PainelSenha extends JPanel {

	public final int LARG_DEFAULT = 690;
	public final int ALT_DEFAULT = 510;
	public final int COORD_Y=110;
	private BufferedImage i;
	TratadorBotoes tb;
	JTextField login;
	JLabel aviso;
	RandomNumber rn = new RandomNumber();

	public PainelSenha() {
		
		tb = new TratadorBotoes();

		try
		{
			i=ImageIO.read(new File("Senha.png"));
		}
		catch(IOException e)
		{
			System.out.println(e.getMessage());
			System.exit(1);
		}
		
		Font font = new Font("Helvetica", Font.BOLD,18);
		
		TileButton tile1 = new TileButton();
		tile1.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		tile1.setBounds(264, 350, 25, 25);
		tile1.setVisible(true);
		tile1.setName("Tile1");
		tile1.addMouseListener(tb);
		this.add(tile1);
		
		TileButton tile2 = new TileButton();
		tile2.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		tile2.setBounds(tile1.getX()+31, 350, 25, 25);
		tile2.setVisible(true);
		tile2.setName("Tile2");
		tile2.addMouseListener(tb);
		this.add(tile2);
		
		TileButton tile3 = new TileButton();
		tile3.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		tile3.setBounds(tile2.getX()+31, 350, 25, 25);
		tile3.setVisible(true);
		tile3.setName("Tile3");
		tile3.addMouseListener(tb);
		this.add(tile3);
		
		TileButton tile4 = new TileButton();
		tile4.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		tile4.setBounds(tile3.getX()+31, 350, 25, 25);
		tile4.setVisible(true);
		tile4.setName("Tile4");
		tile4.addMouseListener(tb);
		this.add(tile4);
		
		TileButton tile5 = new TileButton();
		tile5.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		tile5.setBounds(tile4.getX()+31, 350, 25, 25);
		tile5.setVisible(true);
		tile5.setName("Tile5");
		tile5.addMouseListener(tb);
		this.add(tile5);
				
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
