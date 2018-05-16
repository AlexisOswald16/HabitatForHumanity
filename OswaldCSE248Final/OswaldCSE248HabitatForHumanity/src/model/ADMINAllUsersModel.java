package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import controller.SQLiteConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ADMINAllUsersModel {
	Connection connection;
	ObservableList<String> allUsers = FXCollections.observableArrayList();
	int numberOfUsers = 0;

	public ADMINAllUsersModel() {
		connection = SQLiteConnection.connect();
		if (connection == null) {
			System.exit(1);
		}
	}

	public ObservableList<String> getAllUsersFromDatabase() throws SQLException {
		connection = SQLiteConnection.connect();
		String username = "";
		String password = "";
		String name = "";
		String email = "";
		String output = "";
		int isAdmin = 0;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String query = "select * from Users ";
		try {
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				username = resultSet.getString("Username");
				password = resultSet.getString("Password");
				name = resultSet.getString("FirstName") + " " + resultSet.getString("LastName");
				email = resultSet.getString("Email");
				isAdmin = resultSet.getInt("isAdministrator");
				numberOfUsers++;
				String admin = "";
				if (isAdmin == 1) {
					admin = "Administrator";
				} else {
					admin = "Not An Administrator";
				}
				output = "Username: " + username + "\nName: " + name + "\n" + email + "\n" + admin;
				allUsers.add(output);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			preparedStatement.close();
			resultSet.close();
		}
		return allUsers;
	}

	public int getNumberOfUsers() {
		return numberOfUsers;
	}

	public void setNumberOfUsers(int numberOfUsers) {
		this.numberOfUsers = numberOfUsers;
	}

}
