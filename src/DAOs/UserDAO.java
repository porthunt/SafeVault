package DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Sistema.ConectaBD;
import Sistema.Digest;
import Sistema.User;
import Suporte.RandomNumber;

public class UserDAO {
	
	 private Connection con;
	
	public UserDAO() throws Exception
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
	
	public void insereUser (User user) throws Exception
    {
        PreparedStatement ps = null;
        Connection con = null;
        ResultSet rs = null;
        RandomNumber rn = new RandomNumber();
        String randomNumber = rn.randomize();
        Digest digest = new Digest();
        
        try
        {
        	String insert = "INSERT INTO user (nome, login, senha, grupo) VALUES" +
        			"(?,?,?,?)";
        	
        	String insert2 = "INSERT INTO randomNumbers (id, number) VALUES" +
        	"(?,?)";
        	
            connect();
            con = this.con;
            String hashSenha = user.getSenha()+randomNumber;
            hashSenha = digest.getDigest("MD5", hashSenha);
            ps = con.prepareStatement(insert);
            ps.setString(1, user.getNome());
            ps.setString(2, user.getLogin());
            ps.setString(3, hashSenha);
            ps.setString(4, user.getGrupo());
            ps.executeUpdate();
            
            ps = con.prepareStatement(insert2);
            ps.setString(1, user.getLogin());
            ps.setString(2, randomNumber);
            ps.executeUpdate();
        }
        catch(SQLException sqle)
        {
            throw new Exception("Erro ao inserir." + sqle);
        }
       
        ConectaBD.closeConnection(con, ps);
    }
	
	public boolean confereUser (String username) throws Exception
    {
        PreparedStatement ps = null;
        Connection con = null;
        ResultSet rs = null;
        
        try
        {
        	String busca = "SELECT login FROM user WHERE login=?";
        	
            connect();
            con = this.con;
            ps = con.prepareStatement(busca);
            ps.setString(1, username);
            rs = ps.executeQuery();
            
            if (!rs.next()){
                return false;
            }
            
            ConectaBD.closeConnection(con, ps);
            return true;
            
        }
        catch(SQLException sqle)
        {
            throw new Exception("Erro ao buscar usuário." + sqle);
        }
       
        
    }
	
	public User buscaUser (String username) throws Exception
    {
        PreparedStatement ps = null;
        Connection con = null;
        ResultSet rs = null;
        User usr = new User();
        
        try
        {
        	String busca = "SELECT * FROM user WHERE login=?";
        	
            connect();
            con = this.con;
            ps = con.prepareStatement(busca);
            ps.setString(1, username);
            rs = ps.executeQuery();
            
            if (!rs.next()){
                return null;
            }
            
            usr.setLogin(rs.getString("login"));
            usr.setNome(rs.getString("nome"));
            usr.setGrupo(rs.getString("grupo"));
            
            
            ConectaBD.closeConnection(con, ps);
            return usr;
            
        }
        catch(SQLException sqle)
        {
            throw new Exception("Erro ao buscar usuário." + sqle);
        }
       
        
    }
	
	public String buscaSaltUser (String username) throws Exception
    {
        PreparedStatement ps = null;
        Connection con = null;
        ResultSet rs = null;
        
        try
        {
        	String busca = "SELECT number FROM randomNumbers WHERE id=?";
        	
            connect();
            con = this.con;
            ps = con.prepareStatement(busca);
            ps.setString(1, username);
            rs = ps.executeQuery();
            
            if (!rs.next()){
                return null;
            }
            
            String num = rs.getString("number");
            ConectaBD.closeConnection(con, ps);
            return num;
     
        }
        catch(SQLException sqle)
        {
            throw new Exception("Erro ao conferir senha." + sqle);
        }
       
        
    }
	
	public String buscaHashSenha (String username) throws Exception
    {
        PreparedStatement ps = null;
        Connection con = null;
        ResultSet rs = null;
        
        try
        {
        	String busca = "SELECT senha FROM user WHERE login=?";
        	
            connect();
            con = this.con;
            ps = con.prepareStatement(busca);
            ps.setString(1, username);
            rs = ps.executeQuery();
            
            if (!rs.next() || username==null){
                return null;
            }
            
            String num = rs.getString("senha");
            ConectaBD.closeConnection(con, ps);
            return num;
     
        }
        catch(SQLException sqle)
        {
            throw new Exception("Erro ao checar senha." + sqle);
        }
       
        
    }
	
}
