package model;

import java.sql.Connection;
import java.util.ArrayList;

import controller.SQLiteConnection;

public class ShopInventoryModel {
	private Connection connection;
	private ArrayList<Item> allItems = new ArrayList<Item>();
	private int numberOfItems = 0;

	public void connectToDB() {
		connection = SQLiteConnection.connect();
		if (connection == null) {
			System.exit(1);
		}
	}

	public void getInventoryFromDatabase() {
		// when each item is accessed, create a new item object and then insert
		// it into the arraylist 'allItems' and increment number of items

		// return allItems

	}
}
