package model;

import java.sql.Connection;

import controller.SQLiteConnection;

public class CheckOutModel {
	private Connection connection;

	public CheckOutModel() {
		connection = SQLiteConnection.connect();
		if (connection == null) {
			System.exit(1);
		}
	}

	public void checkAllFields() {

	}

}
