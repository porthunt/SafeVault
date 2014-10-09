package Interface;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Tratadores.TratadorBotoes;

public class SairFrame extends JFrame {

	private JButton sair;
	private JButton voltar;
	
	public SairFrame() {
		this.setTitle("Sair");
		this.setBounds(0, 0, 600, 200);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setResizable(false);
		this.setLayout(null);
		TratadorBotoes tb = new TratadorBotoes();
		
		String text="Voce deseja realmente sair?";
		JLabel label = new JLabel(text);
		label.setVisible(true);
		label.setBounds(20, 20, 600, 200);
		this.add(label);
		
		sair = new JButton("Sair");
		sair.setVisible(true);
		sair.setBounds(0, 0, 50, 20);
		sair.setName("Fechar");
		sair.addMouseListener(tb);
		this.add(sair);
		
		voltar = new JButton("Voltar");
		voltar.setVisible(true);
		voltar.setBounds(60, 20, 50, 20);
		voltar.setName("VoltarSair");
		voltar.addMouseListener(tb);
		this.add(voltar);
		
		this.setVisible(true);
	}

	
	
}
