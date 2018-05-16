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
import javafx.scene.control.ListView;
import model.MyOrdersModel;

public class MyOrdersController implements Initializable {
	Main main = new Main();
	MyOrdersModel mom = new MyOrdersModel();

	@FXML
	private ListView<String> ordersListView;

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
		try {
			ordersListView.setItems(mom.getAllOrdersFromDatabase());

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
