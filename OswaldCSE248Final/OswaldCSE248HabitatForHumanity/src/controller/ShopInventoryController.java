package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Stack;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.ImageViewBuilder;
import model.Item;
import model.ShopInventoryModel;

public class ShopInventoryController implements Initializable {
	ShopInventoryModel sim = new ShopInventoryModel();
	Main main = new Main();
	Stack<Item> allItemsStack;

	@FXML
	private ImageView img1;
	@FXML
	private ImageView img2;
	@FXML
	private ImageView img3;
	@FXML
	private ImageView img4;
	@FXML
	private ImageView img5;

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

	public void fillFields() {
		Item currentItem;
		currentItem = allItemsStack.pop();
		titleLbl1.setText(currentItem.getItemName());
		quantityLbl1.setText(Integer.toString(currentItem.getQuantity()));
		String price1 = Double.toString(currentItem.getPrice());
		priceLbl1.setText("$" + price1);
		itemNumLbl1.setText(currentItem.getIdNumber());
		Image image1 = new Image(
				getClass().getResource("/InventoryImages/" + currentItem.getImageURL()).toExternalForm());
		img1.setImage(image1);

		currentItem = allItemsStack.pop();
		titleLbl2.setText(currentItem.getItemName());
		quantityLbl2.setText(Integer.toString(currentItem.getQuantity()));
		String price2 = Double.toString(currentItem.getPrice());
		priceLbl2.setText("$" + price2);
		itemNumLbl2.setText(currentItem.getIdNumber());
		Image image2 = new Image(
				getClass().getResource("/InventoryImages/" + currentItem.getImageURL()).toExternalForm());
		img2.setImage(image2);

		currentItem = allItemsStack.pop();
		titleLbl3.setText(currentItem.getItemName());
		quantityLbl3.setText(Integer.toString(currentItem.getQuantity()));
		String price3 = Double.toString(currentItem.getPrice());
		priceLbl3.setText("$" + price3);
		itemNumLbl3.setText(currentItem.getIdNumber());
		Image image3 = new Image(
				getClass().getResource("/InventoryImages/" + currentItem.getImageURL()).toExternalForm());
		img3.setImage(image3);

		currentItem = allItemsStack.pop();
		titleLbl4.setText(currentItem.getItemName());
		quantityLbl4.setText(Integer.toString(currentItem.getQuantity()));
		String price4 = Double.toString(currentItem.getPrice());
		priceLbl4.setText("$" + price4);
		itemNumLbl4.setText(currentItem.getIdNumber());
		Image image4 = new Image(
				getClass().getResource("/InventoryImages/" + currentItem.getImageURL()).toExternalForm());
		img4.setImage(image4);

		currentItem = allItemsStack.pop();
		titleLbl5.setText(currentItem.getItemName());
		quantityLbl5.setText(Integer.toString(currentItem.getQuantity()));
		String price5 = Double.toString(currentItem.getPrice());
		priceLbl5.setText("$" + price5);
		itemNumLbl5.setText(currentItem.getIdNumber());
		Image image5 = new Image(
				getClass().getResource("/InventoryImages/" + currentItem.getImageURL()).toExternalForm());
		img5.setImage(image5);

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			sim.getInventoryFromDatabase();
			if (sim.getNumberOfItems() < 5) {
				nextBtn.setVisible(false);
			} else {
				nextBtn.setVisible(true);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		allItemsStack = sim.getAllItems();
		backBtn.setVisible(false);
		fillFields();
	}

}
