package Database;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Main.Main;
/***************************************************************
 * cette classe contient les diffentes methodes permettant de manipuler la table patient dans la DB
 * @author User
 * 
 *
 *
 */
public class Patient
{
	static Connection connection=Main.getConnection();
	/***************************************************************************
	 * inserer les informations d'un nouveau patient
	 * @param firstname
	 * @param lastname
	 * @param gender
	 * @param age
	 * @param weight
	 * @param height
	 * @param bloodtype
	 * @throws SQLException
	 */
	public static void insert(String firstname, String lastname,String gender,int age, double weight, double height, String bloodtype) throws SQLException
	{
		int id=count()+1;
		String query="INSERT INTO Patient Values ('"+id+"','"+firstname+"','"+lastname+"','"+gender+"','"+age+"','"+weight+"','"+height+"','"+bloodtype+"');";
		Statement statement;
		statement =connection.createStatement();
		statement.executeUpdate(query);

	}
	/**********************************************************************
	 * avoir l'id du patient à partir du firstname et lastname
	 * @param FirstName
	 * @param LastName
	 * @return
	 */
	
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
		return -1;
	}
	/***************************************************************************
	 * le nombre des patients
	 * @return
	 */
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
	/**************************************************
	 * les informations du patient à partir du firstname and lastname
	 * @param firstname
	 * @param lastname
	 * @return
	 */
	public static ResultSet getResultSet(String firstname,String lastname)
	{
		try 
		{
			String query="SELECT * FROM Patient where FirstName='"+firstname+"'and LastName='"+lastname+"';";
			Statement statement=(Statement) connection.createStatement();
			return statement.executeQuery(query);

		}
		catch(Exception e) {System.out.println(e);}
		return null;
	}
	public static ResultSet getResultSet2(int id)
	{
		try 
		{
			String query="SELECT * FROM Patient where idPatient='"+id+"';";
			Statement statement=(Statement) connection.createStatement();
			return statement.executeQuery(query);

		}
		catch(Exception e) {System.out.println(e);}
		return null;
	}
	/***********************************************************
	 * les informations du patient à partir du firstname et lastname sous forme d'un tableau
	 * @param firstname
	 * @param lastname
	 * @return
	 */
	public static String[] getResultSet1(String firstname,String lastname) 
	{
		ResultSet rs=getResultSet(firstname,lastname);
		int i=0;
		String tab[]=new String[8];
		try {
			while(rs.next())
			{
				tab[i]=rs.getString(i);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tab;
		
	}
	public static String[] getResultSet3(int id) 
	{
		ResultSet rs=getResultSet2(id);
		int i=0;
		String tab[]=new String[8];
		try {
			while(rs.next())
			{
				tab[i]=rs.getString(i);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tab;
		
	}
	
}
