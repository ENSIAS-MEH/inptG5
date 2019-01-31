package Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import Main.Main;

public class StaffUse
{
	static Connection connection=Main.getConnection();
	
	public static void insert(int IdEmergency, int IdStaff)
	{
		try
		{
			String query="INSERT INTO StaffUse Values ('"+IdEmergency+"','"+IdStaff +"');";
			Statement statement;
			statement =connection.createStatement();
			statement.executeUpdate(query);
		}
		catch(Exception e) {}
		
	}
	
	public static int getDriverUsed(int IdEmergency)
	{
		try 
		{
			String query="SELECT IdStaff FROM StaffUse where IdEmergency='"+IdEmergency+"';";
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
