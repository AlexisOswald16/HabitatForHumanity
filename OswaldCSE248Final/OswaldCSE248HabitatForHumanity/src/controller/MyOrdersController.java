package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class MyOrdersController implements Initializable {
	Main main = new Main();

	@FXML
	private TextArea orderNumber;
	@FXML
	private TextArea paymentMethod;
	@FXML
	private TextArea totalPrice;
	@FXML
	private TextArea dateOrdered;
	@FXML
	private TextArea dateDelivered;
	@FXML
	private TextArea eligibleForReturn;

	@FXML
	private Label messageLbl;

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
		// TODO Auto-generated method stub

	}
}
