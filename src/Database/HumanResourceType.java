package Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Main.Main;

public class HumanResourceType
{
	static Connection connection=Main.getConnection();
	
	public static String getName(int IdType) throws SQLException
	{
		try 
		{
			String query="SELECT Name FROM HumanResourceType where IdType='"+IdType+"';";
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
}
