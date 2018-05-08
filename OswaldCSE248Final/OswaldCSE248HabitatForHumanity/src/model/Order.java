package model;

public class Order {
	private String shippingAddress;
	private String billingAddress;
	private String cardInfo;
	private String items;

	public Order(String shippingAddress, String billingAddress, String cardInfo, String items) {
		super();
		this.shippingAddress = shippingAddress;
		this.billingAddress = billingAddress;
		this.cardInfo = cardInfo;
		this.items = items;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public String getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}

	public String getCardInfo() {
		return cardInfo;
	}

	public void setCardInfo(String cardInfo) {
		this.cardInfo = cardInfo;
	}

	public String getItems() {
		return items;
	}

	public void setItems(String items) {
		this.items = items;
	}

}
