package Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import Main.Main;

public class PatientHistory
{
	static Connection connection=Main.getConnection();
	
	public static void insert(int IdPatient, int IdDoctor, String Sickness, String Treatement, Date date, int Amount ) throws SQLException
	{
		int id=count()+1;
		String query="INSERT INTO PatientHistory Values ('"+id+"','"+IdPatient+"','"+IdDoctor+"','"+Sickness+"','"+Treatement+"','"+date+"','"+Amount +"');";
		Statement statement;
		statement =connection.createStatement();
		statement.executeUpdate(query);

	}
	
	public static int count()
	{
		try 
		{
			String query="SELECT max(IdHistory) FROM PatientHistory;";
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
	
	public static ResultSet getResultSetByPatient(int IdPatient)
	{
		try 
		{
			String query="SELECT * FROM PatientHistory where IdPatient='"+IdPatient+"';";
			Statement statement=(Statement) connection.createStatement();
			return statement.executeQuery(query);
		}
		catch(Exception e) {System.out.println(e);}
		return null;
	}
}
