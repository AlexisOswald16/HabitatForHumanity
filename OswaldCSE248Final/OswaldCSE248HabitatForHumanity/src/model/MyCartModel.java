package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import controller.SQLiteConnection;

public class MyCartModel {
	private Connection connection;
	private ArrayList<Item> allItems = new ArrayList<>();
	private String itemString;
	private String quantitiesString;
	private ArrayList<String> quantities;
	private ArrayList<String> itemNums;

	public MyCartModel() {
		connection = SQLiteConnection.connect();
		if (connection == null) {
			System.exit(1);
		}
	}

	public void convertStringsToArrays() {
		quantities = new ArrayList<String>(Arrays.asList(quantitiesString.split(",")));
		itemNums = new ArrayList<String>(Arrays.asList(itemString.split(",")));
	}

	public boolean deleteFromCart(String num) throws SQLException {
		if (itemNums.size() > 1) {
			for (int i = 0; i < itemNums.size(); i++) {
				if (itemNums.get(i).equals(num)) {
					quantities.remove(i);
					itemNums.remove(i);
					updateExistingCart(itemNums, quantities);
					return true;
				}
			}
		} else {
			quantities.remove(0);
			itemNums.remove(0);
			updateExistingCart(itemNums, quantities);
			return true;
		}

		return false;

	}

	public void updateExistingCart(ArrayList<String> itemNumber, ArrayList<String> quantity) throws SQLException {
		if (itemNumber.size() != 0) {
			String itemNums = itemNumber.get(0);
			String quantities = quantity.get(0);
			for (int i = 1; i < itemNumber.size(); i++) {
				itemNums = itemNums + "," + itemNumber.get(i);
				quantities = quantities + "," + quantity.get(i);
			}

			PreparedStatement preparedStatement2 = null;

			String query2 = "UPDATE Carts SET Items = ? , Quantities = ? WHERE user = ?";
			try {
				preparedStatement2 = connection.prepareStatement(query2);
				preparedStatement2.setString(1, itemNums);
				preparedStatement2.setString(2, quantities);
				preparedStatement2.setString(3, LoginModel.current1.getUsername());
				preparedStatement2.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				preparedStatement2.close();
			}
		} else {
			PreparedStatement preparedStatement1 = null;
			String item = "";
			String quant = "";
			String query1 = "UPDATE Carts SET Items = ? , Quantities = ? WHERE user = ?";
			try {
				preparedStatement1 = connection.prepareStatement(query1);
				preparedStatement1.setString(1, item);
				preparedStatement1.setString(2, quant);
				preparedStatement1.setString(3, LoginModel.current1.getUsername());
				preparedStatement1.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				preparedStatement1.close();
			}
		}

	}

	public void getCartFromDatabase() throws SQLException {
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
		}
		if(itemString != null){
			convertStringsToArrays();

		}

	}

	public ArrayList<Item> getAllItems() {
		return allItems;
	}

	public void setAllItems(ArrayList<Item> allItems) {
		this.allItems = allItems;
	}

	public ArrayList<String> getQuantities() {
		return quantities;
	}

	public void setQuantities(ArrayList<String> quantities) {
		this.quantities = quantities;
	}

	public ArrayList<String> getItemNums() {
		return itemNums;
	}

	public void setItemNums(ArrayList<String> itemNums) {
		this.itemNums = itemNums;
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
					allItems.add(item);
				}

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			preparedStatement.close();
			resultSet.close();
		}
	}

	

}
