package controller;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLiteConnection {
	
	public static Connection connect() {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:HabitatForHumanityDB.db");
			return conn;
		} catch(Exception e) {
			return null;
		}
	}

}
