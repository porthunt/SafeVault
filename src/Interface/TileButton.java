package Interface;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;

import com.sun.prism.paint.Color;

public class TileButton extends JButton {
	
	private BufferedImage i;

	public TileButton(String n1, String n2)
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

		Font font = new Font("Helvetica", Font.PLAIN,13);
		JLabel num1 = new JLabel("<html><font color='white'>"+n1+"</font></html>");
		JLabel num2 = new JLabel("<html><font color='white'>"+n2.toString()+"</font></html>");
		num1.setBounds(4, 4, 10, 10);
		num1.setFont(font);
		num2.setFont(font);
		num2.setBounds(14, 13, 10, 10);
		this.setLayout(null);
		num1.setVisible(true);
		num2.setVisible(true);
		this.add(num1);
		this.add(num2);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(i, 0, 0, null);
	}
	
}