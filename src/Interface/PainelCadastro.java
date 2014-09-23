package Interface;

import java.awt.Color;
import java.awt.Font;
import java.text.ParseException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

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
	
	public PainelCadastro() throws ParseException {
		this.setLayout(null);
		
		JLabel titulo = new JLabel("Cadastrar Usuário:");
		titulo.setFont(new Font("Constantia", Font.BOLD, 19));
		titulo.setBounds(10, 10, 194, 40);
		titulo.setVisible(true);
		this.add(titulo);
		String[] tipo_sexo = { "Selecione","Masculino", "Feminino"}; 
		
		//Labels
		
		JLabel nome = new JLabel("Nome:");
		nome.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		nome.setBounds(10, 60, 50, 30);
		nome.setVisible(true);
		this.add(nome);
		
		JLabel login = new JLabel("Username:");
		login.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		login.setBounds(380, 60, 85, 30);
		login.setVisible(true);
		this.add(login);
		
		JLabel grupo = new JLabel("Grupo:");
		grupo.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		grupo.setBounds(10, 159, 57, 30);
		grupo.setVisible(true);
		this.add(grupo);
		
		JLabel senha = new JLabel("Senha:");
		senha.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		senha.setBounds(10, 109, 50, 30);
		senha.setVisible(true);
		this.add(senha);
		
		JLabel confirmasenha = new JLabel("Confirmação de Senha:");
		confirmasenha.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		confirmasenha.setBounds(288, 109, 177, 30);
		confirmasenha.setVisible(true);
		this.add(confirmasenha);
		
		JLabel caminhochave = new JLabel("Caminho da Chave Pública:");
		caminhochave.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		caminhochave.setBounds(216, 159, 204, 30);
		caminhochave.setVisible(true);
		this.add(caminhochave);
		
		//TextFields
		
		textnome = new JTextField();
		textnome.setBounds(72, 61, 300, 30);
		textnome.setVisible(true);
		this.add(textnome);
		
		textlogin = new JTextField();
		textlogin.setBounds(461, 61, 201, 30);
		textlogin.setVisible(true);
		this.add(textlogin);
		
		textcaminhochave = new JTextField();
		textcaminhochave.setBounds(419, 160, 246, 30);
		textcaminhochave.setVisible(true);
		this.add(textcaminhochave);
		
			
		//defaultBorder = textnome.getBorder();
		
		JButton voltar = new JButton("Voltar");
		voltar.setName("Voltar");
		voltar.setFocusable(false);
		voltar.setBounds(126, 241, 150, 30);
		voltar.setVisible(true);
		this.add(voltar);
		voltar.addMouseListener(tt);
		
		// Confirmar
		
		JButton confirmar = new JButton("Confirmar");
		confirmar.setName("Confirmar");
		confirmar.setBounds(303, 241, 150, 30);
		confirmar.setVisible(true);
		this.add(confirmar);
		confirmar.addMouseListener(tt);
		
		this.setBackground(Color.WHITE);
		this.setVisible(true);
		this.setBounds(0, 0, LARG_DEFAULT, ALT_DEFAULT);
		
		String[] grupos = { "Administrador", "Usuário" };
		textgrupo = new JComboBox(grupos);
		textgrupo.setBounds(82, 163, 122, 27);
		add(textgrupo);
		
		textsenha = new JPasswordField();
		textsenha.setBounds(72, 111, 204, 28);
		add(textsenha);
		
		textconfirmasenha = new JPasswordField();
		textconfirmasenha.setBounds(461, 111, 201, 28);
		add(textconfirmasenha);
		
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
	
}
