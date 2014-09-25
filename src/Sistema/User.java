package Sistema;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import DAOs.UserDAO;
import Interface.FramePrincipal;

public class User {

	String nome, login, senha, grupo;
	
	public User() {
		nome = "";
		login = "";
		senha = "";
		grupo = "";
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public void cadastraUser(String nome, String login, String senha, String grupo) throws Exception {
		this.nome = nome;
		this.login = login;
		this.senha = senha;
		this.grupo = grupo;

		try {
			UserDAO cDAO = new UserDAO();
			cDAO.insereUser(this);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception("ID já existente. " + e);
		}

	}
	
	public boolean buscarUser(String login) throws Exception {
		try {
			UserDAO cDAO = new UserDAO();
			Boolean bool = cDAO.buscaUser(login);
			return bool;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception("Erro ao buscar. " + e);
		}

	}
	
	public String buscarSaltUser(String login) throws Exception {
		try {
			UserDAO cDAO = new UserDAO();
			return cDAO.buscaSaltUser(login);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception("Erro ao conferir. " + e);
		}

	}
	
	public String buscaHashSenha(String login) throws Exception {
		try {
			UserDAO cDAO = new UserDAO();
			return cDAO.buscaHashSenha(login);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception("Erro ao conferir. " + e);
		}

	}
	
	public boolean testaSenha(String senha, String confirmasenha) {
		if (!senha.equals(confirmasenha))
			return false;
		else {
			for (int i=0; i<senha.length()-2; i++) {
				if (senha.substring(i,i+1).equals(senha.substring(i+1, i+2)))
					return false;
			}

			if (senha.substring(senha.length()-2, senha.length()-1).equals(senha.substring(senha.length()-1, senha.length())))
				return false;
			
			else if (!User.isNumeric(senha))
				return false;
			
			else {
				if (senha.length()!=6)
					return false;
				else
					return true;
			}
		}
	}
	
	public boolean testaIntegridadeSenha(String senha) {

		String salt=null;	
		try {
			salt = this.buscarSaltUser(this.getLogin());
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
		
		String[] senhas = new String[64];

		for(int i=0; i<senhas.length; i++) {
			senhas[i] = "";
		}

		int particiona = 32;
		int digit = 0;

		for(int j=0; j<chs.size(); j++) {
			for(int i=0; i<senhas.length; i++) {
				if(i!=0 && i%particiona == 0)
					digit = (digit==0 ? 1 : 0);
				senhas[i] += chs.get(j)[digit];
			}
			particiona /= 2;
			digit = 0;
		}
		
		for(int i=0; i<senhas.length; i++) {
			if(salt!=null) {
				senhas[i]=senhas[i]+salt;
				Digest digest = new Digest();
				try {
					if(digest.getDigest("MD5", senhas[i]).equals(this.buscaHashSenha(this.getLogin()))) {
						return true;
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else {
				System.err.println("Ops! Um erro aconteceu!");
				System.exit(1);
			}
		}
	
		return false;
	}
	
	public static boolean isNumeric(String str)  
	{  
	  try  
	  {  
	    double d = Double.parseDouble(str);  
	  }  
	  catch(NumberFormatException nfe)  
	  {  
	    return false;  
	  }  
	  return true;  
	}

}
