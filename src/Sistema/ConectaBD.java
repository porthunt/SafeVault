package Sistema;

import java.sql.*;

public class ConectaBD {
    public static Connection getConnection() throws Exception
    {
    	Connection c;
        try
        {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:SafeVaultDB.db");
            return c;

        }
        catch(Exception e)
        {
        	e.printStackTrace();
        	throw new Exception(e.getMessage());
        }
    }

    public static void closeConnection(
            Connection conn, Statement stmt, ResultSet rs)
            throws Exception
    {
        close(conn,stmt,rs);
    }

    public static void closeConnection (
            Connection conn, Statement stmt)
            throws Exception
    {
        close(conn,stmt,null);
    }

    public static void closeConnection(Connection conn)
            throws Exception
    {
        close(conn,null,null);
    }

    private static void close (
            Connection conn, Statement stmt, ResultSet rs)
            throws Exception
    {
        try
        {
            if(rs!=null) rs.close();
            if(stmt!=null) stmt.close();
            if(conn!=null) conn.close();
        }
        catch(Exception e)
        {
            throw new Exception(e.getMessage());
        }
    }
}
