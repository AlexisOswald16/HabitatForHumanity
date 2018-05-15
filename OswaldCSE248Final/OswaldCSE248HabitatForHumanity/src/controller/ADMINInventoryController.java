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
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.ADMINInventoryModel;
import model.Item;

public class ADMINInventoryController implements Initializable {
	Main main = new Main();
	ADMINInventoryModel aim = new ADMINInventoryModel();
	ArrayList<Item> allItemsList;
	Item currentItem;
	int pageCount = 0;
	int item = 0;

	@FXML
	private Label messageLbl;

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
	private Button backBtn;
	@FXML
	private Button nextBtn;

	@FXML
	private TextField titleLbl1;
	@FXML
	private TextField titleLbl2;
	@FXML
	private TextField titleLbl3;
	@FXML
	private TextField titleLbl4;
	@FXML
	private TextField titleLbl5;

	@FXML
	private TextField quantityLbl1;
	@FXML
	private TextField quantityLbl2;
	@FXML
	private TextField quantityLbl3;
	@FXML
	private TextField quantityLbl4;
	@FXML
	private TextField quantityLbl5;

	@FXML
	private TextField priceLbl1;
	@FXML
	private TextField priceLbl2;
	@FXML
	private TextField priceLbl3;
	@FXML
	private TextField priceLbl4;
	@FXML
	private TextField priceLbl5;

	@FXML
	private TextField itemNumLbl1;
	@FXML
	private TextField itemNumLbl2;
	@FXML
	private TextField itemNumLbl3;
	@FXML
	private TextField itemNumLbl4;
	@FXML
	private TextField itemNumLbl5;

	@FXML
	private CheckBox furniture1;
	@FXML
	private CheckBox furniture2;
	@FXML
	private CheckBox furniture3;
	@FXML
	private CheckBox furniture4;
	@FXML
	private CheckBox furniture5;

	@FXML
	private CheckBox appliances1;
	@FXML
	private CheckBox appliances2;
	@FXML
	private CheckBox appliances3;
	@FXML
	private CheckBox appliances4;
	@FXML
	private CheckBox appliances5;

	@FXML
	private CheckBox building1;
	@FXML
	private CheckBox building2;
	@FXML
	private CheckBox building3;
	@FXML
	private CheckBox building4;
	@FXML
	private CheckBox building5;

	@FXML
	private CheckBox homeAccessories1;
	@FXML
	private CheckBox homeAccessories2;
	@FXML
	private CheckBox homeAccessories3;
	@FXML
	private CheckBox homeAccessories4;
	@FXML
	private CheckBox homeAccessories5;

	@FXML
	private CheckBox homeAccessoriesNew;
	@FXML
	private CheckBox buildingNew;
	@FXML
	private CheckBox appliancesNew;
	@FXML
	private CheckBox furnitureNew;
	@FXML
	private TextField titleNew;
	@FXML
	private TextField itemNumberNew;
	@FXML
	private TextField quantityNew;
	@FXML
	private TextField priceNew;

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

	public void next(ActionEvent event) throws IOException {
		fillFields();
		backBtn.setVisible(true);
	}

	public void addItemToDatabase(ActionEvent event) throws SQLException {
		ImageView image = new ImageView();
		String[] categories = getCheckedBoxes(homeAccessoriesNew, buildingNew, appliancesNew, furnitureNew);
		Item item = createNewItem(titleNew, quantityNew, priceNew, itemNumberNew, image, categories);
		System.out.println(item.toString());

		aim.addNewItemInDB(item);
	}

	public String[] getCheckedBoxes(CheckBox cb1, CheckBox cb2, CheckBox cb3, CheckBox cb4) {
		String cat = "";
		if (cb1.isSelected() == true) {
			cat = cat + cb1.getText();
		} else if (cb2.isSelected() == true) {
			cat = cat + "," + cb2.getText();
		} else if (cb3.isSelected() == true) {
			cat = cat + "," + cb3.getText();
		} else if (cb4.isSelected() == true) {
			cat = cat + "," + cb4.getText();
		}
		String[] categories = cat.split(",");
		return categories;
	}

	public void saveChanges(ActionEvent event) throws SQLException {
		String[] categories1 = getCheckedBoxes(homeAccessories1, building1, appliances1, furniture1);
		String[] categories2 = getCheckedBoxes(homeAccessories2, building2, appliances2, furniture2);
		String[] categories3 = getCheckedBoxes(homeAccessories3, building3, appliances3, furniture3);
		String[] categories4 = getCheckedBoxes(homeAccessories4, building4, appliances4, furniture4);
		String[] categories5 = getCheckedBoxes(homeAccessories5, building5, appliances5, furniture5);

		Item item1 = createNewItem(titleLbl1, quantityLbl1, priceLbl1, itemNumLbl1, img1, categories1);
		Item item2 = createNewItem(titleLbl2, quantityLbl2, priceLbl2, itemNumLbl2, img2, categories2);
		Item item3 = createNewItem(titleLbl3, quantityLbl3, priceLbl3, itemNumLbl3, img3, categories3);
		Item item4 = createNewItem(titleLbl4, quantityLbl4, priceLbl4, itemNumLbl4, img4, categories4);
		Item item5 = createNewItem(titleLbl5, quantityLbl5, priceLbl5, itemNumLbl5, img5, categories5);

		aim.updateItemInDB(item1);
		aim.updateItemInDB(item2);
		aim.updateItemInDB(item3);
		aim.updateItemInDB(item4);
		aim.updateItemInDB(item5);

		messageLbl.setText("Inventory values have been updated.");

	}

	public Item createNewItem(TextField title, TextField quantity, TextField price, TextField itemNumber,
			ImageView image, String[] categories) {
		String p = price.getText();
		String pr = p.substring(1);

		double itemPrice = Double.parseDouble(pr);
		int quant = Integer.parseInt(quantity.getText());
		System.out.println(
				itemNumber.getText() + "," + title.getText() + "," + itemPrice + "," + quant + "," + categories.toString());
		Item item = new Item(itemNumber.getText(), title.getText(), itemPrice, quant, categories, "");
		System.out.println(item.toString());
		return item;
	}

	public void clearFields(TextField title, TextField quantity, TextField price, TextField itemNumber,
			ImageView image) {
		title.setText("");
		quantity.setText("");
		price.setText("");
		itemNumber.setText("");
		Image image1 = new Image("/images/transparent.png");
		image.setImage(image1);
	}

	public void setItemFields(TextField title, TextField quantity, TextField price, TextField itemNumber,
			ImageView image) {
		title.setText(currentItem.getItemName());
		quantity.setText(Integer.toString(currentItem.getQuantity()));
		String price1 = Double.toString(currentItem.getPrice());
		price.setText("$" + price1);
		itemNumber.setText(currentItem.getIdNumber());
		Image image1 = new Image(
				getClass().getResource("/InventoryImages/" + currentItem.getImageURL()).toExternalForm());
		image.setImage(image1);
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
		int numberOfPages = aim.getNumberOfItems() / 5;
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
			aim.getInventoryFromDatabase();
			if (aim.getNumberOfItems() < 5) {
				nextBtn.setVisible(false);
			} else {
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		allItemsList = aim.getItemList();
		fillFields();
	}

}
