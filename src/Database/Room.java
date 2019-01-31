package Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import Main.Main;

public class Room
{
	static Connection connection=Main.getConnection();

	public static int[] getAvailble()
	{
		int lenght=0;
		try 
		{
			String query="SELECT count(*) FROM Room where isAvailable='true';";
			Statement statement=(Statement) connection.createStatement();
			ResultSet resultSet= statement.executeQuery(query);
			if(resultSet.next())
			{
				lenght=resultSet.getInt(1);
			}
			int[] ret = new int[lenght];
			query="SELECT IdRoom FROM Room where isAvailable='true';";
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
	
	public static int getFee(int IdRoom)
	{
		try 
		{
			String query="SELECT Fee FROM Room where IdRoom='"+IdRoom+"';";
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
	
	public static void setStatus(int idRoom, boolean status)
	{
		try 
		{
			String query="UPDATE Room SET status = '"+status+"' WHERE (idRoom ="+idRoom+")";
			Statement statement=(Statement) connection.createStatement();
			statement.executeUpdate(query);
		}
		catch(Exception e) {System.out.println(e);}
	}
	
	public static int count()
	{
		try 
		{
			String query="SELECT count(*) FROM Room;";
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
