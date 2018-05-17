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
	ObservableList<String> user = FXCollections.observableArrayList();

	int numberOfUsers = 0;

	public ADMINAllUsersModel() {
		connection = SQLiteConnection.connect();
		if (connection == null) {
			System.exit(1);
		}
	}

	public ObservableList<String> getUser(String username) throws SQLException {
		connection = SQLiteConnection.connect();
		String password = "";
		String name = "";
		String email = "";
		String output = "";
		int isAdmin = 0;
		System.out.println("here1");
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String query = "select * from Users where Username = ? ";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, username);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				System.out.println("here2");
				password = resultSet.getString("Password");
				name = resultSet.getString("FirstName") + " " + resultSet.getString("LastName");
				email = resultSet.getString("Email");
				isAdmin = resultSet.getInt("isAdministrator");
				String admin = "";
				if (isAdmin == 1) {
					admin = "Administrator";
				} else {
					admin = "Not An Administrator";
				}
				output = "Username: " + username + "\nName: " + name + "\n" + email + "\n" + admin;
				user.add(output);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			preparedStatement.close();
			resultSet.close();
		}
		return user;
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
