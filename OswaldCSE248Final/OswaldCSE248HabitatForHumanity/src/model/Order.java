package model;

public class Order {
	private Address shippingAddress;
	private Address billingAddress;
	private Card card;
	private String items;
	private double price;

	public Order(Address shippingAddress, Address billingAddress, Card card, String items, double price) {
		super();
		this.shippingAddress = shippingAddress;
		this.billingAddress = billingAddress;
		this.card = card;
		this.items = items;
		this.price = price;
	}

	public Address getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(Address shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public Address getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public String getItems() {
		return items;
	}

	public void setItems(String items) {
		this.items = items;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String forDisplay() {
		return "Shipping Address: \n" + shippingAddress + " \n\n" + card + "\n\n" + "Price: $" +price+"\n ---------------------------------------------------------------------";
	}

	@Override
	public String toString() {
		return shippingAddress + "&" + billingAddress + "&" + card + "&" + items + "&" + price;
	}

}
