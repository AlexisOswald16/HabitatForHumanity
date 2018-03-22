package application;

import java.sql.Connection;
import java.sql.SQLException;

public class LoginModel {

	private Connection connection;
	
	public LoginModel(){
		connection = SQLiteConnection.Connect();
		if(connection == null){
			System.exit(1);
		}
	}
	
	public boolean isDBConnected(){
		try{
			return !connection.isClosed();
		} catch(SQLException e){
			return false;
		}
	}
}
