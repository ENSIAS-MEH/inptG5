package Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Main.Main;

public class Emergency
{
	static Connection connection=Main.getConnection();
	
	public static void insert(int IdPatient, String OccurenceDate, String Location,double Latitude,double longitude, int priority) throws SQLException
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
	
	public static void setStatus(int IdEmergency, String Status)
	{
		try 
		{
			String query="UPDATE Emergency SET Status= '"+Status +"' WHERE (IdEmergency ="+IdEmergency+")";
			Statement statement=(Statement) connection.createStatement();
			statement.executeUpdate(query);
		}
		catch(Exception e) {System.out.println(e);}
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
	
	public static ResultSet getResultSet()
	{
		try 
		{
			String query="SELECT * FROM Emergency;";
			Statement statement=(Statement) connection.createStatement();
			return statement.executeQuery(query);

		}
		catch(Exception e) {System.out.println(e);}
		return null;
	}
	public static ResultSet getResultSetByStatus(String status)
	{
		try 
		{
			String query="SELECT * FROM Emergency where Status='"+status+"';";
			Statement statement=(Statement) connection.createStatement();
			return statement.executeQuery(query);
		}
		catch(Exception e) {System.out.println(e);}
		return null;
	}
}
