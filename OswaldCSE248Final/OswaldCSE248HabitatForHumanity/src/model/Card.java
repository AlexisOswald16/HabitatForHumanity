package model;

public class Card {
	private String nameOnCard;
	private String cardNumber;
	private String expirationDate;
	private String ccv;

	public Card(String nameOnCard, String cardNumber, String expirationDate, String ccv) {
		super();
		this.nameOnCard = nameOnCard;
		this.cardNumber = cardNumber;
		this.expirationDate = expirationDate;
		this.ccv = ccv;
	}

	public String getNameOnCard() {
		return nameOnCard;
	}

	public void setNameOnCard(String nameOnCard) {
		this.nameOnCard = nameOnCard;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getCcv() {
		return ccv;
	}

	public void setCcv(String ccv) {
		this.ccv = ccv;
	}

	@Override
	public String toString() {
		String[] number = cardNumber.split("");
		String cardForDisplay = "";
		int numberOfDigits = number.length;
		for (int i = 0; i < numberOfDigits - 4; i++) {
			cardForDisplay = cardForDisplay + "x";
		}
		for (int i = numberOfDigits - 4; i < numberOfDigits; i++) {
			cardForDisplay = cardForDisplay + number[i];
		}

		return "Name on Card: " + nameOnCard + "\n " + cardForDisplay + "\n Exp: " + expirationDate;
	}

}
