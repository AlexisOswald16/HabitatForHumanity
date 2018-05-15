package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controller.SQLiteConnection;

public class ADMINInventoryModel {
	private Connection connection;
	private int numberOfItems = 0;
	private ArrayList<Item> itemList = new ArrayList<>();

	public ADMINInventoryModel() {
		connection = SQLiteConnection.connect();
		if (connection == null) {
			System.exit(1);
		}
	}

	public void addNewItemInDB(Item item) throws SQLException {
		String cat = "";
		String[] arr = item.getCategories();
		for (int i = 0; i < arr.length; i++) {
			cat = cat + arr[i]+",";
		}
		PreparedStatement preparedStatement = null;
		String query = "insert INTO Inventory(IDNumber,Name,Price,Quantity,Category) VALUES(?,?,?,?,?) ";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, item.getIdNumber());
			preparedStatement.setString(2, item.getItemName());
			preparedStatement.setDouble(3, item.getPrice());
			preparedStatement.setInt(4, item.getQuantity());
			preparedStatement.setString(5, cat);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			preparedStatement.close();
		}
	}

	public void updateItemInDB(Item item) throws SQLException {
		PreparedStatement preparedStatement = null;
		String cat = "";
		String[] arr = item.getCategories();
		for (int i = 0; i < arr.length; i++) {
			cat = cat + arr[i]+",";
		}

		String query = "UPDATE Inventory SET IDNumber = ? , Name = ?, Price = ?, Quantity = ? , Category = ? WHERE IDNumber = ?";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, item.getIdNumber());
			preparedStatement.setString(2, item.getItemName());
			preparedStatement.setDouble(3, item.getPrice());
			preparedStatement.setInt(4, item.getQuantity());
			preparedStatement.setString(5, cat);
			preparedStatement.setString(6, item.getIdNumber());

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			preparedStatement.close();
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
				String idNumber = resultSet.getString("IDNumber");
				String name = resultSet.getString("Name");
				Double price = resultSet.getDouble("Price");
				String categories = resultSet.getString("Category");
				String[] categoriesArray = categories.split(" ");
				String imageURL = resultSet.getString("Image");
				int quantity = resultSet.getInt("Quantity");
				if (quantity != 0) {
					Item item = new Item(idNumber, name, price, quantity, categoriesArray, imageURL);
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

	public int getNumberOfItems() {
		return numberOfItems;
	}

	public void setNumberOfItems(int numberOfItems) {
		this.numberOfItems = numberOfItems;
	}
}
