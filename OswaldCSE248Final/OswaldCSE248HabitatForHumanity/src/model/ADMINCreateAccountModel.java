package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import controller.SQLiteConnection;

public class ADMINCreateAccountModel {
	private Connection connection;

	public void connectToDB() {
		connection = SQLiteConnection.connect();
		if (connection == null) {
			System.exit(1);
		}
	}

	public boolean checkPassword(String password, String rePassword) {
		if (password.equals(rePassword)) {
			return true;
		}
		return false;
	}

	public boolean checkPasswordCriteria(String password) {
		if (password.length() < 5 || checkForUppercase(password) == false) {
			return false;
		}
		return true;

	}

	public boolean checkForUppercase(String password) {
		for (int i = 0; i < password.length(); i++) {
			if (Character.isUpperCase(password.charAt(i))) {
				return true;
			}
		}
		return false;
	}

	public boolean checkEmailFormat(String email) {
		Pattern pattern = Pattern.compile(
				"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		Matcher matcher = pattern.matcher(email);
		if (matcher.matches()) {
			return true;
		}
		return false;
	}

	public boolean checkEmail(String email) throws SQLException {
		connectToDB();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String query = "select * from Users where email=? ";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, email);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
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

	public boolean checkUsername(String username) throws SQLException {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String query = "select * from Users where username=? ";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, username);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
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

	public void createNewAccount(String username, String password, String firstName, String lastName, String email,
			boolean admin) throws SQLException {
		PreparedStatement preparedStatement = null;
		int isAdmin;
		
		if (admin == true) {
			isAdmin = 1;
		}else{
			isAdmin = 0;
		}
		String query = "insert INTO Users(username,password,firstName,lastName,email,isAdministrator) VALUES(?,?,?,?,?,?) ";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			preparedStatement.setString(3, firstName);
			preparedStatement.setString(4, lastName);
			preparedStatement.setString(5, email);
			preparedStatement.setInt(6, isAdmin);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			preparedStatement.close();
		}
	}
}
