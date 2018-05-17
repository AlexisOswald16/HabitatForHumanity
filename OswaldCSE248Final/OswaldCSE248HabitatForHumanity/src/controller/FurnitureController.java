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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.FurnitureModel;
import model.Item;

public class FurnitureController implements Initializable {
	FurnitureModel fm = new FurnitureModel();
	Main main = new Main();
	ArrayList<Item> allItemsList;
	Item currentItem;
	int pageCount = 0;
	int item = 0;

	@FXML
	private TextField itemNumber;
	@FXML
	private TextField quantity;

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

	public void next(ActionEvent event) throws IOException {
		fillFields();
		backBtn.setVisible(true);
	}

	public void addToCart(ActionEvent event) throws SQLException {
		if (fm.checkIfItemNumberExists(itemNumber.getText()) == true) {
			if (fm.checkIfQuantityIsValid(itemNumber.getText(), quantity.getText()) == true) {
				fm.addToCart(itemNumber.getText(), quantity.getText());
				welcomeLbl.setText("The item has been added to your cart.");

			} else {
				welcomeLbl.setText("The quantity you entered is not available. Please try again.");
			}
		} else {
			welcomeLbl.setText("The item number is invalid. Please try again.");
		}
		itemNumber.setText("");
		quantity.setText("");

	}

	public void clearFields(Label title, Label quantity, Label price, Label itemNumber, ImageView image) {
		title.setText("");
		quantity.setText("");
		price.setText("");
		itemNumber.setText("");
		Image image1 = new Image("/images/transparent.png");
		image.setImage(image1);
	}

	public void setItemFields(Label title, Label quantity, Label price, Label itemNumber, ImageView image) {
		title.setText(currentItem.getItemName());
		quantity.setText(Integer.toString(currentItem.getQuantity()));
		String price1 = Double.toString(currentItem.getPrice());
		price.setText("$" + price1);
		itemNumber.setText(currentItem.getIdNumber());
		if (currentItem.getImageURL() != null) {
			Image image1 = new Image(
					getClass().getResource("/InventoryImages/" + currentItem.getImageURL()).toExternalForm());
			image.setImage(image1);

		} else {
			Image image1 = new Image("/images/transparent.png");
			image.setImage(image1);
		}
	}

	public void back(ActionEvent event) {
		int lastItemOnPage = 0;
		pageCount--;
		if (pageCount == 0) {
			lastItemOnPage = 5;
			setBackFields(lastItemOnPage);
		} else {
			lastItemOnPage = pageCount * 5;
			setBackFields(lastItemOnPage);
		}

	}

	public void setBackFields(int lastItemOnPage) {
		item = item - 5;
		currentItem = allItemsList.get(--lastItemOnPage);
		setItemFields(titleLbl5, quantityLbl5, priceLbl5, itemNumLbl5, img5);
		currentItem = allItemsList.get(--lastItemOnPage);
		setItemFields(titleLbl4, quantityLbl4, priceLbl4, itemNumLbl4, img4);
		currentItem = allItemsList.get(--lastItemOnPage);
		setItemFields(titleLbl3, quantityLbl3, priceLbl3, itemNumLbl3, img3);
		currentItem = allItemsList.get(--lastItemOnPage);
		setItemFields(titleLbl2, quantityLbl2, priceLbl2, itemNumLbl2, img2);
		currentItem = allItemsList.get(--lastItemOnPage);
		setItemFields(titleLbl1, quantityLbl1, priceLbl1, itemNumLbl1, img1);
		if (pageCount == 0) {
			backBtn.setVisible(false);
		}

	}

	public void fillFields() {
		int numberOfPages = fm.getNumberOfItems() / 5;
		if (pageCount < numberOfPages) {
			currentItem = allItemsList.get(item++);
			setItemFields(titleLbl1, quantityLbl1, priceLbl1, itemNumLbl1, img1);
			currentItem = allItemsList.get(item++);
			setItemFields(titleLbl2, quantityLbl2, priceLbl2, itemNumLbl2, img2);
			currentItem = allItemsList.get(item++);
			setItemFields(titleLbl3, quantityLbl3, priceLbl3, itemNumLbl3, img3);
			currentItem = allItemsList.get(item++);
			setItemFields(titleLbl4, quantityLbl4, priceLbl4, itemNumLbl4, img4);
			currentItem = allItemsList.get(item++);
			setItemFields(titleLbl5, quantityLbl5, priceLbl5, itemNumLbl5, img5);
			pageCount++;
			return;
		} else if (numberOfPages == pageCount) {
			int remaining = (allItemsList.size() - item);
			pageCount++;
			switch (remaining) {
			case 1:
				currentItem = allItemsList.get(item);
				setItemFields(titleLbl1, quantityLbl1, priceLbl1, itemNumLbl1, img1);
				clearFields(titleLbl2, quantityLbl2, priceLbl2, itemNumLbl2, img2);
				clearFields(titleLbl3, quantityLbl3, priceLbl3, itemNumLbl3, img3);
				clearFields(titleLbl4, quantityLbl4, priceLbl4, itemNumLbl4, img4);
				clearFields(titleLbl5, quantityLbl5, priceLbl5, itemNumLbl5, img5);

				break;
			case 2:
				currentItem = allItemsList.get(item++);
				setItemFields(titleLbl1, quantityLbl1, priceLbl1, itemNumLbl1, img1);
				currentItem = allItemsList.get(item);
				setItemFields(titleLbl2, quantityLbl2, priceLbl2, itemNumLbl2, img2);
				clearFields(titleLbl3, quantityLbl3, priceLbl3, itemNumLbl3, img3);
				clearFields(titleLbl4, quantityLbl4, priceLbl4, itemNumLbl4, img4);
				clearFields(titleLbl5, quantityLbl5, priceLbl5, itemNumLbl5, img5);

				break;
			case 3:
				currentItem = allItemsList.get(item++);
				setItemFields(titleLbl1, quantityLbl1, priceLbl1, itemNumLbl1, img1);
				currentItem = allItemsList.get(item++);
				setItemFields(titleLbl2, quantityLbl2, priceLbl2, itemNumLbl2, img2);
				currentItem = allItemsList.get(item);
				setItemFields(titleLbl3, quantityLbl3, priceLbl3, itemNumLbl3, img3);
				clearFields(titleLbl4, quantityLbl4, priceLbl4, itemNumLbl4, img4);
				clearFields(titleLbl5, quantityLbl5, priceLbl5, itemNumLbl5, img5);
				break;

			case 4:
				currentItem = allItemsList.get(item++);
				setItemFields(titleLbl1, quantityLbl1, priceLbl1, itemNumLbl1, img1);
				currentItem = allItemsList.get(item++);
				setItemFields(titleLbl2, quantityLbl2, priceLbl2, itemNumLbl2, img2);
				currentItem = allItemsList.get(item++);
				setItemFields(titleLbl3, quantityLbl3, priceLbl3, itemNumLbl3, img3);
				currentItem = allItemsList.get(item);
				setItemFields(titleLbl4, quantityLbl4, priceLbl4, itemNumLbl4, img4);
				clearFields(titleLbl5, quantityLbl5, priceLbl5, itemNumLbl5, img5);
				break;

			case 5:
				currentItem = allItemsList.get(item++);
				setItemFields(titleLbl1, quantityLbl1, priceLbl1, itemNumLbl1, img1);
				currentItem = allItemsList.get(item++);
				setItemFields(titleLbl2, quantityLbl2, priceLbl2, itemNumLbl2, img2);
				currentItem = allItemsList.get(item++);
				setItemFields(titleLbl3, quantityLbl3, priceLbl3, itemNumLbl3, img3);
				currentItem = allItemsList.get(item++);
				setItemFields(titleLbl4, quantityLbl4, priceLbl4, itemNumLbl4, img4);
				currentItem = allItemsList.get(item);
				setItemFields(titleLbl5, quantityLbl5, priceLbl5, itemNumLbl5, img5);
				break;

			}

		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			fm.getInventoryFromDatabase();
			if (fm.getNumberOfItems() < 5) {
				nextBtn.setVisible(false);
			} else {
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for(int i = 0; i < fm.getItemList().size(); i++){
			String[] cat = fm.getItemList().get(i).getCategories();
			for(int j = 0;j < cat.length; j ++){
				if(cat[j].equals("Furniture")){
					allItemsList.add(fm.getItemList().get(i));
				}
			}
		}
		backBtn.setVisible(false);
		fillFields();		
		
	}
}