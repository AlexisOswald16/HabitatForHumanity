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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.EditMyProfileModel;
import model.LoginModel;

public class EditMyProfileController implements Initializable {
	Main main = new Main();
	EditMyProfileModel editModel = new EditMyProfileModel();
	private String[] states = { "California", "Alabama", "Arkansas", "Arizona", "Alaska", "Colorado", "Connecticut",
			"Delaware", "Florida", "Georgia", "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa", "Kansas", "Kentucky",
			"Louisiana", "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota", "Mississippi", "Missouri",
			"Montana", "Nebraska", "Nevada", "New Hampshire", "New Jersey", "New Mexico", "New York", "North Carolina",
			"North Dakota", "Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island,", "South Carolina",
			"South Dakota", "Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington", "West Virginia",
			"Wisconsin", "Wyoming" };

	@FXML
	private Label messageLbl;
	@FXML
	private Label userLbl;
	@FXML
	private TextField nameLbl;
	@FXML
	private TextField emailLbl;
	@FXML
	private TextField houseNumLbl;
	@FXML
	private TextField streetLbl;
	@FXML
	private TextField cityLbl;
	@FXML
	private TextField zipLbl;
	@FXML
	private TextField phoneLbl;
	@FXML
	private ComboBox<String> stateLbl;
	@FXML
	private TextField oldPass;
	@FXML
	private TextField newPass;

	public void delete(ActionEvent event) throws IOException {
		 main.changeScene("DeleteAccountView.fxml", messageLbl);
	}

	public void saveProfileDetails(ActionEvent event) throws IOException, SQLException {
		String name = nameLbl.getText();
		String[] split = name.split("\\s+");
		String firstName = split[0];
		String lastName = split[1];

		if (editModel.checkAllParameters(firstName, lastName, emailLbl.getText(), houseNumLbl.getText(),
				streetLbl.getText(), cityLbl.getText(), zipLbl.getText(), phoneLbl.getText(), stateLbl.getValue(),
				userLbl.getText(), oldPass.getText(), newPass.getText()) == true) {
			System.out.println(stateLbl.getValue());
			editModel.updateAccountInfo(firstName, lastName, emailLbl.getText(), houseNumLbl.getText(),
					streetLbl.getText(), cityLbl.getText(), zipLbl.getText(), phoneLbl.getText(), stateLbl.getValue(),
					userLbl.getText(), newPass.getText());
			main.changeScene("MyProfileView.fxml", messageLbl);
		} else {
			messageLbl.setText("One or more fields are incorrect. Please try again.");
		}
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Arrays.sort(states);
		stateLbl.getItems().addAll(states);

		userLbl.setText(LoginModel.current1.getUsername());
		nameLbl.setText(LoginModel.current1.getfName() + " " + LoginModel.current1.getlName());
		emailLbl.setText(LoginModel.current1.getEmail());
		if (LoginModel.current1.getHouseNum() != null) {
			houseNumLbl.setText(LoginModel.current1.getHouseNum());
			streetLbl.setText(LoginModel.current1.getStreetName());
			cityLbl.setText(LoginModel.current1.getCity());
			stateLbl.setValue(LoginModel.current1.getState());
			zipLbl.setText(LoginModel.current1.getZip());
			phoneLbl.setText(LoginModel.current1.getPhone());
		}

	}

}
