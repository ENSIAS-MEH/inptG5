package Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Main.Main;

public class Log
{
	static Connection connection=Main.getConnection();
	public static void insert(int IdStaff, String History, int ConcerningId) throws SQLException
	{
		int id=count()+1;
		String query="INSERT INTO Log Values ('"+id+"','"+IdStaff+"','"+History+"','"+ConcerningId+"');";
		Statement statement;
		statement =connection.createStatement();
		statement.executeUpdate(query);
	}
	
	public static int count()
	{
		try 
		{
			String query="SELECT max(idLog) FROM Log;";
			Statement statement=(Statement) connection.createStatement();
			ResultSet resultSet= statement.executeQuery(query);
			if(resultSet.next())
			{
				return resultSet.getInt(1);
			}
		}
		catch(Exception e) {System.out.println(e);}
		return 0;
	}
	
	public static ResultSet getResultSet()
	{
		try 
		{
			String query="SELECT * FROM Log;";
			Statement statement=(Statement) connection.createStatement();
			return statement.executeQuery(query);

		}
		catch(Exception e) {System.out.println(e);}
		return null;
	}
}
