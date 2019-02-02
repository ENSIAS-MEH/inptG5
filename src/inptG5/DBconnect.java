package inptG5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;



public class DBconnect {
     
     public static Connection get_connection(){
    	 Connection connection=null;
    	 try { 
    		 Class.forName("org.sqlite.JDBC");
    		 connection=DriverManager.getConnection("jdbc:sqlite:database.db");
    	 	}catch (Exception e) {
    	 		System.err.println("[Communication with server ERROR]"+e);
    	 	}
    	 System.out.println(connection);
    	 return connection;
	}
     
     
     public static ResultSet get_result(Connection C, String req) {
    	Statement st;
 		ResultSet rs = null;
 		try {
			st = C.createStatement();
			rs=st.executeQuery(req);
 		}catch(Exception e) {
 			e.printStackTrace();
 		}
 		return rs;
     }
     
     public static void Close(Connection C) {
    	 if (C!=null) {
    		 try {
    			 C.close();
    		 }catch( Exception e) {
    			 e.printStackTrace();
    		 }
    	 }
    	 
     }
     
     
     public static int count(String tablename, Connection connection) {
 		int size=0;
 		try {
 			ResultSet rs = DBconnect.get_result(connection, "SELECT max(Id"+tablename+") AS SIZE FROM "+tablename+";");
 			while(rs.next()) {
 				size=rs.getInt(1);
 			}
 		}catch (Exception e) {
 		System.out.println(e);	
 		}
 		return size;
 	}
     
     
     
     public static void main(String[] args) {
    		Connection connection=null;
    		connection=DBconnect.get_connection();
    		ResultSet rs = DBconnect.get_result(connection, "SELECT Longitude, Latitude FROM Emergency");
			try {
				while(rs.next())
					System.out.println(
							rs.getDouble(2));
					connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
	    	DBconnect.Close(connection);
			
   }
}