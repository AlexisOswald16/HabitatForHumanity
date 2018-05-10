package model;

public class Address {
	private String name;
	private int houseNum;
	private String streetName;
	private String city;
	private String state;
	private int zip;
	private String phone;

	public Address(String name, int houseNum, String streetName, String city, String state, int zip, String phone) {
		super();
		this.name = name;
		this.houseNum = houseNum;
		this.streetName = streetName;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHouseNum() {
		return houseNum;
	}

	public void setHouseNum(int houseNum) {
		this.houseNum = houseNum;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String formatAddressForDB() {
		return name + "&" + houseNum + "&" + streetName + "&" + city + "&" + state + "&" + zip + "&" + phone;
	}

	@Override
	public String toString() {

		return name + " \n" + houseNum + " " + streetName + ", " + city + ", " + state + " " + zip + " " + phone;
	}

}
