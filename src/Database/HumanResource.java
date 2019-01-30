package Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Main.Main;

public class HumanResource
{
	static Connection connection=Main.getConnection();
	
	public static void insert(String firstname, String lastname,String username,String password, int idType,int IdSpecialization ) throws SQLException
	{
		int id=count()+1;
		String query="INSERT INTO HumanResource Values ('"+id+"','"+firstname+"','"+lastname+"','"+username+"','"+password+"','true','"+idType+"','"+IdSpecialization+"');";
		Statement statement;
		statement =connection.createStatement();
		statement.executeUpdate(query);
	}
	
	public static int getType(int IdStaff) throws SQLException
	{
		try 
		{
			String query="SELECT IdType FROM HumanResource where IdStaff='"+IdStaff+"';";
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
	
	public static int getSpecialization(int IdStaff) throws SQLException
	{
		try 
		{
			String query="SELECT IdSpecialization  FROM HumanResource where IdStaff='"+IdStaff+"';";
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
	
	
	public static void setAvailablity(int IdStaff, boolean IsAvailable)
	{
		try 
		{
			String query="UPDATE HumanResource SET IsAvailable  = '"+IsAvailable +"' WHERE (IdStaff ="+IdStaff+")";
			Statement statement=(Statement) connection.createStatement();
			statement.executeUpdate(query);
		}
		catch(Exception e) {System.out.println(e);}
	}
	
	public static int count()
	{
		try 
		{
			String query="SELECT max(IdStaff) FROM HumanResource;";
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
