package inptG5;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;



public class Specialization {
	private int idsp;
	private String Spname;
	
	public Specialization(int idsp, String Spname) {
		this.setIdsp(idsp);
		this.setSpname(Spname);
	}
	
	public static String getNamesq(int IdSpecialization, Connection con) throws SQLException {
		String r = "";
		try {
			ResultSet rs = DBconnect.get_result(con, "SELECT Name FROM Specialization where IdSpecialization='"+IdSpecialization+"';");
			while(rs.next()) {
				r= rs.getString(1);
			}
		}
		catch(Exception e) {System.out.println(e);}
		return r;
	}
	
	
	public static int getIdsq(String spec, Connection con) throws SQLException {
		int r = -1;
		try {
			ResultSet rs = DBconnect.get_result(con, "SELECT IdSpecialization FROM Specialization where Name='"+spec+"';");
			while(rs.next()) {
				r= rs.getInt(1);
			}
		}
		catch(Exception e) {System.out.println(e);}
		return r;
	}
	
	public static ObservableList<String> getAllsp(Connection con) {
		ObservableList<String> specs = FXCollections.observableArrayList();
		try {
			ResultSet rs = DBconnect.get_result(con, "SELECT Name FROM Specialization;");
			while(rs.next()) {
				specs.add(rs.getString(1));
			}
		}
		catch(Exception e) {System.out.println(e);}
		return specs;
	}
	

	public int getIdsp() {
		return idsp;
	}

	public void setIdsp(int idsp) {
		this.idsp = idsp;
	}

	public String getSpname() {
		return Spname;
	}

	public void setSpname(String spname) {
		Spname = spname;
	}
}
