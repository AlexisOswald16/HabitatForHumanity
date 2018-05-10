package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import controller.SQLiteConnection;

public class ReviewOrderModel {
	private Connection connection;
	private String[] itemNumbers;
	private String[] itemQuantities;

	public ReviewOrderModel() {
		connection = SQLiteConnection.connect();
		if (connection == null) {
			System.exit(1);
		}
	}

	public void getCartFromDB() throws SQLException {
		PreparedStatement preparedStatement = null;
		String itemString = "";
		String quantitiesString = "";
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
		}
		itemNumbers = itemString.split(",");
		itemQuantities = quantitiesString.split(",");

	}

	public String convertArraysIntoItems(){
		String itemString = "";
		for(int i = 0 ; i < itemNumbers.length; i++){
			ItemForOrder item = new ItemForOrder(itemNumbers[i], Integer.parseInt(itemQuantities[i]));
			itemString = itemString + item;
		}
		return itemString;
		
	}

	public void addOrderToDatabase() throws SQLException {
		getCartFromDB();
		connection = SQLiteConnection.connect();
		PreparedStatement preparedStatement = null;
		String user = LoginModel.current1.getfName() + " " + LoginModel.current1.getlName();
		String productId = convertArraysIntoItems();
		double totalCost = CheckOutModel.currentOrder.getPrice();
		Address s = CheckOutModel.currentOrder.getShippingAddress();
		String shipping = s.formatAddressForDB();
		Address b = CheckOutModel.currentOrder.getBillingAddress();
		String billing = b.formatAddressForDB();
		Card c = CheckOutModel.currentOrder.getCard();
		String card = c.formatCardForDB();

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		String d = formatter.format(date);

		connection = SQLiteConnection.connect();

		String query = "insert INTO Orders(User,ProductID,TotalCost,Shipping,Billing,Card,Date) VALUES(?,?,?,?,?,?,?) ";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, user);
			preparedStatement.setString(2, productId);
			preparedStatement.setDouble(3, totalCost);
			preparedStatement.setString(4, shipping);
			preparedStatement.setString(5, billing);
			preparedStatement.setString(6, shipping);
			preparedStatement.setString(6, card);
			preparedStatement.setString(7, d);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			preparedStatement.close();
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
				}
			}
		}
	}
}
