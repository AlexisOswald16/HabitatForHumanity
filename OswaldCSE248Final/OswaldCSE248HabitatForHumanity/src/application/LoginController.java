package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.LoginModel;

public class LoginController implements Initializable {
	private LoginModel loginModel = new LoginModel();
	Main main = new Main();
	Stage prevStage;

	@FXML
	private Label messageLbl;
	@FXML
	private TextField userNameField;
	@FXML
	private PasswordField passwordField;

	public void login(ActionEvent event) throws IOException {
		messageLbl.setText("Button pressed.");
		main.changeScene("FirstMainSceneView.fxml", messageLbl);

	}

	public void createAccount(ActionEvent event) throws IOException {
		main.changeScene("CreateAccountView.fxml", messageLbl);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

}
