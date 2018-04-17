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
			String oldPass, String newPass) {

		// check if old pass = old pass in database,
		// if it does, then return true

		if (isValidZip(zipCode) == false) {
			return false;
		}

		if (isValidPhone(phoneNumber) == false) {
			return false;
		}

		return true;
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
			String cityName, String zipCode, String phoneNumber, String state, String username, String oldPass,
			String newPass) throws SQLException {
		PreparedStatement preparedStatement = null;

		// need to implement addition of new password (delete old, then add new)

		String query = "UPDATE Users SET FirstName = ? , LastName = ?, Email = ?, StreetNumber = ?, StreetName = ?, CityName = ?, ZipCode = ?, PhoneNumber = ?, State = ? WHERE username = ?";
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
			preparedStatement.setString(10, username);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			preparedStatement.close();
		}
	}
}
