package application;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLiteConnection {
	
	public static Connection Connect(){
		try{
			Class.forName("org.sqlite.JDBC"); 
			Connection conn = DriverManager.getConnection("jdbc:sqlite:DatabaseDB.sqlite"); 
			return conn;
			
		} catch(Exception e){
			return null;
		}
	}
}
