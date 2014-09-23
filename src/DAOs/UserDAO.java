package DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Sistema.ConectaBD;
import Sistema.Digest;
import Sistema.RandomNumber;
import Sistema.User;

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
            hashSenha = digest.getDigest("SHA1", hashSenha);
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
	
	/*public User buscaPessoa (String nome) throws Exception
    {
        PreparedStatement ps = null;
        Connection con = null;
        ResultSet rs = null;
        User c = new User();
        
        try
        {
        	String busca = "SELECT * FROM cliente WHERE nome LIKE '%' || ? || '%';";
        	
            connect();
            con = this.con;
            ps = con.prepareStatement(busca);
            ps.setString(1, nome);
            rs = ps.executeQuery();
            
            if (!rs.next()){
                return null;
            }
            
            c.setId(rs.getInt("id"));
            c.setNome(rs.getString("nome"));
            c.setDatanascimento(rs.getString("datanascimento"));
            c.setEmail(rs.getString("email"));
            c.setSexo(rs.getString("sexo"));
            c.setEndereco(rs.getString("endereco"));
            c.setCpf(rs.getString("cpf"));
            c.setTelefone(rs.getString("telefone"));
            c.setCelular(rs.getString("celular"));
            c.setBairro(rs.getString("bairro"));
            c.setDiabetico(rs.getString("diabetico"));
            c.setObservacoes(rs.getString("observacoes"));
     
        }
        catch(SQLException sqle)
        {
            throw new Exception("Erro ao inserir." + sqle);
        }
       
        ConectaBD.closeConnection(con, ps);
        return c;
    }*/
	
}
