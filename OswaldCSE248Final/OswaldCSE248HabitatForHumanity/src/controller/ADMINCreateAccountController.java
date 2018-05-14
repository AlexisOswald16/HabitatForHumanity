package controller;

import java.io.IOException;
import java.sql.SQLException;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.ADMINCreateAccountModel;

public class ADMINCreateAccountController {
	Main main = new Main();
	ADMINCreateAccountModel acam = new ADMINCreateAccountModel();

	@FXML
	private Label messageLbl;
	@FXML
	private Button clearBtn;
	@FXML
	private Button createAccountBtn;
	@FXML
	private Button backBtn;
	@FXML
	private TextField userField;
	@FXML
	private PasswordField passField;
	@FXML
	private TextField fNameField;
	@FXML
	private TextField lNameField;
	@FXML
	private PasswordField rePassField;
	@FXML
	private TextField emailField;
	@FXML
	private CheckBox isAdminBox;

	public void createNewAccount(ActionEvent event) throws SQLException {
		boolean admin = isAdminBox.isSelected();
		if (checkFieldsForCompletion() == false) {
			messageLbl.setText("All fields must be completed.");
		} else if (acam.checkPassword(passField.getText(), rePassField.getText()) == false) {
			messageLbl.setText("Both passwords must match.");
		} else if (acam.checkPasswordCriteria(passField.getText()) == false) {
			messageLbl.setText("Your password must be at least 5 characters long and contain one uppercase letter.");
		} else if (acam.checkEmailFormat(emailField.getText()) == false) {
			messageLbl.setText("The email must be in the correct format.");
		} else if (acam.checkEmail(emailField.getText()) == true) {
			messageLbl.setText("This email you entered is already in use. Please try another.");
		} else if (acam.checkUsername(userField.getText()) == true) {
			messageLbl.setText("The username you entered is already in use. Please try another.");
		} else {
			acam.createNewAccount(userField.getText(), passField.getText(), fNameField.getText(), lNameField.getText(),
					emailField.getText(), admin);
			messageLbl.setText("Your account has been created. Return to login.");
			clearAllFields();

		}
	}

	public void clear(ActionEvent event) {
		clearAllFields();
	}

	public void clearAllFields() {
		userField.clear();
		passField.clear();
		fNameField.clear();
		lNameField.clear();
		rePassField.clear();
		emailField.clear();
	}

	public boolean checkFieldsForCompletion() {
		if (userField.getText().isEmpty() || passField.getText().isEmpty() || rePassField.getText().isEmpty()
				|| fNameField.getText().isEmpty() || lNameField.getText().isEmpty() || emailField.getText().isEmpty()) {
			messageLbl.setText("All Fields Must Be Completed.");
			return false;
		}
		return true;
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
}
