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
	
	public static String getName(int IdStaff)
	{
		try 
		{
			String query="SELECT FirstName,LastName FROM HumanResource where IdStaff='"+IdStaff+"';";
			Statement statement=(Statement) connection.createStatement();
			ResultSet resultSet= statement.executeQuery(query);
			if(resultSet.next())
			{
				return (resultSet.getString(1)+resultSet.getString(2));
			}
		}
		catch(Exception e) {System.out.println(e);}
		return "";
	}
	
	public static int[] getDoctorId(int IdSpecialization)
	{
		int lenght=0;
		try 
		{
			String query="SELECT count(*) FROM HumanResource where IdSpecialization='"+IdSpecialization+"';";
			Statement statement=(Statement) connection.createStatement();
			ResultSet resultSet= statement.executeQuery(query);
			if(resultSet.next())
			{
				lenght=resultSet.getInt(1);
			}
			int[] ret = new int[lenght];
			query="SELECT IdStaff FROM HumanResource where IdSpecialization='"+IdSpecialization+"';";
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
	
	public static int[] getAvailbleStaffIdByType(int IdType)
	{
		int lenght=0;
		try 
		{
			String query="SELECT count(*) FROM HumanResource where isAvailable='true' and IdType='"+IdType+"';";
			Statement statement=(Statement) connection.createStatement();
			ResultSet resultSet= statement.executeQuery(query);
			if(resultSet.next())
			{
				lenght=resultSet.getInt(1);
			}
			int[] ret = new int[lenght];
			query="SELECT IdStaff FROM HumanResource where isAvailable='true' and IdType='"+IdType+"';";
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
	
	public static int[] getAvailbleDoctorIdBySpecialization(int IdSpecialization)
	{
		int lenght=0;
		try 
		{
			String query="SELECT count(*) FROM HumanResource where isAvailable='true' and IdSpecialization='"+IdSpecialization+"';";
			Statement statement=(Statement) connection.createStatement();
			ResultSet resultSet= statement.executeQuery(query);
			if(resultSet.next())
			{
				lenght=resultSet.getInt(1);
			}
			int[] ret = new int[lenght];
			query="SELECT IdStaff FROM HumanResource where isAvailable='true' and IdSpecialization='"+IdSpecialization+"';";
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
	
	public static int getType(int IdStaff)
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
	
	public static int getSpecialization(int IdStaff)
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
	
	public static boolean connect(String username, String password)
	{
		
		try 
		{
			String query="Select Password from HumanResource where Username='"+username+"';";
			Statement statement=(Statement) connection.createStatement();
			ResultSet resultSet= statement.executeQuery(query);
			if(resultSet.next())
			{
				return (resultSet.getString(1).equals(password));
			}
		}
		catch(Exception e) {e.printStackTrace();}
		return false;
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
	
	public static int getId(String username)
	{
		try 
		{
			String query="SELECT IdStaff FROM HumanResource where username='"+username+"';";
			Statement statement=(Statement) connection.createStatement();
			ResultSet resultSet= statement.executeQuery(query);
			if(resultSet.next())
			{
				return (resultSet.getInt(1));
			}
		}
		catch(Exception e) {System.out.println(e);}
		return 0;
	}
}
