package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import controller.SQLiteConnection;

public class LoginModel {
	private Connection connection;
	private String user;
	public static CurrentUser current1;

	public LoginModel() {
		connection = SQLiteConnection.connect();
		if (connection == null) {
			System.exit(1);
		}
	}

	public boolean isLogin(String username, String password) throws SQLException {
		user = username;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String query = "select * from Users where username=? and password=?;";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				getCurrentAccountLoggedIn(username);
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			preparedStatement.close();
			resultSet.close();
		}
		return false;
	}

	public CurrentUser getCurrentAccountLoggedIn(String username) throws SQLException {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String query = "select * from Users where username=?";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, username);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Boolean admin;
				int val = resultSet.getInt("isAdministrator");
				if (val == 1) {
					admin = true;
				} else {
					admin = false;
				}
				if (resultSet.getString("StreetName") != null) {
					current1 = new CurrentUser(resultSet.getString("Username"), resultSet.getString("FirstName"),
							resultSet.getString("LastName"), resultSet.getString("Email"),
							resultSet.getString("StreetNumber"), resultSet.getString("StreetName"),
							resultSet.getString("CityName"), resultSet.getString("State"),
							resultSet.getString("ZipCode"), resultSet.getString("PhoneNumber"), admin);

				} else {
					current1 = new CurrentUser(resultSet.getString("Username"), resultSet.getString("FirstName"),
							resultSet.getString("LastName"), resultSet.getString("Email"), admin);

				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			preparedStatement.close();
			resultSet.close();
		}
		return current1;

	}

	public void printArray(String[] array) {
		for (int i = 0; i < 4; i++) {
			System.out.println(array[i].toString());
		}
	}

}
