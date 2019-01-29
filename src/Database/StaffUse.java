package Database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import Main.Connect;

public class StaffUse
{
	static Connection connection=Connect.getConnection();
	
	public static void insert(int IdEmergency, int IdStaff  ) throws SQLException
	{
		String query="INSERT INTO StaffUse Values ('"+IdEmergency+"','"+IdStaff +"');";
		Statement statement;
		statement =connection.createStatement();
		statement.executeUpdate(query);
	}
}
