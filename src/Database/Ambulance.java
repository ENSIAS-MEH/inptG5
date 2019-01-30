package Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Main.Main;

public class Ambulance
{
	static Connection connection=Main.getConnection();
	
	public static void insert() throws SQLException
	{
		int id=count()+1;
		String query="INSERT INTO Ambulance  Values ('"+id+"','true');";
		Statement statement;
		statement =connection.createStatement();
		statement.executeUpdate(query);
	}
	
	public static void setAvailablity (int IdAmbulance, boolean IsAvailable)
	{
		try 
		{
			String query="UPDATE Ambulance SET IsAvailable  = '"+IsAvailable +"' WHERE (IdAmbulance ="+IdAmbulance+")";
			Statement statement=(Statement) connection.createStatement();
			statement.executeUpdate(query);
		}
		catch(Exception e) {System.out.println(e);}
	}
	
	public static int count()
	{
		try 
		{
			String query="SELECT max(IdAmbulance) FROM Ambulance;";
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
