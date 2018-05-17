package controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.ADMINAllOrdersModel;

public class ADMINAllOrdersController implements Initializable {
	Main main = new Main();
	ADMINAllOrdersModel aaom = new ADMINAllOrdersModel();

	@FXML
	private Label messageLbl;
	@FXML
	private ListView<String> ordersView;
	@FXML
	private TextField orderNumField;
	@FXML
	private Label numberOfItemsLbl;
	@FXML
	private Label numberOfOrdersLbl;
	@FXML
	private Label amountOfMoneyLbl;

	public void searchOrder(ActionEvent event) {

	}

	public void inventory(ActionEvent event) throws IOException {
		main.changeScene("ADMINInventoryView.fxml", messageLbl);
	}

	public void allUsers(ActionEvent event) throws IOException {
		main.changeScene("ADMINAllUsersView.fxml", messageLbl);
	}

	public void createAccount(ActionEvent event) throws IOException {
		main.changeScene("ADMINCreateAccountView.fxml", messageLbl);
	}

	public void logout(ActionEvent event) throws IOException {
		main.changeScene("ADMINLogoutView.fxml", messageLbl);
	}

	public void allOrders(ActionEvent event) throws IOException {
		main.changeScene("ADMINAllOrdersView.fxml", messageLbl);
	}

	public void help(ActionEvent event) throws IOException {
		main.changeScene("ADMINHelpView.fxml", messageLbl);
	}

	private static double round(double value, int places) {
		if (places < 0)
			throw new IllegalArgumentException();

		BigDecimal bd = new BigDecimal(Double.toString(value));
		bd = bd.setScale(places, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			ordersView.setItems(aaom.getAllOrdersFromDatabase());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		numberOfItemsLbl.setText(Integer.toString(aaom.getNumberOfItems()));
		numberOfOrdersLbl.setText(Integer.toString(aaom.getNumberOfOrders()));
		double o = aaom.getTotalPrice();
		double p = round(o, 2);
		amountOfMoneyLbl.setText("$ " + Double.toString(p));

	}
}
