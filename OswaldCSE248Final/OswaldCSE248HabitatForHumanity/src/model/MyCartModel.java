package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controller.SQLiteConnection;

public class MyCartModel {
	private Connection connection;
	private ArrayList<Item> itemList;
	private String itemString;
	private String quantitiesString;

	public MyCartModel() {
		connection = SQLiteConnection.connect();
		if (connection == null) {
			System.exit(1);
		}
	}

	public void getArrays() {
		String[] itemNumbers = itemString.split(",");
		String[] itemQuantities = quantitiesString.split(",");
	}

	public void getInventoryFromDatabase() throws SQLException {
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
				getArrays();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			preparedStatement.close();
			resultSet.close();
		}

	}

	public ArrayList<Item> getItemList() {
		return itemList;
	}

	public void setItemList(ArrayList<Item> itemList) {
		this.itemList = itemList;
	}

	public String getItemString() {
		return itemString;
	}

	public void setItemString(String itemString) {
		this.itemString = itemString;
	}

	public String getQuantitiesString() {
		return quantitiesString;
	}

	public void setQuantitiesString(String quantitiesString) {
		this.quantitiesString = quantitiesString;
	}
	

}
