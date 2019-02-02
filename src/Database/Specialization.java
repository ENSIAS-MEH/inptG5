package Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Main.Main;

public class Specialization
{
	static Connection connection=Main.getConnection();
	
	public static String getName(int IdSpecialization) throws SQLException
	{
		try 
		{
			String query="SELECT Name FROM Specialization where IdSpecialization='"+IdSpecialization+"';";
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
	
	public static int getFee(int IdSpecialization) throws SQLException
	{
		try 
		{
			String query="SELECT Fee FROM Specialization where IdSpecialization='"+IdSpecialization+"';";
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
