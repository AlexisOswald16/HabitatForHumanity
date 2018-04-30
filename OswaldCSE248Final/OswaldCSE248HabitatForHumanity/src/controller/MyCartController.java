package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Item;
import model.MyCartModel;

public class MyCartController implements Initializable {
	Main main = new Main();
	MyCartModel mcm = new MyCartModel();

	@FXML
	private Label messageLbl;
	@FXML
	private TableView<String> cartTable;
	@FXML
	private TableColumn<String,String> itemNumber;
	@FXML
	private TableColumn<String,String> itemName;
	@FXML
	private TableColumn<String, String> quantity;
	@FXML
	private TableColumn<String, String> price;

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
		ObservableList<Item> items = FXCollections.observableList(mcm.getItemList());
		itemNumber.setCellValueFactory(c -> new SimpleStringProperty(mcm.getItemString()));

	}
}
