package Interface;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Tratadores.TratadorBotoes;

public class PainelPrincipal extends JPanel {

	public final int LARG_DEFAULT = 690;
	public final int ALT_DEFAULT = 510;
	public final int COORD_Y=110;
	private BufferedImage i;
	TratadorBotoes tb;

	public PainelPrincipal() {
		
		tb = new TratadorBotoes();

		try
		{
			i=ImageIO.read(new File("tela.png"));
		}
		catch(IOException e)
		{
			System.out.println(e.getMessage());
			System.exit(1);
		}
		
		Font font = new Font("Helvetica", Font.BOLD,18);
		
		JTextField login = new JTextField();
		login.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		login.setFont(font);
		login.setHorizontalAlignment(SwingConstants.CENTER);
		login.setBounds(316, 255, 145, 22);
		login.setVisible(true);
		this.add(login);
		
		LockButton locker = new LockButton();
		locker.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		locker.setBounds(316, 410, 60, 60);
		locker.setVisible(true);
		locker.setName("Cadastrar");
		locker.addMouseListener(tb);
		this.add(locker);


		this.setLayout(null);
		Dimension size = new Dimension(i.getWidth(null), i.getHeight(null));
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		setSize(size);

		//		textField_1 = new JTextField();
		//		textField_1.setBounds(315, 292, 134, 28);
		//		add(textField_1);
		//		textField_1.setColumns(10);
	}

	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    // paint the background image and scale it to fill the entire space
	    g.drawImage(i, 0, 0, getWidth(), getHeight(), this);
	  }
}
