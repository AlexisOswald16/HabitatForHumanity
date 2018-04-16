package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import controller.SQLiteConnection;

public class EditMyProfileModel {
	Connection connection;

	public Connection connectToDB() {
		connection = SQLiteConnection.connect();
		if (connection == null) {
			System.exit(1);
		}
		return connection;
	}

	public void updateAccountInfo(String firstName, String lastName, String email, String streetNum, String streetName,
			String cityName, String zipCode, String phoneNumber, String state, String username) throws SQLException {

		// NOT WORKING
		connectToDB();
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		String query = "UPDATE Users SET FirstName = ? , " + "LastName = ? " + "Email = ?" + "StreetNumber = ?"
				+ "StreetName = ?" + "CityName = ?" + "ZipCode = ?" + "PhoneNumber = ?" + "State = ?"
				+ "WHERE username = ?";
		try (Connection conn = this.connectToDB(); PreparedStatement pstmt = conn.prepareStatement(query)) {
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
