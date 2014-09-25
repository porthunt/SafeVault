package DAOs;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Sistema.ConectaBD;
import Sistema.Digest;
import Sistema.Log;
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
        	String insert = "INSERT INTO log (id, data, user) VALUES" +
        			"(?,?,?)";
        	java.util.Date date = new java.util.Date();
        	java.sql.Date datesql = new java.sql.Date(date.getTime());
        	
        	connect();
            con = this.con;
            ps = con.prepareStatement(insert);
            ps.setInt(1, log.getId());
            ps.setDate(2, datesql);
            ps.setString(3, log.getUser());
            ps.executeUpdate();
        }
        catch(SQLException sqle)
        {
            throw new Exception("Erro ao inserir." + sqle);
        }
       
        ConectaBD.closeConnection(con, ps);
    }
	
}
