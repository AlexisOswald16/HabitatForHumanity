package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import model.LoginModel;

public class MyProfileController implements Initializable {
	Main main = new Main();

	@FXML
	private Label messageLbl;
	@FXML
	private Label userLbl;
	@FXML
	private Label nameLbl;
	@FXML
	private Label emailLbl;
	@FXML
	private Label houseNumLbl;
	@FXML
	private Label streetLbl;
	@FXML
	private Label cityLbl;
	@FXML
	private Label zipLbl;
	@FXML
	private Label phoneLbl;
	@FXML
	private Label stateLbl;


	public void editAccountDetails(ActionEvent event) throws IOException {
		main.changeScene("EditMyProfileView.fxml", messageLbl);
	}

	public void shop(ActionEvent event) throws IOException {
		main.changeScene("ShopView.fxml", messageLbl);
	}

	public void myProfile(ActionEvent event) throws IOException, SQLException {
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
		userLbl.setText(LoginModel.current1.getUsername());
		nameLbl.setText(LoginModel.current1.getfName() + " " + LoginModel.current1.getlName());
		emailLbl.setText(LoginModel.current1.getEmail());
		if(LoginModel.current1.getHouseNum() != null){
			houseNumLbl.setText(LoginModel.current1.getHouseNum());
			streetLbl.setText(LoginModel.current1.getStreetName());
			cityLbl.setText(LoginModel.current1.getCity());
			stateLbl.setText(LoginModel.current1.getState());
			zipLbl.setText(LoginModel.current1.getZip());
			phoneLbl.setText(LoginModel.current1.getPhone());
		}
	

	}

}
