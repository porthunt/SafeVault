package DAOs;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Sistema.ConectaBD;
import Sistema.Digest;
import Sistema.Log;
import Sistema.User;
import Suporte.RandomNumber;

public class LogDAO {
	
	 private Connection con;
	
	public LogDAO() throws Exception
    {
        try
        {
            this.con = ConectaBD.getConnection();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
	
	public void connect() throws Exception
    {
        try
        {
            this.con = ConectaBD.getConnection();
        }
        catch(Exception e)
        {
            throw new Exception("Erro: " +
                    ":\n" + e.getMessage());
        }
    }
	
	public void insereLog (Log log) throws Exception
    {
        PreparedStatement ps = null;
        Connection con = null;
        ResultSet rs = null;
        RandomNumber rn = new RandomNumber();
        String randomNumber = rn.randomize();
        Digest digest = new Digest();
        
        try
        {
        	String insert = "INSERT INTO log (id, data, user, nome_arq) VALUES" +
        			"(?,?,?,?)";
        	String busca = "SELECT Mensagem FROM Mensagens WHERE MID=?";
        	
        	java.util.Date date = new java.util.Date();
        	java.sql.Date datesql = new java.sql.Date(date.getTime());
        	
        	connect();
            con = this.con;
            ps = con.prepareStatement(insert);
            ps.setInt(1, log.getId());
            ps.setDate(2, datesql);
            ps.setString(3, log.getUser());
            ps.setString(4, log.getNome_arq());
            ps.executeUpdate();
            
            ps = con.prepareStatement(busca);
            ps.setInt(1, log.getId());
            rs = ps.executeQuery();
            
            if (!rs.next()){
                return;
            }
            
            String msg = rs.getString("Mensagem");
            
            if(msg.contains("<login_name>") && log.getUser()!=null) {
				msg = msg.replace("<login_name>", log.getUser());
			}
            if(msg.contains("<arq_name>") && log.getNome_arq()!=null) {
				msg = msg.replace("<arq_name>", log.getNome_arq());
			}
            
            System.out.println(date+"\t"+log.getId()+"\t"+msg);
        }
        catch(SQLException sqle)
        {
            throw new Exception("Erro ao inserir." + sqle);
        }
       
        ConectaBD.closeConnection(con, ps);
    }
	
	public List<Log> buscaLog() throws Exception
    {
        PreparedStatement ps = null;
        Connection con = null;
        ResultSet rs = null;
        Log log;
        List<Log> listaLogs = new ArrayList<Log>();
        
        try
        {
        	String busca = "SELECT * FROM log l INNER JOIN Mensagens m ON l.id = m.MID";
        	
            connect();
            con = this.con;
            ps = con.prepareStatement(busca);
            rs = ps.executeQuery();
            
            if (!rs.next()){
                return null;
            }
            
            while (rs.next()) {
            	log = new Log();
            	log.setId(Integer.parseInt(rs.getString("id")));
                log.setUser(rs.getString("user"));
                log.setNome_arq(rs.getString("nome_arq"));
                log.setData(rs.getDate("data"));
                log.setMsg(rs.getString("Mensagem"));
                listaLogs.add(log);
            }
            ConectaBD.closeConnection(con, ps);
            return listaLogs;
            
        }
        catch(SQLException sqle)
        {
            throw new Exception("Erro ao buscar log." + sqle);
        }
       
        
    }

	public List<Log> buscaUserLog(String login) throws Exception
    {
        PreparedStatement ps = null;
        Connection con = null;
        ResultSet rs = null;
        Log log;
        List<Log> listaLogs = new ArrayList<Log>();
        
        try
        {
        	String busca = "SELECT * FROM log WHERE user=? ORDER BY data asc";
        	
            connect();
            con = this.con;
            ps = con.prepareStatement(busca);
            ps.setString(1, login);
            rs = ps.executeQuery();
            
            while (rs.next()) {
            	log = new Log();
            	log.setId(Integer.parseInt(rs.getString("id")));
                log.setUser(rs.getString("user"));
                log.setNome_arq(rs.getString("nome_arq"));
                log.setData(rs.getDate("data"));
                log.setMsg("");
                listaLogs.add(log);
            }
            ConectaBD.closeConnection(con, ps);
            return listaLogs;
            
        }
        catch(SQLException sqle)
        {
            throw new Exception("Erro ao buscar log." + sqle);
        }
       
        
    }

}
