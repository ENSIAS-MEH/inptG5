package Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Main.Main;

public class Patient
{
	static Connection connection=Main.getConnection();
	public static void insert(String firstname, String lastname,String gender,int age, double weight, double height, String bloodtype) throws SQLException
	{
		int id=count()+1;
		String query="INSERT INTO Patient Values ('"+id+"','"+firstname+"','"+lastname+"','"+gender+"','"+age+"','"+weight+"','"+height+"','"+bloodtype+"');";
		Statement statement;
		statement =connection.createStatement();
		statement.executeUpdate(query);

	}
	
	public static int getId(String FirstName,String LastName)
	{
		try 
		{
			String query="SELECT IdPatient FROM Patient where Firstname='"+FirstName+"' and Lastname='"+LastName+"';";
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
	
	public static int count()
	{
		try 
		{
			String query="SELECT max(IdPatient) FROM Patient;";
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
