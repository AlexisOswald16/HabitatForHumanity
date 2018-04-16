package model;

import java.sql.Connection;

import controller.SQLiteConnection;

public class MyProfileModel {
	private Connection connection;

	public MyProfileModel() {
		connection = SQLiteConnection.connect();
		if (connection == null) {
			System.exit(1);
		}
	}

	
	
	


}
