package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.FirstMainSceneModel;

public class FirstMainSceneController {
	private FirstMainSceneModel firstMainSceneModel = new FirstMainSceneModel();
	private FirstLogoutViewController flwc = new FirstLogoutViewController();
	Main main = new Main();

	@FXML
	private Label welcomeLbl;

	public void shop(ActionEvent event) {
		// main.changeScene("ShopView.fxml", welcomeLbl);

	}

	public void myProfile(ActionEvent event) {
		// main.changeScene("MyProfileView.fxml", welcomeLbl);
	}

	public void myCart(ActionEvent event) {
		// main.changeScene("MyCartView.fxml", welcomeLbl);

	}

	public void orders(ActionEvent event) {
		// main.changeScene("MyOrdersView.fxml", welcomeLbl);
	}

	public void help(ActionEvent event) throws IOException {
		main.changeScene("HelpView.fxml", welcomeLbl);
	}

	public void logout(ActionEvent event) {
		main.setAlertStage("FirstLogoutView.fxml");


		// popup "are you sure you want to logout"
		// if yes, main.changeScene("LoginView.fxml", welcomeLbl); and remove as
		// current user. (do in model)
		// if no, stay.

	}

}
