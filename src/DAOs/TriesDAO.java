package DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Sistema.ConectaBD;
import Sistema.Digest;
import Sistema.User;
import Suporte.RandomNumber;

public class TriesDAO {

	private Connection con;

	public TriesDAO() throws Exception
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

	public void insereTentativa (String username) throws Exception
	{
		PreparedStatement ps = null;
		Connection con = null;
		ResultSet rs = null;

		int tentativas = buscaTentativa(username);

		if (tentativas==0) {
			try
			{
				String insert = "INSERT INTO tries (login, tries) VALUES" +
						"(?,?)";

				connect();
				con = this.con;
				ps = con.prepareStatement(insert);
				ps.setString(1, username);
				ps.setInt(2, tentativas+1);
				ps.executeUpdate();
			}
			catch(SQLException sqle)
			{
				throw new Exception("Erro ao inserir." + sqle);
			}
		}
		else {
			try
			{
				String update = "UPDATE tries SET tries= ? WHERE login=?";

				connect();
				con = this.con;
				ps = con.prepareStatement(update);
				ps.setInt(1, tentativas+1);
				ps.setString(2, username);
				ps.executeUpdate();
			}
			catch(SQLException sqle)
			{
				throw new Exception("Erro ao atualizar." + sqle);
			}
		}

		ConectaBD.closeConnection(con, ps);
	}

	public Integer buscaTentativa (String username) throws Exception
	{
		PreparedStatement ps = null;
		Connection con = null;
		ResultSet rs = null;
		int retorno;

		try
		{
			String busca = "SELECT tries FROM tries WHERE login=?";

			connect();
			con = this.con;
			ps = con.prepareStatement(busca);
			ps.setString(1, username);
			rs = ps.executeQuery();

			if (!rs.next()){
				retorno=0;
			} else {
				retorno=rs.getInt("tries");
			}

			ConectaBD.closeConnection(con, ps);
			return retorno;

		}
		catch(SQLException sqle)
		{
			throw new Exception("Erro ao buscar tentativa." + sqle);
		}


	}
	
	public void removeTentativas() throws Exception
	{
		PreparedStatement ps = null;
		Connection con = null;

		try
		{
			String busca = "DELETE FROM tries";

			connect();
			con = this.con;
			ps = con.prepareStatement(busca);
			ps.executeUpdate();

			ConectaBD.closeConnection(con, ps);

		}
		catch(SQLException sqle)
		{
			throw new Exception("Erro ao remover." + sqle);
		}


	}

}
