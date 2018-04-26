package model;

import java.util.Arrays;

public class Item {
	private String idNumber;
	private String itemName;
	private double price;
	private int quantity;
	private String[] categories;
	private String imageURL;
	
	public Item(String idNumber, String itemName, double price, int quantity, String[] categories, String imageURL) {
		super();
		this.idNumber = idNumber;
		this.itemName = itemName;
		this.price = price;
		this.quantity = quantity;
		this.categories = categories;
		this.imageURL = imageURL;
	}
	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String[] getCategories() {
		return categories;
	}
	public void setCategories(String[] categories) {
		this.categories = categories;
	}
	public String getImageURL() {
		return imageURL;
	}
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	@Override
	public String toString() {
		return "Item [idNumber=" + idNumber + ", itemName=" + itemName + ", price=" + price + ", quantity=" + quantity
				+ ", categories=" + Arrays.toString(categories) + ", imageURL=" + imageURL + "]";
	}
	
	
}
