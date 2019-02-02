package inptG5;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class User {
	private int id;
	private String username;
	private String password;
	
	public User(int id, String username, String password) {
		this.id=id;
		this.username=username;
		this.password=password;
	}
	
	public void useradd(Connection Con) throws Exception {
		Statement st = Con.createStatement();
		st.executeUpdate("INSERT INTO logindata " + "VALUES ("+ this.id +"," + this.username + "," + this.password + ")" );
	}
	
	public static void userdel(int id, Connection Con) throws Exception {
		Statement st = Con.createStatement();
		st.executeQuery("DELETE FROM logindata WHERE id=%"+id);
		System.out.println("DELETE FROM logindata WHERE id=%"+id);
	}
	
	
	public void main (String[] args) {
		
	}
}

