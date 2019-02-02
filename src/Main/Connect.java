package Main;


import java.sql.DriverManager;
import java.sql.Connection;

public class Connect
{
	private static Connection connection;
	public static void main(String[] args)
	{
		try 
		{
			Class.forName("org.sqlite.JDBC");
			connection=DriverManager.getConnection("jdbc:sqlite:database.db");
		}
		catch(Exception e) {e.printStackTrace();}
	}
	
	public static Connection getConnection()
	{
		return connection;
	}
}
