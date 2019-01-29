package Database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import Main.Connect;

public class AmbulanceUse
{
	static Connection connection=Connect.getConnection();
	
	public static void insert(int IdEmergency, int IdAmbulance ) throws SQLException
	{
		String query="INSERT INTO AmbulanceUse Values ('"+IdEmergency+"','"+IdAmbulance+"');";
		Statement statement;
		statement =connection.createStatement();
		statement.executeUpdate(query);
	}
}
