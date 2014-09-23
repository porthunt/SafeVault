package Sistema;

import java.util.Date;

import DAOs.LogDAO;

public class Log {

	private Integer id;
	private String user=null;
	private Date data;

	public Log(Integer id, String user) {
		this.id = id;
		this.user = user;
	}
	
	public Log(Integer id) {
		this.id = id;
	}
	
	public Log() {
	}

	public void cadastraLog(Integer id, String user) throws Exception {
		this.id = id;
		this.user = user;
		
		try {
			LogDAO lDAO = new LogDAO();
			lDAO.insereLog(this);
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

}
