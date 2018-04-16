package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import model.FirstMainSceneModel;

public class FirstMainSceneController {
	private FirstMainSceneModel firstMainSceneModel = new FirstMainSceneModel();
	Main main = new Main();

	@FXML
	private Label welcomeLbl;
	@FXML
	private Label messageLbl;
	
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
		main.changeScene("HelpView.fxml", welcomeLbl);
	}

	public void logout(ActionEvent event) throws IOException {
		main.changeScene("LogoutView.fxml", welcomeLbl);
	}
	


}
