package Sistema;

import java.util.Date;
import java.util.List;

import DAOs.LogDAO;

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
			throw new Exception("ID já existente. " + e);
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
