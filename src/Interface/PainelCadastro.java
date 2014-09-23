package Interface;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Tratadores.TratadorBotoes;

public class PainelCadastro extends JPanel {

	public final int LARG_DEFAULT = 690;
	public final int ALT_DEFAULT = 510;
	TratadorBotoes tt = new TratadorBotoes();
	
	private JTextField textnome, textlogin;
	private JTextField textcaminhochave;
	private JPasswordField textsenha;
	private JPasswordField textconfirmasenha;
	private JComboBox textgrupo;
	private BufferedImage i;
	
	public PainelCadastro() throws ParseException {
		
		try
		{
			i=ImageIO.read(new File("cadastro.png"));
		}
		catch(IOException e)
		{
			System.out.println(e.getMessage());
			System.exit(1);
		}
		
		this.setLayout(null);
		Font font = new Font("Helvetica", Font.BOLD,18);
		
		//TextFields
		
		textnome = new JTextField();
		textnome.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		textnome.setHorizontalAlignment(SwingConstants.CENTER);
		textnome.setFont(font);
		textnome.setBounds(145, 179, 490, 30);
		textnome.setVisible(true);
		this.add(textnome);
		
		textlogin = new JTextField();
		textlogin.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		textlogin.setHorizontalAlignment(SwingConstants.CENTER);
		textlogin.setFont(font);
		textlogin.setBounds(147, 239, 233, 30);
		textlogin.setVisible(true);
		this.add(textlogin);
		
		textcaminhochave = new JTextField();
		textcaminhochave.setBounds(419, 160, 246, 30);
		textcaminhochave.setVisible(true);
		//this.add(textcaminhochave);
		
			
		//defaultBorder = textnome.getBorder();
		
		JButton voltar = new JButton("Voltar");
		voltar.setName("Voltar");
		voltar.setFocusable(false);
		voltar.setBounds(126, 241, 150, 30);
		voltar.setVisible(true);
		//this.add(voltar);
		voltar.addMouseListener(tt);
		
		// Confirmar
		
		ConfirmaButton confirmar = new ConfirmaButton();
		confirmar.setName("Confirmar");
		confirmar.setBounds(243, 419, 182, 56);
		confirmar.setVisible(true);
		this.add(confirmar);
		confirmar.addMouseListener(tt);
		
		this.setBackground(Color.WHITE);
		this.setVisible(true);
		this.setBounds(0, 0, LARG_DEFAULT, ALT_DEFAULT);
		
		String[] grupos = { "Administrador", "Usuário" };
		textgrupo = new JComboBox(grupos);
		textgrupo.setBounds(82, 163, 122, 27);
		//this.add(textgrupo);
		
		textsenha = new JPasswordField();
		textsenha.setBounds(72, 111, 204, 28);
		//this.add(textsenha);
		
		textconfirmasenha = new JPasswordField();
		textconfirmasenha.setBounds(461, 111, 201, 28);
		//this.add(textconfirmasenha);
		
		this.repaint();
	}

	public void destacaTextNome() {
		textnome.setBorder(BorderFactory.createLineBorder(Color.RED));
	}
	
	public void bordaNormal() {
		//textnome.setBorder(defaultBorder);
		//texttelefone.setBorder(defaultBorder);
	}

	public JTextField getTextnome() {
		return textnome;
	}

	public JTextField getTextlogin() {
		return textlogin;
	}

	public JTextField getTextcaminhochave() {
		return textcaminhochave;
	}

	public JPasswordField getTextsenha() {
		return textsenha;
	}

	public JPasswordField getTextconfirmasenha() {
		return textconfirmasenha;
	}
	
	public JComboBox getTextgrupo() {
		return textgrupo;
	}
	
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    // paint the background image and scale it to fill the entire space
	    g.drawImage(i, 0, 0, getWidth(), getHeight(), this);
	  }
	
}
