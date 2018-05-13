package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import org.sqlite.util.StringUtils;

import controller.MyCartController;
import controller.SQLiteConnection;

public class CheckOutModel {
	private Connection connection;
	private ArrayList<ItemForOrder> items = new ArrayList<>();
	public static Order currentOrder;

	public CheckOutModel() {
		connection = SQLiteConnection.connect();
		if (connection == null) {
			System.exit(1);
		}
	}

	public boolean checkCardNum(String s) {
		if (s.length() < 10) {
			return false;
		}
		return true;
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
		} else if (checkCardNum(card[1]) == false) {
			return "Card Number must be at least 10 digits. Please try again.";
		} else {
			Card newCard = createNewCard(card);
			Address shippingAddress = createNewAddress(shipping);
			Address billingAddress = createNewAddress(billing);
			currentOrder = createOrder(shippingAddress, billingAddress, newCard);

			return "correct";
		}
	}

	public Address createNewAddress(String[] array) throws SQLException {
		String name = array[0] + " " + array[1];
		int houseNum = Integer.parseInt(array[2]);
		String street = array[3];
		String city = array[4];
		String state = array[5];
		int zip = Integer.parseInt(array[6]);
		String phone = array[7];
		Address address1 = new Address(name, houseNum, street, city, state, zip, phone);
		return address1;
	}

	public Order createOrder(Address shipping, Address billing, Card card) throws SQLException {
		String allItems = "";
		for (int i = 0; i < items.size(); i++) {
			allItems = allItems + " " + items.get(i);
		}

		double price = MyCartController.priceTotal;
		Order currentOrder = new Order(shipping, billing, card, allItems, price);
		return currentOrder;
	}

	public void getCartFromDatabase() throws SQLException {
		connection = SQLiteConnection.connect();
		String itemString = "";
		String quantitiesString = "";
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String query = "select * from Carts where user=? ";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, LoginModel.current1.getUsername());
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				itemString = resultSet.getString("Items");
				quantitiesString = resultSet.getString("Quantities");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			preparedStatement.close();
			resultSet.close();
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
				}
			}
		}
		convertArraysIntoItems(itemString, quantitiesString);

	}

	public void convertArraysIntoItems(String itemString, String quantitiesString) {
		ArrayList<String> quantities = new ArrayList<String>(Arrays.asList(quantitiesString.split(",")));
		ArrayList<String> itemNums = new ArrayList<String>(Arrays.asList(itemString.split(",")));

		for (int i = 0; i < quantities.size(); i++) {
			int quant = Integer.parseInt(quantities.get(i));
			ItemForOrder currentItem = new ItemForOrder(itemNums.get(i), quant);
			items.add(currentItem);
		}

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

	public Card createNewCard(String[] cardInfo) throws SQLException {
		String expiration = cardInfo[3] + "/" + cardInfo[4];
		Card newCard = new Card(cardInfo[0], cardInfo[1], expiration, cardInfo[2]);
		return newCard;
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
