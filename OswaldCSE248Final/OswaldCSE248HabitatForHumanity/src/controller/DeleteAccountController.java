package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import model.DeleteAccountModel;
import model.LoginModel;

public class DeleteAccountController implements Initializable {
	Main main = new Main();
	DeleteAccountModel dam = new DeleteAccountModel();

	@FXML
	private PasswordField passField;
	@FXML
	private PasswordField confirmPassField;
	@FXML
	private Label messageLbl;
	@FXML
	private Label passLbl;
	@FXML
	private Label confirmLbl;
	@FXML
	private Button confirmDeletionBtn;

	@FXML
	private Button shopBtn;
	@FXML
	private Button cartBtn;
	@FXML
	private Button logoutBtn;
	@FXML
	private Button ordersBtn;
	@FXML
	private Button profileBtn;
	@FXML
	private Button helpBtn;
	@FXML
	private Button okBtn;

	public void firstDeletion(ActionEvent event) {
		confirmDeletionBtn.setVisible(true);
		confirmPassField.setVisible(true);
		passField.setVisible(true);
		confirmLbl.setVisible(true);
		passLbl.setVisible(true);

		shopBtn.setVisible(false);
		cartBtn.setVisible(false);
		logoutBtn.setVisible(false);
		ordersBtn.setVisible(false);
		profileBtn.setVisible(false);
		helpBtn.setVisible(false);

	}

	public void setSceneAfterDeletion() {
		passField.clear();
		confirmPassField.clear();
		messageLbl.setText("Your account has been officially deleted.");
		confirmDeletionBtn.setVisible(false);
		okBtn.setVisible(true);

	}

	public void delete(ActionEvent event) throws SQLException {
		if (dam.checkPassword(LoginModel.current1.getUsername(), passField.getText(),
				confirmPassField.getText()) == true) {
			dam.deleteAccount(LoginModel.current1.getUsername());
			setSceneAfterDeletion();
		} else {
			messageLbl.setText("One or both of your passwords are incorrect. Please try again.");
		}
	}
	
	public void ok(ActionEvent event) throws IOException{
		main.changeScene("LoginView.fxml", messageLbl);
	}

	public void back(ActionEvent event) throws IOException {
		main.changeScene("MyProfileView.fxml", messageLbl);
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
		confirmDeletionBtn.setVisible(false);
		confirmPassField.setVisible(false);
		passField.setVisible(false);
		confirmLbl.setVisible(false);
		passLbl.setVisible(false);
		okBtn.setVisible(false);
	}
}
