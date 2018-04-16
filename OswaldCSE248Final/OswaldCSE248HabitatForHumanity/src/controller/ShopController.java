package controller;

import java.io.IOException;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ShopController {
	Main main = new Main();

	@FXML
	private Label messageLbl;

	public void viewAllInventory(ActionEvent event) throws IOException {
		// opens the view that has all inventory
	}

	public void furniture(ActionEvent event) throws IOException {
		// opens the view that has all furniture
	}

	public void appliances(ActionEvent event) throws IOException {
		// opens the view that has all appliances
	}

	public void homeAccessories(ActionEvent event) throws IOException {
		// opens the view that has home accessories
	}

	public void buildingMaterials(ActionEvent event) throws IOException {
		// opens the view that has building materials
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
}
