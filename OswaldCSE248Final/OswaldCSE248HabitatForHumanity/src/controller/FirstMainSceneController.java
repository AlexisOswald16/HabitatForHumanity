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
import model.FirstMainSceneModel;

public class FirstMainSceneController implements Initializable {
	private FirstMainSceneModel firstMainSceneModel = new FirstMainSceneModel();
	Main main = new Main();

	@FXML
	private Label welcomeLbl;
	@FXML
	private Label messageLbl;
	
	public void shop(ActionEvent event) throws IOException {
		 main.changeScene("ShopView.fxml", welcomeLbl);
	}

	public void myProfile(ActionEvent event) throws IOException, SQLException {
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	

	


}
