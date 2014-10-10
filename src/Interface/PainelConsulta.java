package Interface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.TableColumn;

import Sistema.Arquivos;
import Tratadores.TratadorBotoes;

public class PainelConsulta extends JPanel {

	public final int LARG_DEFAULT = 690;
	public final int ALT_DEFAULT = 510;
	public final int COORD_Y=110;
	public BufferedImage i;
	TratadorBotoes tb;
	FramePrincipal fp = FramePrincipal.getInstance();
	String pathChavePrivada, seed;
	ActionButton go;
	JLabel pathArquivos;
	JTable table;
	JTextField path;
	JLabel acessos;
	ActionButton confirmar;

	public PainelConsulta() throws Exception {

		tb = new TratadorBotoes();

		try
		{
			i=ImageIO.read(new File("imagens/telas/consulta.png"));
		}
		catch(IOException e)
		{
			System.out.println(e.getMessage());
			System.exit(1);
		}

		Arquivos arq = new Arquivos();
		Font font = new Font("Helvetica", Font.BOLD,14);

		acessos = new JLabel(fp.user.getAcessos().toString());
		acessos.setFont(font);
		acessos.setForeground(Color.WHITE);
		acessos.setBounds(650,67,50, 20);
		acessos.setVisible(true);
		this.add(acessos);


		//		pathArquivos = new JLabel(arq.getCaminhoPasta());
		//		pathArquivos.setFont(font);
		//		pathArquivos.setForeground(Color.WHITE);
		//		pathArquivos.setBounds(310, 100, 150, 32);
		//		pathArquivos.setVisible(true);
		//		this.add(pathArquivos);

		path = new JTextField();
		path.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		path.setFont(font);
		path.setBounds(310, 98, 220, 32);
		path.setVisible(true);
		this.add(path);

		JButton confirmaPath = new JButton("Confirmar");
		confirmaPath.setBounds(550, 98, 100, 32);
		confirmaPath.setName("Caminho");
		confirmaPath.addMouseListener(tb);
		confirmaPath.setVisible(true);
		this.add(confirmaPath);

		ActionButton voltar = new ActionButton("imagens/botoes/voltar.png");
		voltar.setName("VoltarConsulta");
		voltar.setBounds(153, 419, 182, 56);
		voltar.setVisible(true);
		this.add(voltar);
		voltar.addMouseListener(tb);

		// Confirmar

		confirmar = new ActionButton("imagens/botoes/decriptar.png");
		confirmar.setName("Decriptar");
		confirmar.setEnabled(false);
		confirmar.setBounds(376, 420, 182, 56);
		confirmar.setVisible(true);
		this.add(confirmar);
		confirmar.addMouseListener(tb);

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

	public JTable getTable() {
		return table;
	}

	public JTextField getPath() {
		return path;
	}

	public void setPath(JTextField path) {
		this.path = path;
	}

	public ActionButton getConfirmar() {
		return confirmar;
	}

	public void setConfirmar(ActionButton confirmar) {
		this.confirmar = confirmar;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

}
