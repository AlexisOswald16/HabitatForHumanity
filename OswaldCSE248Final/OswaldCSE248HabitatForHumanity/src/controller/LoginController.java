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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.LoginModel;

public class LoginController implements Initializable {
	private LoginModel loginModel = new LoginModel();
	Main main = new Main();

	@FXML
	private Label messageLbl;
	@FXML
	private TextField userNameField;
	@FXML
	private PasswordField passwordField;

	public void login(ActionEvent event) throws IOException, SQLException {
		if (loginModel.isLogin(userNameField.getText(), passwordField.getText())) {
			if (loginModel.isAdministrator(userNameField.getText()) == true) {
				main.changeScene("ADMINFirstMainScene.fxml", messageLbl);
			} else {
				main.changeScene("FirstMainSceneView.fxml", messageLbl);
			}
		} else {
			messageLbl.setText("Login Failed, please try again.");
		}
	}

	public void createAccount(ActionEvent event) throws IOException {
		main.changeScene("CreateAccountView.fxml", messageLbl);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

}
