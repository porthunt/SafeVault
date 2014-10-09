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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
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
	JLabel acessos;

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


		pathArquivos = new JLabel(arq.getCaminhoPasta());
		pathArquivos.setFont(font);
		pathArquivos.setForeground(Color.WHITE);
		pathArquivos.setBounds(310, 105, 355, 32);
		pathArquivos.setVisible(true);
		this.add(pathArquivos);

		ActionButton voltar = new ActionButton("imagens/botoes/voltar.png");
		voltar.setName("VoltarConsulta");
		voltar.setBounds(153, 419, 182, 56);
		voltar.setVisible(true);
		this.add(voltar);
		voltar.addMouseListener(tb);

		// Confirmar

		ActionButton confirmar = new ActionButton("imagens/botoes/decriptar.png");
		confirmar.setName("Decriptar");
		confirmar.setBounds(376, 420, 182, 56);
		confirmar.setVisible(true);
		this.add(confirmar);
		confirmar.addMouseListener(tb);

		String[] columns = { "NOME", "NOME ENCRIPTADO", "INTEGRIDADE", "AUTENTICIDADE" };
		String[][] data= new String[100][100];
		List<String> fileList = arq.ReadFileAsList("index");
		for (int i=0; i<fileList.size(); i++) {
			if(fileList.get(i).length()>1) {
				data[i][0] = fileList.get(i).substring(0, fileList.get(i).indexOf(" "));
				data[i][1] = fileList.get(i).substring(fileList.get(i).indexOf(" "), fileList.get(i).length());
				try {
					String extensao = data[i][0].substring(data[i][0].indexOf("."), data[i][0].length());
					byte[] arquivo = arq.decriptaArquivo(data[i][1].trim(), extensao, data[i][1].trim(), 1);
					if(arquivo==null) {
						data[i][2] = "NOT OK";
					} else {
						data[i][2] = "OK";
					}
					if(!arq.checaIntegridade(data[i][1].trim(), arquivo)) {
						data[i][3]= "NOT OK";
					} else {
						data[i][3]= "OK";
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
			}
		}
		table = new JTable(data, columns);
		TableColumn columnA = table.getColumn("NOME");
        columnA.setMinWidth(255);
        columnA.setMaxWidth(255);
        TableColumn columnB = table.getColumn("NOME ENCRIPTADO");
        columnB.setMinWidth(255);
        columnB.setMaxWidth(255);
        TableColumn columnC = table.getColumn("INTEGRIDADE");
        columnC.setMinWidth(70);
        columnC.setMaxWidth(70);
        TableColumn columnD = table.getColumn("AUTENTICIDADE");
        columnD.setMinWidth(70);
        columnD.setMaxWidth(70);
		table.setAutoResizeMode( JTable.AUTO_RESIZE_ALL_COLUMNS );
		JScrollPane scrollpane = new JScrollPane(table);
		scrollpane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS); 
		scrollpane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS); 
		this.add(scrollpane, BorderLayout.CENTER);
		table.setBounds(17, 135, 650, 270);
		table.setVisible(true);
		this.add(table);

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
}
