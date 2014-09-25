package Interface;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;

public class TileButton extends JButton {
	
	private BufferedImage i;

	public TileButton(Integer n1, Integer n2)
	{
		try
		{
			i=ImageIO.read(new File("tile.png"));
		}
		catch(IOException e)
		{
			System.out.println(e.getMessage());
			System.exit(1);
		}
		
		JLabel num1 = new JLabel(n1.to)
		this.setLayout(null);
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(i, 0, 0, null);
	}
}