package model;

public class CurrentUser {
	private String username;
	private String fName;
	private String lName;
	private String email;
	private String houseNum;
	private String streetName;
	private String city;
	private String state;
	private String zip;
	private String phone;
	
	public CurrentUser(String username, String fName, String lName, String email, String houseNum, String streetName,
			String city, String state, String zip, String phone) {
		super();
		this.username = username;
		this.fName = fName;
		this.lName = lName;
		this.email = email;
		this.houseNum = houseNum;
		this.streetName = streetName;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.phone = phone;
	}

	public CurrentUser(String username, String fName, String lName, String email) {
		super();
		this.username = username;
		this.fName = fName;
		this.lName = lName;
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHouseNum() {
		return houseNum;
	}

	public void setHouseNum(String houseNum) {
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

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "CurrentUser [username=" + username + ", fName=" + fName + ", lName=" + lName + ", email=" + email
				+ ", houseNum=" + houseNum + ", streetName=" + streetName + ", city=" + city + ", state=" + state
				+ ", zip=" + zip + ", phone=" + phone + "]";
	}
	
	
	
	
}
