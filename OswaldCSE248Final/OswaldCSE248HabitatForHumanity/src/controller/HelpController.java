package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class HelpController {
	Main main = new Main();

	@FXML
	private Label messageLbl;
	@FXML
	private TextArea helpTextArea;
	@FXML
	private Label welcomeLbl;

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
	
	public void submitHelp(ActionEvent event){
		messageLbl.setText("Your request has been sent to an administrator.");
		//save the text to a text file and let that file be accessible to an administrator
		helpTextArea.clear();
	}

}
