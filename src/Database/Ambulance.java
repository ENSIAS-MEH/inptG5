package Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import Main.Main;

public class Ambulance
{
	static Connection connection=Main.getConnection();
	
	public static void insert()
	{
		try
		{
			
		int id=count()+1;
		String query="INSERT INTO Ambulance  Values ('"+id+"','true');";
		Statement statement;
		statement =connection.createStatement();
		statement.executeUpdate(query);
		}
		catch(Exception e) {}
	}
	
	public static int[] getAvailble()
	{
		int lenght=0;
		try 
		{
			String query="SELECT count(*) FROM Ambulance where isAvailable='true';";
			Statement statement=(Statement) connection.createStatement();
			ResultSet resultSet= statement.executeQuery(query);
			if(resultSet.next())
			{
				lenght=resultSet.getInt(1);
			}
			int[] ret = new int[lenght];
			query="SELECT IdAmbulance FROM Ambulance where isAvailable='true';";
			resultSet= statement.executeQuery(query);
			int i=0;
			while(resultSet.next())
			{
				ret[i]=resultSet.getInt(1);
				i++;
			}
			return ret;
		}
		catch(Exception e) {System.out.println(e);}
		return null;
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
