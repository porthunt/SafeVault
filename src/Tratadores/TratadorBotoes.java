package Tratadores;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.TableColumn;

import Interface.FramePrincipal;
import Interface.PainelCadastro;
import Interface.PainelChave;
import Interface.PainelConsulta;
import Interface.PainelPrincipal;
import Interface.PainelSenha;
import Interface.SairFrame;
import Sistema.Arquivos;
import Sistema.Log;
import Sistema.User;

public class TratadorBotoes implements MouseListener {

	public void mouseClicked(MouseEvent arg0) {

		FramePrincipal fp = FramePrincipal.getInstance();
		User user = new User();
		JFrame frame = new JFrame();
		JTable table=null;

		if (arg0.getComponent().getName().equals("Cadastrar"))
		{
			Log log = new Log();
			try {
				log.cadastraLog(5002, fp.user.getLogin(), null);
				log.cadastraLog(6001, fp.user.getLogin(), null);
				fp.cadastraPanel();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (arg0.getComponent().getName().equals("ConfirmaSenha"))
		{
			PainelSenha ps = (PainelSenha) arg0.getComponent().getParent();
			String login = fp.user.getLogin();
			Log log = new Log();

			if (fp.user.testaIntegridadeSenha(fp.user.getSenha())) {
				try {
					log.cadastraLog(3003, fp.user.getLogin(), null);
					log.cadastraLog(3002, fp.user.getLogin(), null);
					fp.user.tentativas=0;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				fp.chavePanel();
			} else {
				try {
					log.cadastraLog(3004, fp.user.getLogin(), null);
					if(fp.user.tentativas<2) {
						fp.remove(fp.getPainelSenha());
						ps.i=ImageIO.read(new File("imagens/telas/Senha.png"));
						fp.user.setSenha("");
						ps.limpaSenha();
						ps.limpaConectar();
						fp.user.tentativas++;
						fp.senhaPanel();
						ps.createTiles(ps.getPasswordBuilder());
						if(fp.user.tentativas==0)
							log.cadastraLog(3005, fp.user.getLogin(), null);
						else if(fp.user.tentativas==1)
							log.cadastraLog(3006, fp.user.getLogin(), null);
					} else {
						log.cadastraLog(3007, fp.user.getLogin(), null);
						log.cadastraLog(3008, fp.user.getLogin(), null);
						System.exit(1);
					}
					ps.repaint();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

		if (arg0.getComponent().getName().equals("ConectarChave"))
		{
			PainelChave pch = (PainelChave) arg0.getComponent().getParent();
			Log log = new Log();

			try {
				if (fp.user.testaChave(pch.getChavePrivada(), pch.getSeed())) {
					log.cadastraLog(4003, fp.user.getLogin(), null);
					log.cadastraLog(4002, fp.user.getLogin(), null);
					fp.user = fp.user.buscarUser(fp.user.getLogin());
					fp.user.acessos = log.buscaAcessos(fp.user.getLogin());
					fp.user.acessos++;
					fp.logadoPainel(fp.user.getGrupo());
				}
			} catch (Exception e) {
				if(fp.user.tentativas<2) {
					fp.user.tentativas++;
					if(fp.user.tentativas==1) {
						try {
							log.cadastraLog(4004, fp.user.getLogin(), null);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						pch.getAviso().setText("Chave inválida. Você possui mais 2 tentativas.");
					}
					else if(fp.user.tentativas==2) {
						try {
							log.cadastraLog(4005, fp.user.getLogin(), null);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						pch.getAviso().setText("Chave inválida. Você possui mais 1 tentativa.");
					}
				}
				else {
					try {
						log.cadastraLog(4006, fp.user.getLogin(), null);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						log.cadastraLog(4007, fp.user.getLogin(), null);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					System.exit(1);
				}
				pch.repaint();
			}
		}

		else if (arg0.getComponent().getName().equals("Conectar"))
		{
			PainelPrincipal pp = (PainelPrincipal) arg0.getComponent().getParent();
			String login = pp.getLogin().getText();

			try {
				Log log = new Log();

				if(user.confereUser(login)) {
					if(!log.checaBloqueio(login, pp.getAviso())) {
						fp.user.setLogin(login);
						log.cadastraLog(2001, null, null);
						log.cadastraLog(2003, login, null);
						log.cadastraLog(2002, null, null);
						log.cadastraLog(3001, login, null);
						fp.senhaPanel();
					}
				} else {
					log.cadastraLog(2005, login, null);
					pp.repaint();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		else if (arg0.getComponent().getName().equals("Fechar"))
		{
			if(fp.isVisible()) {
				fp.setVisible(false);
				Log log = new Log();
				try {
					log.cadastraLog(9001, fp.user.getLogin(), null);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				SairFrame sf = new SairFrame();
			} else {
				fp.user.userLogoff();

			}
		}

		else if (arg0.getComponent().getName().equals("Decriptar"))
		{
			PainelConsulta pc = (PainelConsulta) arg0.getComponent().getParent();
			int row = pc.getTable().getSelectedRow();
			Log log = new Log();

			if(!pc.getPath().getText().isEmpty()) {
				String nomeArq = (String)pc.getTable().getValueAt(row, 0);
				String nomeEncrypt = (String)pc.getTable().getValueAt(row, 1);
				Arquivos arq = new Arquivos();

				if (nomeArq!=null && nomeEncrypt!=null) {
					if(pc.getPath().getText().endsWith("/"))
						arq.caminhoPasta = pc.getPath().getText();
					else
						arq.caminhoPasta = pc.getPath().getText()+"/";
					
					String extensao = nomeArq.substring(nomeArq.indexOf("."), nomeArq.length());
					nomeArq = nomeArq.substring(0, nomeArq.indexOf("."));
					try {
						log.cadastraLog(8003, fp.user.getLogin(), nomeArq+extensao);
						arq.decriptaArquivo(nomeEncrypt.trim(), extensao, nomeArq, 0);
						log.cadastraLog(8004, fp.user.getLogin(), nomeArq+extensao);
						log.cadastraLog(8005, fp.user.getLogin(), nomeArq+extensao);

					} catch (Exception e) {
						try {
							log.cadastraLog(8006, fp.user.getLogin(), nomeArq+extensao);
							log.cadastraLog(8007, fp.user.getLogin(), nomeArq+extensao);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}

				}
			} else {
				JOptionPane.showMessageDialog(frame, "Caminho de diretório não existe!", "", JOptionPane.OK_OPTION);
			}
		}

		else if (arg0.getComponent().getName().equals("FecharMenu"))
		{
			Log log = new Log();
			if(fp.isVisible()) {
				fp.setVisible(false);
				try {
					log.cadastraLog(9001, fp.user.getLogin(), null);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				SairFrame sf = new SairFrame();
			} else {
				Arquivos arq = new Arquivos();
				try {
					log.cadastraLog(5004, fp.user.getLogin(), null);
					arq.removeArqDecriptados();
					log.cadastraLog(1002, null, null);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				fp.writer.close();
				System.exit(1);
			}
		}

		else if (arg0.getComponent().getName().equals("Consultar"))
		{
			Log log = new Log();
			try {
				log.cadastraLog(5003, fp.user.getLogin(), null);
//				Arquivos arq = new Arquivos();
//				byte[] newFile = arq.decriptaArquivo("index", ".txt", "index", 0);
//				if(newFile==null || !(arq.checaIntegridade("index", newFile))) {
//					JOptionPane.showMessageDialog(frame, "Arquivo index não integro! Programa sendo interrompido por falta de segurança!", "", JOptionPane.OK_OPTION);
//					arq.removeArqDecriptados();
//					System.exit(1);
//				}
				fp.consultaPanel();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		else if (arg0.getComponent().getName().equals("ConfirmarCadastro"))
		{	
			PainelCadastro pc = (PainelCadastro) arg0.getComponent().getParent();
			Log log = new Log();
			try {
				log.cadastraLog(6002, fp.user.getLogin(), null);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			if (!pc.getTextnome().getText().equals("")
					&& !pc.getTextlogin().getText().equals("")
					&& !pc.getTextsenha().getText().equals("")
					&& !pc.getTextconfirmasenha().getText().equals("")
					&& !pc.getTextgrupo().getText().equals("")
					&& !pc.getTextcaminhochave().getText().equals(""))
			{
				if (user.testaSenha(pc.getTextsenha().getText(), pc.getTextconfirmasenha().getText())) {
					String nome = pc.getTextnome().getText();
					String login = pc.getTextlogin().getText();
					String senha = pc.getTextsenha().getText();
					String confirmasenha = pc.getTextconfirmasenha().getText();
					String grupo = (String) pc.getTextgrupo().getText();
					String caminhochave = pc.getTextcaminhochave().getText();

					try {
						User usr = new User();
						usr.cadastraUser(nome, login, senha.toString(), grupo, fp.user.getBytesFromFile(new File(caminhochave)));
						JOptionPane.showMessageDialog(frame, "Cadastrado com sucesso!", "", JOptionPane.PLAIN_MESSAGE);
					} catch (Exception e) {
						JOptionPane.showMessageDialog(frame, "ID já existente!", "", JOptionPane.OK_OPTION);
						e.printStackTrace();
					}
					fp.logadoPainel(fp.user.getGrupo());
				} else {
					JOptionPane.showMessageDialog(frame, "A senha não é válida!", "", JOptionPane.OK_OPTION);
				}
			}
			else
				JOptionPane.showMessageDialog(frame, "Preencha os campos destacados!", "", JOptionPane.OK_OPTION);

		}

		else if (arg0.getComponent().getName().equals("VoltarCadastro"))
		{	
			Log log = new Log();
			try {
				log.cadastraLog(6003, fp.user.getLogin(), null);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			fp.logadoPainel(fp.user.getGrupo());
		} 

		else if (arg0.getComponent().getName().equals("VoltarConsulta"))
		{
			Log log = new Log();
			try {
				log.cadastraLog(8002, fp.user.getLogin(), null);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			fp.logadoPainel(fp.user.getGrupo());
		} 

		else if (arg0.getComponent().getName().equals("VoltarSair"))
		{	
			SairFrame sf = (SairFrame) arg0.getComponent().getParent().getParent().getParent().getParent();
			sf.dispose();
			Log log = new Log();
			try {
				log.cadastraLog(9003, fp.user.getLogin(), null);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			fp.setVisible(true);
		} 

		else if (arg0.getComponent().getName().equals("Caminho"))
		{	
			PainelConsulta pc = (PainelConsulta) arg0.getComponent().getParent();
			Arquivos arq = new Arquivos();
			
			if(pc.getPath().getText().endsWith("/"))
				arq.caminhoPasta = pc.getPath().getText();
			else
				arq.caminhoPasta = pc.getPath().getText()+"/";

			File path = new File(arq.caminhoPasta+"index.enc");
			
			if(path.exists()) {
				pc.getPath().setEditable(false);
				fp.path = arq.caminhoPasta;
				File success = (new File(arq.caminhoPasta+"decriptados"));
				success.mkdir();
				try {
					arq.decriptaArquivo("index", ".txt", "index", 0);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				/* add table */
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
				pc.add(scrollpane, BorderLayout.CENTER);
				table.setBounds(17, 135, 650, 270);
				table.setVisible(true);
				pc.setTable(table);
				pc.add(table);
				pc.repaint();
				/* fim */
			} else {
				JOptionPane.showMessageDialog(frame, "Este diretorio não é válido!", "", JOptionPane.OK_OPTION);
			}
		} 

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {


	}
}
