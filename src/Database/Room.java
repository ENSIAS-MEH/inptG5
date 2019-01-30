package Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import Main.Main;

public class Room
{
	static Connection connection=Main.getConnection();

	public static boolean[] getAvailability()
	{
		boolean[] ret=new boolean[count()];
		try 
		{
			String query="SELECT IsAvailable FROM Room;";
			Statement statement=(Statement) connection.createStatement();
			ResultSet resultSet= statement.executeQuery(query);
			int j=0;
			while(resultSet.next())
			{
				if(resultSet.getString(1).equals("true"))
					ret[j]=true;
				else 
					ret[j]=false;
			}
		}
		catch(Exception e) {System.out.println(e);}
		return ret;
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
