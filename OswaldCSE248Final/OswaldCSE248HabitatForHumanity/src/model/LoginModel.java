package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginModel {

	private Connection connection;

	public LoginModel() {
		// make connection to database
	}

	public boolean isDBConnected() {
		// check if the database is connected
		return false;
	}

}
