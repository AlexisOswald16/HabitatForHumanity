package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.ShopInventoryModel;

public class ShopInventoryController implements Initializable {
	ShopInventoryModel sim = new ShopInventoryModel();
	Main main = new Main();
	
	@FXML
	private Label welcomeLbl;
	@FXML
	private Button backBtn;
	@FXML
	private Button nextBtn;
	
	@FXML
	private Label titleLbl1;
	@FXML
	private Label titleLbl2;
	@FXML
	private Label titleLbl3;
	@FXML
	private Label titleLbl4;
	@FXML
	private Label titleLbl5;

	@FXML
	private Label quantityLbl1;
	@FXML
	private Label quantityLbl2;
	@FXML
	private Label quantityLbl3;
	@FXML
	private Label quantityLbl4;
	@FXML
	private Label quantityLbl5;
	
	@FXML
	private Label priceLbl1;
	@FXML
	private Label priceLbl2;
	@FXML
	private Label priceLbl3;
	@FXML
	private Label priceLbl4;
	@FXML
	private Label priceLbl5;
	
	@FXML 
	private Label itemNumLbl1;
	@FXML 
	private Label itemNumLbl2;
	@FXML 
	private Label itemNumLbl3;
	@FXML 
	private Label itemNumLbl4;
	@FXML 
	private Label itemNumLbl5;
	
	
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
		sim.getInventoryFromDatabase();
		backBtn.setVisible(false);
		// if number of db entries > 5, set backBtn.setVisible(true); else false
	}

}
