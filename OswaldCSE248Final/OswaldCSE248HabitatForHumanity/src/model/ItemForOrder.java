package model;

public class ItemForOrder {
	String itemNumber;
	int quantity;

	public ItemForOrder(String itemNumber, int quantity) {
		super();
		this.itemNumber = itemNumber;
		this.quantity = quantity;
	}

	public String getItemNumber() {
		return itemNumber;
	}

	public void setItemNumber(String itemNumber) {
		this.itemNumber = itemNumber;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return itemNumber + "," + quantity + "&";
	}

}
