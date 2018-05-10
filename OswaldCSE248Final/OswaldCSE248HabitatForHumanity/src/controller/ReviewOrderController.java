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
import model.CheckOutModel;
import model.ReviewOrderModel;

public class ReviewOrderController implements Initializable {
	Main main = new Main();
	ReviewOrderModel rom = new ReviewOrderModel();

	@FXML
	Label messageLbl;

	@FXML
	Label shippingAddressArea;
	@FXML
	Label billingAddressArea;
	@FXML
	Label cardInfoArea;

	public void submitOrder(ActionEvent event) throws IOException, SQLException {
		rom.addOrderToDatabase();
		main.changeScene("OrderCompletedView.fxml",messageLbl);
	}

	public void cancel(ActionEvent event) throws IOException {
		main.changeScene("CheckOutView.fxml", messageLbl);
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		shippingAddressArea.setText(CheckOutModel.currentOrder.getShippingAddress().toString());
		billingAddressArea.setText(CheckOutModel.currentOrder.getBillingAddress().toString());
		cardInfoArea.setText(CheckOutModel.currentOrder.getCard().toString());
	}
}
