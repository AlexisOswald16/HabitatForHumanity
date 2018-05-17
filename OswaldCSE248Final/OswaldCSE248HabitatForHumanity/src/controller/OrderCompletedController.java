package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import model.CheckOutModel;
import model.Order;
import model.OrderCompletedModel;

public class OrderCompletedController implements Initializable {
	Main main = new Main();
	OrderCompletedModel ocm = new OrderCompletedModel();

	@FXML
	Label welcomeLbl;
	@FXML
	Label messageLbl;

	@FXML
	Label orderNumLbl;
	@FXML
	Label shippingAddressLbl;
	@FXML
	Label paymentInfoLbl;
	@FXML
	Label dateLbl;
	@FXML
	Label deliveryDateLbl;

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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		int orderNumber = 0;
		Order currentOrder = null;

		shippingAddressLbl.setText(CheckOutModel.currentOrder.getShippingAddress().toString());
		try {
			orderNumber = ocm.getOrderNumberFromDatabase();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			currentOrder = ocm.getOrderFromDatabase(orderNumber);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String in = "";
		String[] itemAndQuant = currentOrder.getItems().split("");
		ArrayList<String> itemNumber = new ArrayList<>();
		ArrayList<String> quantities = new ArrayList<>();
		for (int i = 0; i < itemAndQuant.length; i++) {
			int currentInt = i;
			if (currentInt % 2 == 0) {
				quantities.add(itemAndQuant[i]);
			} else {
				itemNumber.add(itemAndQuant[i]);
			}
			for (int j = 0; j < itemNumber.size(); j++) {
				in = itemNumber.get(j);
			}
		}
		String[] itemNumbers = in.split(",");
		
		SimpleDateFormat sdfa = new SimpleDateFormat("MM/dd/yyyy");
		Calendar a = Calendar.getInstance();
		a.setTime(new Date());

		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		
		c.add(Calendar.DATE, 7);
		String shippingDate = sdf.format(c.getTime());
		String d = sdfa.format(a.getTime());

		deliveryDateLbl.setText(shippingDate);
		paymentInfoLbl.setText(ocm.setCardOutput(currentOrder.getCard()));
		dateLbl.setText(d);
		shippingAddressLbl.setText(currentOrder.getShippingAddress().toString());
		orderNumLbl.setText(Integer.toString(orderNumber));
	}
}
