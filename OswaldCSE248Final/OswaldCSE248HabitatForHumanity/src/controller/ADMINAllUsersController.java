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
import javafx.scene.control.TextField;
import model.ADMINAllUsersModel;

public class ADMINAllUsersController implements Initializable {
	Main main = new Main();
	ADMINAllUsersModel aaum = new ADMINAllUsersModel();

	@FXML
	private Label messageLbl;
	@FXML
	private ListView<String> usersList;
	@FXML
	private Label numberOfUsers;
	@FXML
	private TextField usernameField;
	
	public void search(ActionEvent event) throws SQLException{
		usersList.setItems(aaum.getUser(usernameField.getText()));
		usernameField.setText("");
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			usersList.setItems(aaum.getAllUsersFromDatabase());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		numberOfUsers.setText(Integer.toString(aaum.getNumberOfUsers()));
	}

}
