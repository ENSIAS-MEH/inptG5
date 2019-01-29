package Database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import Main.Connect;

public class InpatientResource
{
	static Connection connection=Connect.getConnection();

	public static void insert(int IdStaff, int IdCare	) throws SQLException
	{
		String query="INSERT INTO InpatientResource Values ('"+IdStaff+"','"+IdCare  +"');";
		Statement statement;
		statement =connection.createStatement();
		statement.executeUpdate(query);
	}
}
