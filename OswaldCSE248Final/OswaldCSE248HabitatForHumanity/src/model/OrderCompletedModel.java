package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controller.SQLiteConnection;

public class OrderCompletedModel {
	private Connection connection;
	private int orderNumber = 0;

	public OrderCompletedModel() {
		connection = SQLiteConnection.connect();
		if (connection == null) {
			System.exit(1);
		}
	}

	public int getOrderNumberFromDatabase() throws SQLException {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String query = "select MAX(Number) as maxID from Orders ";
		try {
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				orderNumber = resultSet.getInt("maxID");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			preparedStatement.close();
			if (resultSet != null) {
				resultSet.close();
			}
		}

		return orderNumber;
	}

	public String setCardOutput(Card card) {
		String last4 = "";
		String[] cardNum = card.getCardNumber().split("");
		int length = cardNum.length;
		last4 = cardNum[length - 1];
		last4 = cardNum[length - 2] + last4;
		last4 = cardNum[length - 3] + last4;
		last4 = cardNum[length - 4] + last4;
		String output = "Card ending in: " + last4;
		return output;
	}

	public String setItemOutputString(String[] itemNumbers, ArrayList<String> quantities) {
		String itemName = "";
		String itemOutputString = "";
		for(int j = 0; j <itemNumbers.length; j++){
			System.out.println(itemNumbers[j]);
		}
		for (int i = 0; i < itemNumbers.length; i++) {
			try {
				itemName = getItemFromDatabase(itemNumbers[i]);
				itemOutputString = itemOutputString + "Quantity: " + quantities.get(i) + "     " + itemName + "\n";

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return itemOutputString;
	}

	public String getItemFromDatabase(String itemNumber) throws SQLException {
		connection = SQLiteConnection.connect();
		String itemName = "";
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String query = "select * from Inventory where IDNumber =? ";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, itemNumber);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				itemName = resultSet.getString("Name");
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
		return itemName;
	}

	public Order getOrderFromDatabase(int orderNumber) throws SQLException {
		connection = SQLiteConnection.connect();
		String shipping = "";
		String billing = "";
		String card = "";
		String items = "";
		double price = 0;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String query = "select * from Orders where Number=? ";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, orderNumber);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				shipping = resultSet.getString("Shipping");
				billing = resultSet.getString("Billing");
				card = resultSet.getString("Card");
				items = resultSet.getString("ProductID");
				price = resultSet.getDouble("Billing");

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			preparedStatement.close();
			resultSet.close();
		}
		Address billingAddress = splitStringIntoAddress(billing);
		Address shippingAddress = splitStringIntoAddress(shipping);
		Card cardInfo = splitStringIntoCard(card);
		String item = splitStringIntoItemsArray(items);
		Order currentOrder = new Order(shippingAddress, billingAddress, cardInfo, item, price);
		return currentOrder;
	}

	public String splitStringIntoItemsArray(String items) {
		String[] itemsArray = items.split("&");
		String item = "";
		for (int i = 0; i < itemsArray.length; i++) {
			item = item + itemsArray[i];
		}
		return item;
	}

	public Card splitStringIntoCard(String c) {
		String[] cardArray = c.split("&");
		String nameOnCard = cardArray[0];
		String cardNum = cardArray[1];
		String expiration = cardArray[2];
		String ccv = cardArray[3];
		Card card = new Card(nameOnCard, cardNum, expiration, ccv);
		return card;
	}

	public Address splitStringIntoAddress(String address) {
		String[] addressArray = address.split("&");
		String name = addressArray[0];
		int houseNum = Integer.parseInt(addressArray[1]);
		String streetName = addressArray[2];
		String city = addressArray[3];
		String state = addressArray[4];
		int zip = Integer.parseInt(addressArray[5]);
		String phone = addressArray[6];
		Address a = new Address(name, houseNum, streetName, city, state, zip, phone);
		return a;
	}
}
