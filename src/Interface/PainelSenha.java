package Interface;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Suporte.RandomNumber;
import Tratadores.TratadorBotoes;
import Tratadores.TratadorTiles;

public class PainelSenha extends JPanel {

	public final int LARG_DEFAULT = 690;
	public final int ALT_DEFAULT = 510;
	public final int COORD_Y=110;
	public BufferedImage i;
	TratadorBotoes tb;
	TratadorTiles tl;
	JTextField login;
	JLabel aviso;
	RandomNumber rn = new RandomNumber();
	FramePrincipal fp = FramePrincipal.getInstance();
	TileButton tile1, tile2, tile3, tile4, tile5;
	String passwordBuilder;
	JPasswordField jf;
	ActionButton go;

	public PainelSenha(String caminho) {

		tb = new TratadorBotoes();
		tl = new TratadorTiles();

		try
		{
			i=ImageIO.read(new File(caminho));
		}
		catch(IOException e)
		{
			System.out.println(e.getMessage());
			System.exit(1);
		}

		Font font = new Font("Helvetica", Font.BOLD,18);

		passwordBuilder = rn.completelyRandomize();
		this.createTiles(passwordBuilder);

		jf = new JPasswordField();
		jf.setFont(font);
		jf.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		jf.setHorizontalAlignment(SwingConstants.CENTER);
		jf.setBounds(260, 310, 155, 24);
		jf.setEnabled(false);
		jf.setVisible(true);
		this.add(jf);

		CloseButton close = new CloseButton();
		close.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		close.setBounds(314, 447, 45, 45);
		close.setVisible(true);
		close.setName("Fechar");
		close.addMouseListener(tb);
		this.add(close);

		tb = new TratadorBotoes();
		go = new ActionButton("go.png");
		go.setName("ConfirmaSenha");
		go.addMouseListener(tb);
		go.setBounds(426, 305, 35, 36);
		go.setVisible(false);
		this.add(go);

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

	public void createTiles(String passwordBuilder) {
		tile1 = new TileButton(passwordBuilder.substring(0, 1), passwordBuilder.substring(1, 2));
		tile1.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		tile1.setBounds(264, 350, 25, 25);
		tile1.setVisible(true);
		tile1.setName("Tile1");
		tile1.addMouseListener(tl);
		this.add(tile1);

		tile2 = new TileButton(passwordBuilder.substring(2, 3), passwordBuilder.substring(3, 4));
		tile2.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		tile2.setBounds(tile1.getX()+31, 350, 25, 25);
		tile2.setVisible(true);
		tile2.setName("Tile2");
		tile2.addMouseListener(tl);
		this.add(tile2);

		tile3 = new TileButton(passwordBuilder.substring(4, 5), passwordBuilder.substring(5, 6));
		tile3.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		tile3.setBounds(tile2.getX()+31, 350, 25, 25);
		tile3.setVisible(true);
		tile3.setName("Tile3");
		tile3.addMouseListener(tl);
		this.add(tile3);

		tile4 = new TileButton(passwordBuilder.substring(6, 7), passwordBuilder.substring(7, 8));
		tile4.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		tile4.setBounds(tile3.getX()+31, 350, 25, 25);
		tile4.setVisible(true);
		tile4.setName("Tile4");
		tile4.addMouseListener(tl);
		this.add(tile4);

		tile5 = new TileButton(passwordBuilder.substring(8, 9), passwordBuilder.substring(9, 10));
		tile5.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		tile5.setBounds(tile4.getX()+31, 350, 25, 25);
		tile5.setVisible(true);
		tile5.setName("Tile5");
		tile5.addMouseListener(tl);
		this.add(tile5);
	}

	public void removeTiles() {
		this.remove(tile1);
		this.remove(tile2);
		this.remove(tile3);
		this.remove(tile4);
		this.remove(tile5);
	}

	public String getPasswordBuilder() {
		return passwordBuilder;
	}

	public void setPasswordBuilder(String passwordBuilder) {
		this.passwordBuilder = passwordBuilder;
	}

	public void resetPasswordBuilder() {
		this.passwordBuilder = rn.completelyRandomize();
	}

	public void setI(BufferedImage i) {
		this.i = i;
	}

	public void renameJF(String novo) {
		this.jf.setText(jf.getText()+novo);
	}

	public ActionButton getGo() {
		return go;
	}

	public boolean testaIntegridadeSenha(String senha) {

		try {
			String salt = fp.user.buscarSaltUser(fp.user.getLogin());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<String[]> chs = new ArrayList<String[]>();

		String[] pt1 = new String[2];
		String[] pt2 = new String[2];
		String[] pt3 = new String[2];
		String[] pt4 = new String[2];
		String[] pt5 = new String[2];
		String[] pt6 = new String[2];
		
		pt1[0] = senha.substring(0, 1);
		pt1[1] = senha.substring(1, 2);
		pt2[0] = senha.substring(2, 3);
		pt2[1] = senha.substring(3, 4);
		pt3[0] = senha.substring(4, 5);
		pt3[1] = senha.substring(5, 6);
		pt4[0] = senha.substring(6, 7);
		pt4[1] = senha.substring(7, 8);
		pt5[0] = senha.substring(8, 9);
		pt5[1] = senha.substring(9, 10);
		pt6[0] = senha.substring(10, 11);
		pt6[1] = senha.substring(11, 12);
		
		chs.add(pt1);
		chs.add(pt2);
		chs.add(pt3);
		chs.add(pt4);
		chs.add(pt5);
		chs.add(pt6);
		
		String[] senhas = gerarSenhas(chs);
	
		return false;
	}

	private String[] gerarSenhas(List<String[]> digitos) {

		String[] senhas = new String[64];

		for(int i=0; i<senhas.length; i++) {
			senhas[i] = "";
		}

		int particiona = 32;
		int digit = 0;

		for(int j=0; j<digitos.size(); j++) {
			for(int i=0; i<senhas.length; i++) {
				if(i!=0 && i%particiona == 0)
					digit = (digit==0 ? 1 : 0);
				senhas[i] += digitos.get(j)[digit];
			}
			particiona /= 2;
			digit = 0;
		}
		return senhas;
	}
}
