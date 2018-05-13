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
	private int numberOfItems = 0;
	private ArrayList<Item> itemList = new ArrayList<>();

	public ShopInventoryModel() {
		connection = SQLiteConnection.connect();
		if (connection == null) {
			System.exit(1);
		}
	}

	public boolean checkIfCartExists() throws SQLException {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String query = "select * from Carts where user=? ";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, LoginModel.current1.getUsername());
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			preparedStatement.close();
			resultSet.close();
		}
		return false;

	}

	public boolean checkIfQuantityIsValid(String itemNumber, String quantity) {
		int quant = Integer.parseInt(quantity);
		for (Item item : itemList) {
			if (item.getQuantity() >= quant) {
				return true;
			}
			return false;
		}

		return false;

	}

	public boolean checkIfItemNumberExists(String itemNumber) {
		for (Item item : itemList) {
			if (item.getIdNumber().equals(itemNumber)) {
				return true;
			}
		}
		return false;
	}

	public void createNewCart(String itemNumber, String quantity) throws SQLException {
		PreparedStatement preparedStatement = null;
		itemNumber = itemNumber + ",";
		quantity = quantity + ",";
		String query = "insert INTO Carts(user,items,quantities) VALUES(?,?,?) ";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, LoginModel.current1.getUsername());
			preparedStatement.setString(2, itemNumber);
			preparedStatement.setString(3, quantity);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			preparedStatement.close();
		}

	}

	public void addToCart(String itemNumber, String quantity) throws SQLException {
		if (checkIfCartExists() == false) {
			createNewCart(itemNumber, quantity);
		} else {
			updateExistingCart(itemNumber, quantity);
		}

	}

	public void updateExistingCart(String itemNumber, String quantity) throws SQLException {
		String oldItemNumber = null;
		String oldQuantity = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String query = "select * from Carts where user=? ";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, LoginModel.current1.getUsername());
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				oldItemNumber = resultSet.getString("Items");
				oldQuantity = resultSet.getString("Quantities");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			preparedStatement.close();
			resultSet.close();
		}

		itemNumber = oldItemNumber + "," + itemNumber;
		quantity = oldQuantity + "," + quantity;

		PreparedStatement preparedStatement2 = null;

		String query2 = "UPDATE Carts SET Items = ? , Quantities = ? WHERE user = ?";
		try {
			preparedStatement2 = connection.prepareStatement(query2);
			preparedStatement2.setString(1, itemNumber);
			preparedStatement2.setString(2, quantity);
			preparedStatement2.setString(3, LoginModel.current1.getUsername());
			preparedStatement2.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			preparedStatement2.close();
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
