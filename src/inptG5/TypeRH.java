package inptG5;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class TypeRH {
	
	private int idt;
	private String tname;
	
	
	public TypeRH(int idt, String tname) {
		this.setIdt(idt);
		this.setTname(tname);
	}
	
	//---------------------------------------getName
	public static String getNamesq(int IdType, Connection con) throws SQLException {
		String r="";
		try {
			ResultSet rs= DBconnect.get_result(con, "SELECT Name FROM HumanResourceType where IdType='"+IdType+"';");
			while(rs.next()) {
				r= rs.getString(1);
			}
		}
		catch(Exception e) {System.out.println(e);}
		return r;
	}
	//---------------------------------------
	public static int getIdsq(String Name, Connection con) throws SQLException {
		int r=-1;
		try {
			ResultSet rs= DBconnect.get_result(con, "SELECT IdType FROM HumanResourceType where Name='"+Name+"';");
			while(rs.next()) {
				r= rs.getInt(1);
			}
		}
		catch(Exception e) {System.out.println(e);}
		return r;
	}
	//--------------------------------------getAlltp
	public static ObservableList<String> getAlltp(Connection con) {
		ObservableList<String> specs = FXCollections.observableArrayList();
		try {
			ResultSet rs = DBconnect.get_result(con, "SELECT Name FROM HumanResourceType;");
			while(rs.next()) {
				specs.add(rs.getString(1));
			}
		}
		catch(Exception e) {System.out.println(e);}
		return specs;
	}
	
	//------------------------------Getters and Setters
	
	public int getIdt() {
		return idt;
	}


	public void setIdt(int idt) {
		this.idt = idt;
	}


	public String getTname() {
		return tname;
	}


	public void setTname(String tname) {
		this.tname = tname;
	}
}
