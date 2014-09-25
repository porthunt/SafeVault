package Sistema;

import java.util.Date;

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
