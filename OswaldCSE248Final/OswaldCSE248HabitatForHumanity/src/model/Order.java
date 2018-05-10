package model;

public class Order {
	private Address shippingAddress;
	private Address billingAddress;
	private Card card;
	private String items;

	public Order(Address shippingAddress, Address billingAddress, Card card, String items) {
		super();
		this.shippingAddress = shippingAddress;
		this.billingAddress = billingAddress;
		this.card = card;
		this.items = items;
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

	@Override
	public String toString() {
		return shippingAddress + "%" + billingAddress + "%" + card + "%" + items;
	}

}
