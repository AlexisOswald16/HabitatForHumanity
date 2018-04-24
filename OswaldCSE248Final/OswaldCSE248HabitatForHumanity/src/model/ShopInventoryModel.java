package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Stack;

import controller.SQLiteConnection;

public class ShopInventoryModel {
	private Connection connection;
	private Stack<Item> allItems = new Stack<Item>();
	private int numberOfItems = 0;
	private ArrayList<Item> itemList = new ArrayList<>();

	public ShopInventoryModel() {
		connection = SQLiteConnection.connect();
		if (connection == null) {
			System.exit(1);
		}
	}

	public void getInventoryFromDatabase() throws SQLException {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String query = "select * from Inventory";
		try {
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String idNumber = resultSet.getString("ID Number");
				String name = resultSet.getString("Name");
				Double price = resultSet.getDouble("Price");
				String categories = resultSet.getString("Category");
				String[] categoriesArray = categories.split(" ");
				String imageURL = resultSet.getString("Image");
				int quantity = resultSet.getInt("Quantity");
				if (quantity != 0) {
					Item item = new Item(idNumber, name, price, quantity, categoriesArray, imageURL);
					allItems.push(item);
					itemList.add(item);
					numberOfItems++;
				}

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

	public Stack<Item> getAllItems() {
		return allItems;
	}

	public void setAllItems(Stack<Item> allItems) {
		this.allItems = allItems;
	}

	public int getNumberOfItems() {
		return numberOfItems;
	}

	public void setNumberOfItems(int numberOfItems) {
		this.numberOfItems = numberOfItems;
	}

}
