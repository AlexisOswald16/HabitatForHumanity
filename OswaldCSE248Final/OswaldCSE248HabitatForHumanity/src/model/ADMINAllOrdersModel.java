package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controller.SQLiteConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ADMINAllOrdersModel {
	Connection connection;
	ObservableList<String> allOrders = FXCollections.observableArrayList();
	int numberOfItems = 0;
	double totalPrice = 0;
	int numberOfOrders = 0;

	public ADMINAllOrdersModel() {
		connection = SQLiteConnection.connect();
		if (connection == null) {
			System.exit(1);
		}
	}

	public ObservableList<String> getAllOrdersFromDatabase() throws SQLException {
		connection = SQLiteConnection.connect();
		String shipping = "";
		String billing = "";
		String card = "";
		String items = "";
		double price = 0;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String query = "select * from Orders ";
		try {
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				shipping = resultSet.getString("Shipping");
				billing = resultSet.getString("Billing");
				card = resultSet.getString("Card");
				items = resultSet.getString("ProductID");
				price = resultSet.getDouble("TotalCost");

				Address billingAddress = splitStringIntoAddress(billing);
				Address shippingAddress = splitStringIntoAddress(shipping);
				Card cardInfo = splitStringIntoCard(card);
				String item = splitStringIntoItemsArray(items);
				Order currentOrder = new Order(shippingAddress, billingAddress, cardInfo, item, price);
				allOrders.add(currentOrder.forDisplay());
				totalPrice = totalPrice + price;
				numberOfItems = numberOfItems + items.length() / 4;
				numberOfOrders++;
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
		return allOrders;
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

	public int getNumberOfItems() {
		return numberOfItems;
	}

	public void setNumberOfItems(int numberOfItems) {
		this.numberOfItems = numberOfItems;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getNumberOfOrders() {
		return numberOfOrders;
	}

	public void setNumberOfOrders(int numberOfOrders) {
		this.numberOfOrders = numberOfOrders;
	}

}