package Database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import Main.Main;

public class StaffUse
{
	static Connection connection=Main.getConnection();
	
	public static void insert(int IdEmergency, int IdStaff  ) throws SQLException
	{
		String query="INSERT INTO StaffUse Values ('"+IdEmergency+"','"+IdStaff +"');";
		Statement statement;
		statement =connection.createStatement();
		statement.executeUpdate(query);
	}
}
