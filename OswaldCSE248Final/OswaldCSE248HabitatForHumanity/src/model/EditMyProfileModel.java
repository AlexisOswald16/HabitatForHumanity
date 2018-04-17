package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import controller.SQLiteConnection;

public class EditMyProfileModel {
	Connection connection;

	public EditMyProfileModel() {
		connection = SQLiteConnection.connect();
		if (connection == null) {
			System.exit(1);
		}
	}

	public boolean checkAllParameters(String firstName, String lastName, String email, String streetNum,
			String streetName, String cityName, String zipCode, String phoneNumber, String state, String username,
			String oldPass, String newPass) throws SQLException {
	
		if (checkOldPassword(oldPass, newPass, username) == false) {
			System.out.println("old");
			return false;
		} else if (checkPasswordCriteria(newPass) == false) {
			System.out.println("new");
			return false;
		} else if (isValidZip(zipCode) == false) {
			System.out.println("zip");
			return false;
		} else if (isValidPhone(phoneNumber) == false) {
			System.out.println("phone");
			return false;
		} else
			return true;
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

	public boolean checkOldPassword(String oldPass, String newPass, String username) throws SQLException {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String query = "select * from Users where username=?";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, username);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				if (resultSet.getString("Password").equals(oldPass)) {
					checkPasswordCriteria(newPass);
					return true;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			preparedStatement.close();
			resultSet.close();
		}
		return false;
	}

	public boolean isValidZip(String zip) {
		if (zip.length() != 5) {
			return false;
		}
		return true;
	}

	public boolean isValidPhone(String phone) {
		if (phone.length() != 10) {
			return false;
		}
		return true;
	}

	public void updateAccountInfo(String firstName, String lastName, String email, String streetNum, String streetName,
			String cityName, String zipCode, String phoneNumber, String state, String username, String newPass)
			throws SQLException {
		PreparedStatement preparedStatement = null;

		String query = "UPDATE Users SET FirstName = ? , LastName = ?, Email = ?, StreetNumber = ?, StreetName = ?, CityName = ?, ZipCode = ?, PhoneNumber = ?, State = ?, Password = ? WHERE username = ?";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, firstName);
			preparedStatement.setString(2, lastName);
			preparedStatement.setString(3, email);
			preparedStatement.setString(4, streetNum);
			preparedStatement.setString(5, streetName);
			preparedStatement.setString(6, cityName);
			preparedStatement.setString(7, zipCode);
			preparedStatement.setString(8, phoneNumber);
			preparedStatement.setString(9, state);
			preparedStatement.setString(10, newPass);
			preparedStatement.setString(11, username);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			preparedStatement.close();
		}
	}
}
