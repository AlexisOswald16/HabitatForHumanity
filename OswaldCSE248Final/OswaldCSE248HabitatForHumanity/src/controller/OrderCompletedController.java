package controller;

import java.io.IOException;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class OrderCompletedController {
	Main main = new Main();

	@FXML
	Label welcomeLbl;
	@FXML
	Label messageLbl;

	public void shop(ActionEvent event) throws IOException {
		main.changeScene("ShopView.fxml", welcomeLbl);

	}

	public void myProfile(ActionEvent event) throws IOException {
		main.changeScene("MyProfileView.fxml", welcomeLbl);
	}

	public void myCart(ActionEvent event) throws IOException {
		main.changeScene("MyCartView.fxml", welcomeLbl);

	}

	public void orders(ActionEvent event) throws IOException {
		main.changeScene("MyOrdersView.fxml", welcomeLbl);
	}

	public void help(ActionEvent event) throws IOException {
		main.changeScene("HelpView.fxml", messageLbl);
	}

	public void logout(ActionEvent event) throws IOException {
		main.changeScene("LogoutView.fxml", messageLbl);

	}
}
