package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.sqlite.util.StringUtils;

import controller.SQLiteConnection;

public class CheckOutModel {
	private Connection connection;

	public CheckOutModel() {
		connection = SQLiteConnection.connect();
		if (connection == null) {
			System.exit(1);
		}
	}

	public boolean isAllNumbers(String s) {
		String regex = "[0-9]+";
		if (s.matches(regex)) {
			return true;
		}
		return false;
	}

	public String checkAllFields(String[] shipping, String[] billing, String[] card) throws SQLException {
		if (checkIfBlank(shipping) == true || checkIfBlank(billing) == true || checkIfBlank(card) == true) {
			return "One or more fields are blank. Please try again.";
		} else if (isZipValid(shipping) == false || isZipValid(billing) == false) {
			return "Zip code must contain 5 digits 0-9. Please try again.";
		} else if (isPhoneValid(shipping) == false || isPhoneValid(billing) == false) {
			return "Phone number must contain 10 digits and no other characters. Please try again.";
		} else if (isAllNumbers(card[2]) == false) {
			return "CCV code must only contain numbers 0-9. Please try again.";
		} else if (isAllNumbers(card[1]) == false) {
			return "Card Number must only contain numbers 0-9. Please try again.";
		} else if (isAllNumbers(shipping[2]) == false || isAllNumbers(billing[2]) == false) {
			return "House number must only contain numbers 0-9. Please try again.";
		} else {
			createNewCard(card);
			createNewAddress(shipping);
			createNewAddress(billing);
			return "correct";
		}
	}

	public void createNewAddress(String[] array) {
		String name = array[0] + " " + array[1];
		int houseNum = Integer.parseInt(array[2]);
		String street = array[3];
		String city = array[4];
		String state = array[5];
		int zip = Integer.parseInt(array[6]);
		String phone = array[7];
		Address address1 = new Address(name, houseNum, street, city, state, zip, phone);
		// add address to database
		// gets this address when go to review order screen
	}

	public boolean isPhoneValid(String[] address) {
		if (address[7].length() != 10 || isAllNumbers(address[7]) == false) {
			return false;
		}
		return true;
	}

	public boolean isZipValid(String[] address) {
		if (address[6].length() != 5 || isAllNumbers(address[6]) == false) {
			return false;
		}
		return true;
	}

	public void createNewCard(String[] cardInfo) throws SQLException {
		String expiration = cardInfo[3] + "/" + cardInfo[4];
		Card newCard = new Card(cardInfo[0], cardInfo[1], expiration, cardInfo[2]);
		addCardToDatabase(newCard);
	}

	public void addCardToDatabase(Card card) throws SQLException {
		PreparedStatement preparedStatement = null;
		String cardnum = card.getCardNumber();
		String name = card.getNameOnCard();
		String ccv = card.getCcv();
		String expiration = card.getExpirationDate();
		String query = "insert INTO Cards(CardNumber,Name,CCV,expiration) VALUES(?,?,?,?) ";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, cardnum);
			preparedStatement.setString(2, name);
			preparedStatement.setString(3, ccv);
			preparedStatement.setString(4, expiration);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			preparedStatement.close();
		}
	}

	public boolean checkIfBlank(String[] array) {
		for (int i = 0; i < array.length; i++) {
			if (array[i].equals("") || array[i].equals(" ")) {
				return true;
			}
		}
		return false;
	}

}
