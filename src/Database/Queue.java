package Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import Main.Connect;

public class Queue
{
	static Connection connection=Connect.getConnection();
	
	public static void insert(int IdPatient,int IdDoctor) throws SQLException
	{
		int id=count()+1;
		
		Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
        String strDate = dateFormat.format(date);
        
		String query="INSERT INTO Queue Values ('"+id+"','"+strDate+"','"+IdPatient+"','"+IdDoctor+"');";
		Statement statement;
		statement =connection.createStatement();
		statement.executeUpdate(query);
	}
	
	public static int count()
	{
		try 
		{
			String query="SELECT max(IdQueue) FROM Queue;";
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
}
