package controller;

import java.io.IOException;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import model.HelpModel;

public class HelpController {
	Main main = new Main();
	HelpModel helpModel = new HelpModel();

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
	
	public void submitHelp(ActionEvent event) throws IOException{
		helpModel.writeToTextFile(helpTextArea.getText());
		messageLbl.setText("Your request has been sent to an administrator.");
		helpTextArea.clear();
	}

}
