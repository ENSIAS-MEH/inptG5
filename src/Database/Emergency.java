package Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import Main.Main;

public class Emergency
{
	static Connection connection=Main.getConnection();
	
	public static void insert(int IdPatient, Date OccurenceDate, String Location,double Latitude,double longitude, int priority) throws SQLException
	{
		int id=count()+1;
		String query="INSERT INTO Emergency Values ('"+id+"','"+IdPatient+"','"+OccurenceDate+"','','"+Location+"','"+Latitude+"','"+longitude+"','"+priority+"','Waiting');";
		Statement statement;
		statement =connection.createStatement();
		statement.executeUpdate(query);
	}
	
	public static String getStatus(int IdEmergency) throws SQLException
	{
		try 
		{
			String query="SELECT Status FROM Emergency where IdEmergency='"+IdEmergency+"';";
			Statement statement=(Statement) connection.createStatement();
			ResultSet resultSet= statement.executeQuery(query);
			if(resultSet.next())
			{
				return resultSet.getString(1);
			}
		}
		catch(Exception e) {System.out.println(e);}
		return "";
	}
	
	public static int count()
	{
		try 
		{
			String query="SELECT max(IdEmergency) FROM Emergency;";
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
