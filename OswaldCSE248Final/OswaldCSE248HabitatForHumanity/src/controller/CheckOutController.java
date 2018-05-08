package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.ResourceBundle;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.CheckOutModel;

public class CheckOutController implements Initializable {
	Main main = new Main();
	CheckOutModel com = new CheckOutModel();

	private String[] states = { "California", "Alabama", "Arkansas", "Arizona", "Alaska", "Colorado", "Connecticut",
			"Delaware", "Florida", "Georgia", "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa", "Kansas", "Kentucky",
			"Louisiana", "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota", "Mississippi", "Missouri",
			"Montana", "Nebraska", "Nevada", "New Hampshire", "New Jersey", "New Mexico", "New York", "North Carolina",
			"North Dakota", "Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island,", "South Carolina",
			"South Dakota", "Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington", "West Virginia",
			"Wisconsin", "Wyoming" };
	private String[] months = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
			"October", "November", "December" };
	private String[] years = { "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028",
			"2029", "2030", "2031", "2032", "2033", "2034", "2035" };

	String[] shippingAddress;
	String[] billingAddress;
	String[] cardInfo;

	@FXML
	private Label messageLbl;

	@FXML
	private TextField fName1;
	@FXML
	private TextField fName2;
	@FXML
	private TextField lName1;
	@FXML
	private TextField lName2;
	@FXML
	private TextField houseNum1;
	@FXML
	private TextField houseNum2;
	@FXML
	private TextField streetName1;
	@FXML
	private TextField streetName2;
	@FXML
	private TextField city1;
	@FXML
	private TextField city2;
	@FXML
	private TextField zip1;
	@FXML
	private TextField zip2;
	@FXML
	private TextField phone1;
	@FXML
	private TextField phone2;

	@FXML
	private ComboBox<String> state1;
	@FXML
	private ComboBox<String> state2;

	@FXML
	private ComboBox<String> month;
	@FXML
	private ComboBox<String> year;

	@FXML
	private TextField cardName;
	@FXML
	private TextField cardNum;
	@FXML
	private TextField cvcNum;

	@FXML
	private CheckBox sameAddress;

	public void reviewDetails(ActionEvent event) throws IOException, SQLException {
		getAllFieldValues();
		String output = com.checkAllFields(shippingAddress, billingAddress, cardInfo);
		if (output.equals("correct")) {
			main.changeScene("ReviewOrderView.fxml", messageLbl);
		} else {
			messageLbl.setText(output);
		}
	}

	public void getAllFieldValues() {
		shippingAddress = new String[8];
		billingAddress = new String[8];
		cardInfo = new String[5];

		shippingAddress[0] = fName1.getText();
		shippingAddress[1] = lName1.getText();
		shippingAddress[2] = houseNum1.getText();
		shippingAddress[3] = streetName1.getText();
		shippingAddress[4] = city1.getText();
		shippingAddress[5] = state1.getSelectionModel().getSelectedItem();
		shippingAddress[6] = zip1.getText();
		shippingAddress[7] = phone1.getText();

		billingAddress[0] = fName2.getText();
		billingAddress[1] = lName2.getText();
		billingAddress[2] = houseNum2.getText();
		billingAddress[3] = streetName2.getText();
		billingAddress[4] = city2.getText();
		billingAddress[5] = state2.getSelectionModel().getSelectedItem();
		billingAddress[6] = zip2.getText();
		billingAddress[7] = phone2.getText();

		cardInfo[0] = cardName.getText();
		cardInfo[1] = cardNum.getText();
		cardInfo[2] = cvcNum.getText();
		cardInfo[3] = month.getSelectionModel().getSelectedItem();
		cardInfo[4] = year.getSelectionModel().getSelectedItem();

	}

	public void checkIsChecked(ActionEvent event) {
		if (sameAddress.isSelected()) {
			fName2.setText(fName1.getText());
			lName2.setText(lName1.getText());
			houseNum2.setText(houseNum1.getText());
			streetName2.setText(streetName1.getText());
			city2.setText(city1.getText());
			int s = state1.getSelectionModel().getSelectedIndex();
			state2.getSelectionModel().select(s);
			zip2.setText(zip1.getText());
			phone2.setText(phone1.getText());
		} else {
			clearBilling();
		}
	}

	public void clearBilling() {
		fName2.setText("");
		lName2.setText("");
		houseNum2.setText("");
		streetName2.setText("");
		city2.setText("");
		state2.getSelectionModel().selectFirst();
		zip2.setText("");
		phone2.setText("");
	}

	public void shop(ActionEvent event) throws IOException {
		main.changeScene("ShopView.fxml", messageLbl);
	}

	public void myProfile(ActionEvent event) throws IOException {
		main.changeScene("MyProfileView.fxml", messageLbl);
	}

	public void myCart(ActionEvent event) throws IOException {
		main.changeScene("MyCartView.fxml", messageLbl);

	}

	public void orders(ActionEvent event) throws IOException {
		main.changeScene("MyOrdersView.fxml", messageLbl);
	}

	public void help(ActionEvent event) throws IOException {
		main.changeScene("HelpView.fxml", messageLbl);
	}

	public void logout(ActionEvent event) throws IOException {
		main.changeScene("LogoutView.fxml", messageLbl);

	}

	public void cancel(ActionEvent event) throws IOException {
		main.changeScene("MyCartView.fxml", messageLbl);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Arrays.sort(states);
		state1.getItems().addAll(states);
		state2.getItems().addAll(states);
		month.getItems().addAll(months);
		year.getItems().addAll(years);

		// if the profile has an address saved, automatically load it into the
		// specific fields in shipping

	}

}
