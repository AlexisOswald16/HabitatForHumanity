package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.Item;
import model.MyCartModel;

public class MyCartController implements Initializable {
	Main main = new Main();
	MyCartModel mcm;
	public static double priceTotal;

	@FXML
	private Label messageLbl;
	@FXML
	private TextArea cartArea;
	@FXML
	private TextField itemNum;

	@FXML
	private TextArea itemNumber;
	@FXML
	private TextArea itemName;
	@FXML
	private TextArea itemQuantity;
	@FXML
	private TextArea itemPrice;
	@FXML
	private Label totalPrice;

	public void remove(ActionEvent event) throws SQLException {
		if (itemNum.getText() != null) {
			if (mcm.deleteFromCart(itemNum.getText()) == true) {
				messageLbl.setText("The item has been deleted");
				setTextArea();
				return;
			} else {
				messageLbl.setText("The item number is invalid. Please try again.");
			}
		} else {
			messageLbl.setText("The item number is invalid. Please try again.");
		}
		itemNum.setText(" ");

	}

	public void checkOut(ActionEvent event) throws IOException {
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

	public void setTextArea() throws SQLException {
		mcm.getCartFromDatabase();
		if (mcm.getItemNums() != null) {

			int numberOfItems = mcm.getItemNums().size();
			ArrayList<String> itemNumbers = mcm.getItemNums();
			ArrayList<String> quantities = mcm.getQuantities();
			ArrayList<Item> allItems = mcm.getAllItems();
			String name = "";
			String price = "";
			String newLine = System.getProperty("line.separator");
			String tab = "		";
			String output1 = tab;
			String output2 = "";
			String output3 = tab;
			String output4 = tab;
			double[] priceArray = new double[itemNumbers.size()];
			if (numberOfItems != 0) {
				for (int i = 0; i < numberOfItems; i++) {
					String itemNum = itemNumbers.get(i);
					String quant = quantities.get(i);
					for (Item item : allItems) {
						if (item.getIdNumber().equals(itemNum)) {
							name = item.getItemName();
							priceArray[i] = item.getPrice();
							price = Double.toString(item.getPrice());

						}
					}
					output1 += newLine + tab + itemNum;
					output2 += newLine + name;
					output3 += newLine + tab + quant;
					output4 += newLine + tab + "$ " + price;

				}
				itemNumber.setText(output1);
				itemName.setText(output2);
				itemQuantity.setText(output3);
				itemPrice.setText(output4);
				double total = 0;
				double tax = 0;
				for (int i = 0; i < priceArray.length; i++) {
					total = total + priceArray[i];
					tax = total * .08;
					total = total + tax;
					total = Math.round(total * 100.0) / 100.0;
				}
				priceTotal = total;
				totalPrice.setText("$ " + Double.toString(total));
			}
		} else {
			totalPrice.setText("$ 0.00");
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		mcm = new MyCartModel();
		try {
			mcm.getInventoryFromDatabase();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			setTextArea();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
