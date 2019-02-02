package Database;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import javax.swing.JTable;

import Main.Main;
import net.proteanit.sql.DbUtils;
/******************************************
 * cette classe contient les diffentes methodes permettant de manipuler la table patient history dans la db
 
 */

public class PatientHistory
{
	static Connection connection=Main.getConnection();
	
	/*******************************************
	 * ajout des infos à l'historique du patient
	 * @param IdPatient
	 * @param IdDoctor
	 * @param Sickness
	 * @param Treatement
	 * @param date date de visite
	 * @param Amount
	 * @throws SQLException
	 */
	public static void insert(int IdPatient, int IdDoctor, String Sickness, String Treatement, Date date, int Amount ) throws SQLException
	{
		int id=count()+1;
		String query="INSERT INTO PatientHistory Values ('"+id+"','"+IdPatient+"','"+IdDoctor+"','"+Sickness+"','"+Treatement+"','"+date+"','"+Amount +"');";
		Statement statement;
		statement =connection.createStatement();
		statement.executeUpdate(query);

	}
	/****************************************
	 * nmmbre de visite du patient à cet hopital
	 * @return
	 */
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
	
	public static ResultSet getResultSetByPatient(String firstname,String lastname)
	{
		try 
		{
			String query="SELECT * FROM PatientHistory where FirstName='"+firstname+"'and LastName='"+lastname+"';";
			Statement statement=(Statement) connection.createStatement();
			return statement.executeQuery(query);
		}
		catch(Exception e) {System.out.println(e);}
		return null;
	}
	/***************************************
	 * avoir l'hisory du patient à partir de son id
	 * @param id id du patient
	 * @return
	 */
	public static ResultSet getResultSetByPatient1(int id)
	{
		try 
		{
			String query="SELECT * FROM PatientHistory where idPatient='"+id+"';";
			Statement statement=(Statement) connection.createStatement();
			return statement.executeQuery(query);
		}
		catch(Exception e) {System.out.println(e);}
		return null;
	}
	
	
	/*****************************************************
	 * afficher le resultats d'une requete dans un Jtable
	 * @param table une table à remplir avec les infos
	 * @param rs resultats d'une requete
	 */
	public static void update(JTable table,ResultSet rs){
		
		
		try{
			
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
		
		}
		catch(Exception e)
		{
			
			System.out.println(e);
		}
		
	}
}
