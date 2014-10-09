package Sistema;

import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.swing.JLabel;

import DAOs.LogDAO;
import Interface.FramePrincipal;

public class Log {

	private Integer id;
	private String user=null;
	private String nome_arq=null;
	private Date data;
	private String msg;

	public Log(Integer id, String user) {
		this.id = id;
		this.user = user;
	}

	public Log(Integer id) {
		this.id = id;
	}

	public Log() {
	}

	public void cadastraLog(Integer id, String user, String nome_arq) throws Exception {

		try {
			Log log = new Log();
			log.setId(id);
			log.setUser(user);
			log.setNome_arq(nome_arq);
			LogDAO lDAO = new LogDAO();
			lDAO.insereLog(log);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception("Erro ao cadastrar log. " + e);
		}

	}
	
	public Integer buscaAcessos(String user) throws Exception {

		try {
			LogDAO lDAO = new LogDAO();
			return lDAO.buscaAcessos(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception("Erro ao conferir acessos." + e);
		}

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getNome_arq() {
		return nome_arq;
	}

	public void setNome_arq(String nome_arq) {
		this.nome_arq = nome_arq;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public boolean checaBloqueio(String login, JLabel jl) {
		try {
			LogDAO lDAO = new LogDAO();
			List<Log> lstLog = lDAO.buscaUserLog(login);
			FramePrincipal fp = FramePrincipal.getInstance();
			
			if(lstLog!=null || !lstLog.isEmpty()) {
				for (int i=0; i<lstLog.size(); i++) {
					java.util.Date datasql = new java.util.Date();
					datasql = lstLog.get(i).data;
					java.util.Date data = new java.util.Date();
					if (lstLog.get(i).id==2004 || lstLog.get(i).id==3008 || lstLog.get(i).id==4007) {
						if (data.getTime() - datasql.getTime() < 2*60*1000) {
							jl.setText("Usuário bloqueado. Tente novamente em: "+(120-(data.getTime() - datasql.getTime())/1000)+" segundos.");
							return true;
						}
					}
					else if(lstLog.get(i).id==3005) {
						jl.setText("Senha inválida. Você possui mais 2 tentativas.");
					} else if(lstLog.get(i).id==3006) {
						jl.setText("Senha inválida. Você possui mais 1 tentativa.");
					} else if (lstLog.get(i).id==3007) {
						jl.setText("Usuário bloqueado. Tente novamente em: "+(120-(data.getTime() - datasql.getTime())/1000)+" segundos.");
					}
				}
			}

			return false;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public void geraLog() throws Exception {

		try {
			LogDAO lDAO = new LogDAO();
			List<Log> listaLog = lDAO.buscaLog();
			if(listaLog==null)
				return;
			for(int i=0; i<listaLog.size(); i++) {
				if(listaLog.get(i).msg.contains("<login_name>") && listaLog.get(i).user!=null) {
					listaLog.get(i).msg = listaLog.get(i).msg.replace("<login_name>", listaLog.get(i).user);
				}
				if (listaLog.get(i).msg.contains("<arq_name>") && listaLog.get(i).nome_arq!=null) {
					listaLog.get(i).msg = listaLog.get(i).msg.replace("<arq_name>", listaLog.get(i).nome_arq);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//throw new Exception("Erro ao exibir log. " + e);
			e.printStackTrace();
		}
	}

}
