package Interface;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Tratadores.TratadorBotoes;
import java.awt.Font;
import javax.swing.SwingConstants;

public class SairFrame extends JFrame {

	private JButton sair;
	private JButton voltar;
	
	public SairFrame() {
		this.setTitle("Sair");
		this.setBounds(0, 0, 600, 200);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setResizable(false);
		getContentPane().setLayout(null);
		TratadorBotoes tb = new TratadorBotoes();
		
		String text="“Pressione o Botão Sair para apagar os arquivos decriptados e encerrar o sistema.";
		JLabel label = new JLabel(text);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		label.setVisible(true);
		label.setBounds(0, 20, 600, 62);
		getContentPane().add(label);
		
		sair = new JButton("Sair");
		sair.setVisible(true);
		sair.setBounds(302, 94, 111, 44);
		sair.setName("Fechar");
		sair.addMouseListener(tb);
		getContentPane().add(sair);
		
		voltar = new JButton("Voltar");
		voltar.setVisible(true);
		voltar.setBounds(135, 94, 111, 44);
		voltar.setName("VoltarSair");
		voltar.addMouseListener(tb);
		getContentPane().add(voltar);
		
		this.setVisible(true);
	}

	
	
}
