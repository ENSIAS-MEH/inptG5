package Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import Main.Main;

public class InpatientCare
{
	static Connection connection=Main.getConnection();
	public static void insert(int IdPatient , int IdDoctor, int IdRoom, int idNurse)
	{
		try
		{
			int id=count()+1;
			String query="INSERT INTO InpatientCare  Values ('"+id+"','"+IdPatient +"','"+IdDoctor+"','"+IdRoom+"','"+idNurse+"');";
			Statement statement;
			statement =connection.createStatement();
			statement.executeUpdate(query);
		}
		catch(Exception e) {}
	}
	
	public static int count()
	{
		try 
		{
			String query="SELECT max(IdCare) FROM InpatientCare ;";
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
