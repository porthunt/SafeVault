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
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;

import Tratadores.TratadorBotoes;

public class PainelCadastro extends JPanel {

	public final int LARG_DEFAULT = 690;
	public final int ALT_DEFAULT = 510;
	TratadorBotoes tt = new TratadorBotoes();

	private JTextField textnome, textlogin;
	private JTextField textcaminhochave;
	private JPasswordField textsenha;
	private JPasswordField textconfirmasenha;
	private JTextField textgrupo;
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
		DocumentFilter df = new DocumentFilter() {

			@Override
			public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
				String string = fb.getDocument().getText(0, fb.getDocument().getLength()) + text;

				if (string.length() <= 6) {
					super.replace(fb, offset, length, text, attrs);
				}
			}

		};

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

		textsenha = new JPasswordField(6);
		PlainDocument document = (PlainDocument) textsenha.getDocument();
		document.setDocumentFilter(df);
		textsenha.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		textsenha.setHorizontalAlignment(SwingConstants.CENTER);
		textsenha.setFont(font);
		textsenha.setBounds(142, 300, 163, 30);
		textsenha.setVisible(true);
		this.add(textsenha);
		
		textconfirmasenha = new JPasswordField(6);
		document = (PlainDocument) textconfirmasenha.getDocument();
		document.setDocumentFilter(df);
		textconfirmasenha.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		textconfirmasenha.setHorizontalAlignment(SwingConstants.CENTER);
		textconfirmasenha.setFont(font);
		
		textconfirmasenha.setBounds(482, 303, 150, 26);
		textconfirmasenha.setVisible(true);
		this.add(textconfirmasenha);


		textcaminhochave = new JTextField();
		textcaminhochave.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		textcaminhochave.setHorizontalAlignment(SwingConstants.CENTER);
		textcaminhochave.setFont(new Font("Helvetica", Font.BOLD,15));
		textcaminhochave.setBounds(335, 355, 295, 30);
		textcaminhochave.setVisible(true);
		this.add(textcaminhochave);


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

		textgrupo = new JTextField();
		textgrupo.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		textgrupo.setHorizontalAlignment(SwingConstants.CENTER);
		textgrupo.setFont(new Font("Helvetica", Font.BOLD,15));
		textgrupo.setBounds(508, 241, 130, 27);
		this.add(textgrupo);

		this.repaint();
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

	public JTextField getTextgrupo() {
		return textgrupo;
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// paint the background image and scale it to fill the entire space
		g.drawImage(i, 0, 0, getWidth(), getHeight(), this);
	}

}
