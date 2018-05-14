package controller;

import java.io.IOException;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ADMINFirstMainSceneController {
	Main main = new Main();

	@FXML
	private Label messageLbl;

	public void inventory(ActionEvent event) throws IOException {
		main.changeScene("ADMINInventoryView.fxml", messageLbl);
	}

	public void allUsers(ActionEvent event) throws IOException {
		main.changeScene("ADMINAllUsersView.fxml", messageLbl);
	}

	public void createAccount(ActionEvent event) throws IOException {
		main.changeScene("ADMINCreateAccountView.fxml", messageLbl);
	}

	public void logout(ActionEvent event) throws IOException {
		main.changeScene("ADMINLogoutView.fxml", messageLbl);
	}

	public void allOrders(ActionEvent event) throws IOException {
		main.changeScene("ADMINAllOrdersView.fxml", messageLbl);
	}
	
	public void help(ActionEvent event) throws IOException {
		main.changeScene("ADMINHelpView.fxml", messageLbl);
	}
}
