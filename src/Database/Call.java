package Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import Main.Main;

public class Call
{
	static Connection connection=Main.getConnection();
	public static void insert(String firstname, String lastname, Date date, int idEmergency) throws SQLException
	{
		int id=count()+1;
		String query="INSERT INTO Call Values ('"+id+"','"+firstname+"','"+lastname+"','"+date+"','"+idEmergency+"');";
		Statement statement;
		statement =connection.createStatement();
		statement.executeUpdate(query);

	}
	
	public static int count()
	{
		try 
		{
			String query="SELECT max(IdCall) FROM Call;";
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
			String query="SELECT * FROM Call;";
			Statement statement=(Statement) connection.createStatement();
			return statement.executeQuery(query);

		}
		catch(Exception e) {System.out.println(e);}
		return null;
	}
}
